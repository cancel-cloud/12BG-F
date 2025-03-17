from datetime import datetime

class Kinosaal:
    def __init__(self, nr: int, reihen: int, sitzeProReihe: int):
        self.nr = nr
        self.reihen = reihen
        self.sitzeProReihe = sitzeProReihe
        self.vorstellungen = []

    def einfuegen(self, vorstellung):
        self.vorstellungen.append(vorstellung)

    def istFrei(self, datumUhrzeit: datetime, dauer: int) -> bool:
        for v in self.vorstellungen:
            if abs((v.datumUhrzeit - datumUhrzeit).total_seconds()) < dauer * 60:
                return False
        return True
