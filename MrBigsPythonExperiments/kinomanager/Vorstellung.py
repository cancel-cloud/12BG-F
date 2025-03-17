from datetime import datetime

class Vorstellung:
    BASISPREIS = 10.00
    AUFPREISUEBERLAENGE = 2.00

    def __init__(self, kinosaal, film, datumUhrzeit: datetime):
        self.kinosaal = kinosaal
        self.film = film
        self.datumUhrzeit = datumUhrzeit
        self.sitzbelegung = [[False] * kinosaal.sitzeProReihe for _ in range(kinosaal.reihen)]

    def sucheBesteFreieSitze(self, anzahl: int) -> int:
        for row in range(len(self.sitzbelegung) - 1, -1, -1):  # Von hinten nach vorne suchen
            for col in range(len(self.sitzbelegung[0]) - anzahl + 1):
                if all(not self.sitzbelegung[row][col + i] for i in range(anzahl)):
                    return row * len(self.sitzbelegung[0]) + col + 1
        return 0

    def belegeSitz(self, sitzNr: int) -> bool:
        row, col = divmod(sitzNr - 1, len(self.sitzbelegung[0]))
        if not self.sitzbelegung[row][col]:
            self.sitzbelegung[row][col] = True
            return True
        return False

    def berechneNormalpreis(self) -> float:
        return self.BASISPREIS + (self.AUFPREISUEBERLAENGE if self.film.laenge > 150 else 0)
