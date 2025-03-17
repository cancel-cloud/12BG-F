class Film:
    def __init__(self, titel: str, produktionsjahr: int, laenge: int):
        self.titel = titel
        self.produktionsjahr = produktionsjahr
        self.laenge = laenge
        self.vorstellungen = []

    def einfuegen(self, vorstellung):
        self.vorstellungen.append(vorstellung)
        self.vorstellungen.sort(key=lambda v: v.datumUhrzeit)  # Sortieren nach Datum und Uhrzeit
