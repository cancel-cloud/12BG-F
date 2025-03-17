from datetime import datetime

from kinomanager.Film import Film
from kinomanager.Kinosaal import Kinosaal
from kinomanager.Vorstellung import Vorstellung


class KinoManager:
    def __init__(self):
        self.kinosaele = []
        self.filme = []

    def zeigeFilme(self):
        return self.filme

    def zeigeVorstellungenZuFilm(self, film):
        return film.vorstellungen

    def zeigeSitzbelegungZuVorstellung(self, vorstellung):
        return vorstellung.sitzbelegung

    def sucheBesteFreieSitze(self, vorst, anzahl: int) -> int:
        return vorst.sucheBesteFreieSitze(anzahl)

    def belegeSitze(self, vorst, sitznr: int, anzahl: int) -> bool:
        return vorst.belegeSitz(sitznr)

    def erstelleVorstellung(self, film, anzahlPlaetze: int, datumUhrzeit: datetime):
        for saal in self.kinosaele:
            if saal.sitzeProReihe * saal.reihen >= anzahlPlaetze and saal.istFrei(datumUhrzeit, film.laenge):
                vorstellung = Vorstellung(saal, film, datumUhrzeit)
                saal.einfuegen(vorstellung)
                film.einfuegen(vorstellung)
                return vorstellung
        return None


if __name__ == "__main__":
    manager = KinoManager()
    saal1 = Kinosaal(1, 10, 15)
    manager.kinosaele.append(saal1)

    film1 = Film("Inception", 2010, 148)
    manager.filme.append(film1)

    vorstellung1 = manager.erstelleVorstellung(film1, 150, datetime(2025, 3, 10, 20, 0))

    print("Filme im Kino:", [film.titel for film in manager.zeigeFilme()])
    print("Vorstellungen für Inception:", len(manager.zeigeVorstellungenZuFilm(film1)))
    print("Normalpreis für die Vorstellung:", vorstellung1.berechneNormalpreis())
