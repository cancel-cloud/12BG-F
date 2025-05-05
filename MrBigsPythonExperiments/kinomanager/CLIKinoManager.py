import sys
import json
from datetime import datetime
from kinomanager.Film import Film
from kinomanager.Kinosaal import Kinosaal
from kinomanager.Vorstellung import Vorstellung
from kinomanager.KinoManager import KinoManager


def save_data(manager):
    data = {
        "filme": [(film.titel, film.dauer, film.laenge) for film in manager.filme],
        "kinosaele": [(saal.nummer, saal.reihen, saal.sitzeProReihe) for saal in manager.kinosaele],
    }
    with open("kino_data.json", "w") as f:
        json.dump(data, f)
    print("Daten gespeichert.")


def load_data(manager):
    try:
        with open("kino_data.json", "r") as f:
            data = json.load(f)
        manager.filme = [Film(*film) for film in data["filme"]]
        manager.kinosaele = [Kinosaal(*saal) for saal in data["kinosaele"]]
        print("Daten geladen.")
    except FileNotFoundError:
        print("Keine gespeicherten Daten gefunden.")


def main():
    manager = KinoManager()
    load_data(manager)

    while True:
        print("\nKino Manager - Menü")
        print("1. Neuen Film hinzufügen")
        print("2. Neuen Kinosaal hinzufügen")
        print("3. Neue Vorstellung erstellen")
        print("4. Filme anzeigen")
        print("5. Vorstellungen zu einem Film anzeigen")
        print("6. Sitzbelegung anzeigen")
        print("7. Sitz reservieren")
        print("8. Daten speichern")
        print("9. Daten laden")
        print("10. Beenden")

        auswahl = input("Bitte wähle eine Option: ")

        if auswahl == "1":
            titel = input("Filmtitel: ")
            dauer = int(input("Dauer in Minuten: "))
            film = Film(titel, dauer, dauer)
            manager.filme.append(film)
            print(f"Film '{titel}' wurde hinzugefügt.")

        elif auswahl == "2":
            nummer = input("Saalnummer: ")
            reihen = int(input("Anzahl der Reihen: "))
            sitze_pro_reihe = int(input("Sitze pro Reihe: "))
            saal = Kinosaal(nummer, reihen, sitze_pro_reihe)
            manager.kinosaele.append(saal)
            print(f"Kinosaal {nummer} wurde hinzugefügt.")

        elif auswahl == "3":
            print("Verfügbare Filme:")
            for idx, film in enumerate(manager.filme):
                print(f"{idx + 1}. {film.titel}")
            film_index = int(input("Wähle einen Film (Nummer): ")) - 1
            film = manager.filme[film_index]

            anzahl_plaetze = int(input("Anzahl der Plätze: "))
            datum = input("Datum und Uhrzeit (YYYY-MM-DD HH:MM): ")
            datum_obj = datetime.strptime(datum, "%Y-%m-%d %H:%M")

            if manager.erstelleVorstellung(film, anzahl_plaetze, datum_obj):
                print("Vorstellung wurde erstellt.")
            else:
                print("Keine freien Kinosäle für diese Vorstellung.")

        elif auswahl == "4":
            for film in manager.zeigeFilme():
                print(f"{film.titel} ({film.dauer} Min)")

        elif auswahl == "5":
            print("Verfügbare Filme:")
            for idx, film in enumerate(manager.filme):
                print(f"{idx + 1}. {film.titel}")
            film_index = int(input("Wähle einen Film (Nummer): ")) - 1
            film = manager.filme[film_index]
            for vorstellung in manager.zeigeVorstellungenZuFilm(film):
                print(f"{vorstellung.datumUhrzeit} - Saal {vorstellung.kinosaal.nummer}")

        elif auswahl == "6":
            print("Verfügbare Vorstellungen:")
            for idx, film in enumerate(manager.filme):
                for vorstellung in film.vorstellungen:
                    print(f"{idx + 1}. {film.titel} - {vorstellung.datumUhrzeit}")
            auswahl = int(input("Wähle eine Vorstellung (Nummer): ")) - 1
            print("Sitzbelegung:")
            print(manager.zeigeSitzbelegungZuVorstellung(film.vorstellungen[auswahl]))

        elif auswahl == "7":
            print("Verfügbare Vorstellungen:")
            for idx, film in enumerate(manager.filme):
                for vorstellung in film.vorstellungen:
                    print(f"{idx + 1}. {film.titel} - {vorstellung.datumUhrzeit}")
            auswahl = int(input("Wähle eine Vorstellung (Nummer): ")) - 1
            sitznr = int(input("Sitznummer: "))
            if manager.belegeSitze(film.vorstellungen[auswahl], sitznr, 1):
                print("Sitz erfolgreich reserviert.")
            else:
                print("Reservierung fehlgeschlagen. Sitz ist bereits belegt.")

        elif auswahl == "8":
            save_data(manager)

        elif auswahl == "9":
            load_data(manager)

        elif auswahl == "10":
            print("Programm wird beendet.")
            sys.exit()

        else:
            print("Ungültige Auswahl, bitte erneut versuchen.")


if __name__ == "__main__":
    main()
