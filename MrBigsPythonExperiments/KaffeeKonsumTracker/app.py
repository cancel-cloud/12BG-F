import streamlit as st
import pandas as pd
from sqlalchemy import (
    create_engine, MetaData, Table, Column,
    Integer, String, Date, select, func
)
from datetime import date

# --- Datenbank-Setup ---
engine = create_engine('sqlite:///coffee.db')
metadata = MetaData()

# Tabelle für Konsum-Einträge
consumption = Table(
    'consumption', metadata,
    Column('id', Integer, primary_key=True, autoincrement=True),
    Column('date', Date, nullable=False),
    Column('cups', Integer, nullable=False),
    Column('variety_id', Integer, nullable=False)
)

# Tabelle für Sorten
varieties = Table(
    'varieties', metadata,
    Column('id', Integer, primary_key=True, autoincrement=True),
    Column('name', String, unique=True, nullable=False)
)

metadata.create_all(engine)

# --- Sorten vorbefüllen, falls leer ---
with engine.connect() as conn:
    count = conn.execute(
        select(func.count()).select_from(varieties)
    ).scalar_one()
    if count == 0:
        default = ['Espresso', 'Americano', 'Latte', 'Cappuccino']
        conn.execute(varieties.insert(), [{"name": v} for v in default])

# --- Streamlit-App ---
st.title("☕ Kaffeekonsum-Tracker")

# Sorten aus DB laden
with engine.connect() as conn:
    result = conn.execute(
        select(varieties.c.id, varieties.c.name)
    )
    options = result.fetchall()

ids   = [row[0] for row in options]
names = [row[1] for row in options]

with st.form("entry_form", clear_on_submit=True):
    entry_date    = st.date_input("Datum", max_value=date.today())
    entry_cups    = st.number_input("Anzahl Tassen", min_value=1, value=1)
    entry_variety = st.radio("Sorte wählen", names)
    if st.form_submit_button("Eintrag speichern"):
        vid = ids[names.index(entry_variety)]
        with engine.connect() as conn:
            conn.execute(
                consumption.insert().values(
                    date=entry_date,
                    cups=entry_cups,
                    variety_id=vid
                )
            )
        st.success("✅ Eintrag gespeichert")

# --- Daten laden & visualisieren ---
df = pd.read_sql(
    """
    SELECT c.id, c.date, c.cups, v.name AS variety
      FROM consumption c
      JOIN varieties v
        ON c.variety_id = v.id
     ORDER BY date
    """,
    engine,
    parse_dates=['date']
)

if df.empty:
    st.info("Noch keine Einträge vorhanden.")
else:
    st.subheader("Täglicher Verbrauch")
    st.line_chart(df.set_index('date')['cups'])

    st.subheader("Verteilung nach Sorte")
    st.bar_chart(df['variety'].value_counts())

    st.subheader("Rohdaten")
    st.dataframe(df)