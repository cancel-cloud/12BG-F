import streamlit as st
import pandas as pd
import matplotlib.pyplot as plt
import calplot
from sqlalchemy import create_engine, MetaData, Table, Column, Integer, String, Date, select, func
from datetime import date
import io

# Seiten-Konfiguration
st.set_page_config(
    page_title="☕ Kaffeekonsum-Tracker",
    layout="wide"
)

# Einführung der Sidebar-Toggles
st.sidebar.header("Einstellungen")
show_varieties = st.sidebar.checkbox("Sortenverwaltung anzeigen", value=False)
show_editor = st.sidebar.checkbox("Einträge bearbeiten & löschen", value=True)
show_stats = st.sidebar.checkbox("Statistiken anzeigen", value=True)
show_pie = st.sidebar.checkbox("Sorten-Verteilung anzeigen", value=True)
show_heatmap = st.sidebar.checkbox("Kalender-Heatmap anzeigen", value=True)
show_import = st.sidebar.checkbox("Import/Export anzeigen", value=True)

# Datenbank-Setup
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

# Sortenverwaltung (optional)
if show_varieties:
    with st.sidebar.expander("Sorten verwalten", expanded=False):
        df_var = pd.read_sql(select(varieties), engine)
        new_var = st.text_input("Neue Sorte hinzufügen:")
        if st.button("Hinzufügen", key='add_var') and new_var:
            with engine.begin() as conn:
                conn.execute(varieties.insert().values(name=new_var))
            st.success(f"Sorte '{new_var}' hinzugefügt")
        to_delete = st.multiselect("Sorten löschen", df_var['name'])
        if st.button("Löschen", key='del_var') and to_delete:
            with engine.begin() as conn:
                for name in to_delete:
                    conn.execute(varieties.delete().where(varieties.c.name == name))
            st.success(f"Gelöscht: {', '.join(to_delete)}")

# App-Header
st.title("☕ Kaffeekonsum-Tracker")

# Neuer Eintrag
with st.form("entry_form", clear_on_submit=True):
    entry_date = st.date_input("Datum", max_value=date.today())
    entry_cups = st.number_input("Anzahl Tassen", min_value=1, value=1)
    df_var = pd.read_sql(select(varieties), engine)
    choice = st.selectbox("Sorte wählen", df_var['name'])
    if st.form_submit_button("Eintrag speichern"):
        vid = int(df_var[df_var['name'] == choice]['id'].iloc[0])
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
    SELECT c.id, c.date, c.cups, v.name AS variety
      FROM consumption c
      JOIN varieties v ON c.variety_id = v.id
    """,
    engine, parse_dates=['date']
)

# Einträge bearbeiten & löschen (optional)
if show_editor and not df.empty:
    st.subheader("Einträge bearbeiten & löschen")
    edited = st.data_editor(df, num_rows="dynamic", use_container_width=True)
    if st.button("Änderungen übernehmen"):
        orig_ids = set(df['id'])
        new_ids = set(edited['id'])
        removed = orig_ids - new_ids
        with engine.begin() as conn:
            for rid in removed:
                conn.execute(consumption.delete().where(consumption.c.id == int(rid)))
        merged = edited.set_index('id').combine_first(df.set_index('id'))
        df_var = pd.read_sql(select(varieties), engine)
        with engine.begin() as conn:
            for idx, row in merged.iterrows():
                orig = df.set_index('id').loc[idx]
                if not orig.equals(row):
                    vid = int(df_var[df_var['name'] == row['variety']]['id'].iloc[0])
                    conn.execute(
                        consumption.update()
                                   .where(consumption.c.id == int(idx))
                                   .values(
                                       date=row['date'],
                                       cups=int(row['cups']),
                                       variety_id=vid
                                   )
                    )
        st.success("Datenbank aktualisiert")

# Zeitraum & Statistik-Basis
if show_stats and not df.empty:
    st.subheader("Zeitraum auswählen & Statistiken")
    timeframe = st.selectbox(
        "Zeitraum",
        ["Letzte 7 Tage", "Letzte 30 Tage", "Letzte 90 Tage", "Alles"]
    )
    today = pd.to_datetime(date.today())
    tmp = df.copy()
    if timeframe != "Alles":
        days = int(timeframe.split()[1])
        cutoff = today - pd.Timedelta(days=days-1)
        tmp = tmp[tmp['date'] >= cutoff]

    # Täglicher Verbrauch
    daily = tmp.groupby('date', as_index=False)['cups'].sum().sort_values('date')
    st.bar_chart(
        data=daily.rename(columns={'date':'Datum','cups':'Tassen'}), x='Datum', y='Tassen'
    )

    # Sorten-Verteilung (optional)
    if show_pie:
        st.subheader("Sorten-Verteilung")
        pie = tmp.groupby('variety')['cups'].sum()
        fig1, ax1 = plt.subplots()
        fig1.patch.set_facecolor('none'); fig1.patch.set_alpha(0)
        ax1.set_facecolor('none')
        ax1.pie(
            pie, labels=pie.index, autopct='%1.1f%%', startangle=90,
            textprops={'color':'white'}
        )
        ax1.axis('equal')
        st.pyplot(fig1)

    # Kalender-Heatmap (optional)
    if show_heatmap:
        st.subheader("Kalender-Heatmap")
        tmp_series = tmp.groupby('date')['cups'].sum()
        fig3, axes3 = calplot.calplot(
            tmp_series,
            how='sum',
            fillcolor='lightgrey',
            linewidth=0.5,
            figsize=(10, 4)
        )
        fig3.suptitle("Kaffeekonsum-Heatmap", fontsize=16)
        st.pyplot(fig3)

# Import / Export CSV (optional)
if show_import:
    st.subheader("Import / Export")
    col_imp, col_exp = st.columns(2)
    with col_imp:
        up1 = st.file_uploader("Varieties CSV importieren", type=['csv'])
        if up1:
            dfv = pd.read_csv(up1)
            with engine.begin() as conn:
                conn.execute(varieties.insert(), dfv.to_dict(orient='records'))
            st.success("Sorten importiert")
        up2 = st.file_uploader("Consumption CSV importieren", type=['csv'])
        if up2:
            dfc = pd.read_csv(up2, parse_dates=['date'])
            dfc = dfc.merge(df_var, how='left', left_on='variety', right_on='name')
            to_ins = dfc[['date','cups','id']].rename(columns={'id':'variety_id'}).to_dict(orient='records')
            with engine.begin() as conn:
                conn.execute(consumption.insert(), to_ins)
            st.success("Einträge importiert")
    with col_exp:
        all_var = pd.read_sql(select(varieties), engine)
        buf1 = io.StringIO(); all_var.to_csv(buf1, index=False)
        st.download_button("Varieties exportieren", buf1.getvalue(), "varieties.csv")
        all_cons = pd.read_sql(
            "SELECT c.*, v.name AS variety FROM consumption c JOIN varieties v ON c.variety_id=v.id",
            engine, parse_dates=['date']
        )
        buf2 = io.StringIO(); all_cons.to_csv(buf2, index=False)
        st.download_button("Consumption exportieren", buf2.getvalue(), "consumption.csv")