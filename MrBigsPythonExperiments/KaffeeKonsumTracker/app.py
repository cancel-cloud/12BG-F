import streamlit as st
import pandas as pd
import matplotlib.pyplot as plt
from sqlalchemy import (
    create_engine, MetaData, Table, Column,
    Integer, String, Date, select, func
)
from datetime import date

# --- Datenbank-Setup ---
engine = create_engine('sqlite:///coffee.db', echo=False)
metadata = MetaData()

consumption = Table(
    'consumption', metadata,
    Column('id', Integer, primary_key=True, autoincrement=True),
    Column('date', Date, nullable=False),
    Column('cups', Integer, nullable=False),
    Column('variety_id', Integer, nullable=False),
)
varieties = Table(
    'varieties', metadata,
    Column('id', Integer, primary_key=True, autoincrement=True),
    Column('name', String, unique=True, nullable=False),
)
metadata.create_all(engine)

# Sorten vorbefüllen
with engine.begin() as conn:
    if conn.execute(select(func.count()).select_from(varieties)).scalar_one() == 0:
        conn.execute(varieties.insert(), [
            {"name": v} for v in
            ["Espresso", "Americano", "Latte", "Cappuccino", "Flat White"]
        ])

# --- Streamlit-App ---
st.title("☕ Kaffeekonsum-Tracker")

# Sorten laden
with engine.connect() as conn:
    rows = conn.execute(select(varieties.c.id, varieties.c.name)).fetchall()
ids, names = zip(*rows)

# Eintrags-Form
with st.form("entry_form", clear_on_submit=True):
    entry_date    = st.date_input("Datum", max_value=date.today())
    entry_cups    = st.number_input("Anzahl Tassen", min_value=1, value=1)
    entry_variety = st.radio("Sorte wählen", names)
    if st.form_submit_button("Eintrag speichern"):
        vid = ids[names.index(entry_variety)]
        with engine.begin() as conn:
            conn.execute(
                consumption.insert().values(
                    date=entry_date,
                    cups=entry_cups,
                    variety_id=vid
                )
            )
        st.success("✅ Eintrag gespeichert")

# Daten laden
df = pd.read_sql(
    """
    SELECT c.date, c.cups, v.name AS variety
      FROM consumption c
      JOIN varieties v ON c.variety_id = v.id
    """,
    engine, parse_dates=['date']
)

if df.empty:
    st.info("Noch keine Einträge vorhanden.")
else:
    # --- Zeit-Frame-Toggle ---
    option = st.selectbox(
        "Zeitraum auswählen",
        ["Letzte 7 Tage", "Letzte 30 Tage", "Letzte 90 Tage", "Alles"]
    )
    today = pd.to_datetime(date.today())
    if option != "Alles":
        days = int(option.split()[1])
        cutoff = today - pd.Timedelta(days=days-1)
        df = df[df['date'] >= cutoff]

    # --- Täglicher Verbrauch als Balken ---
    daily = (
        df
        .groupby('date', as_index=False)['cups']
        .sum()
        .sort_values('date')
        .rename(columns={'date': 'Datum', 'cups': 'Tassen'})
    )
    st.subheader("Täglicher Verbrauch")
    st.bar_chart(data=daily, x='Datum', y='Tassen')

    # --- Kreisdiagramm der Sorten im selben Zeitraum (Text weiß) ---
    st.subheader("Sorten-Verteilung")
    pie_data = df.groupby('variety')['cups'].sum()
    fig, ax = plt.subplots()
    # transparent background
    fig.patch.set_facecolor('none')
    fig.patch.set_alpha(0)
    ax.set_facecolor('none')
    # weiße Beschriftung
    ax.pie(
        pie_data,
        labels=pie_data.index,
        autopct='%1.1f%%',
        startangle=90,
        textprops={'color': 'white'}
    )
    ax.axis('equal')
    st.pyplot(fig)

    # --- Rohdaten ---
    st.subheader("Einzelne Einträge")
    st.dataframe(df)