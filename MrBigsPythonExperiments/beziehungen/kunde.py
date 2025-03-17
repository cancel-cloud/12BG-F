class Kreditkarte:
    def __init__(self, kartennummer, limitbetrag):
        self.kartennummer = kartennummer
        self.limitbetrag = limitbetrag

    def getKartennummer(self):
        return self.kartennummer

    def getLimitbetrag(self):
        return self.limitbetrag

    def setLimitbetrag(self, neuer_betrag):
        self.limitbetrag = neuer_betrag

    def __str__(self):
        return f"Kreditkarte: {self.kartennummer}, Limit: {self.limitbetrag}€"


class Kunde:
    def __init__(self, name, kreditkarte=None):
        self.name = name
        self.kreditkarte = kreditkarte

    def getName(self):
        return self.name

    def getKreditkarte(self):
        return self.kreditkarte

    def setKreditkarte(self, kreditkarte):
        self.kreditkarte = kreditkarte

    def __str__(self):
        if self.kreditkarte:
            return f"Kunde: {self.name}, Kreditkarte: {self.kreditkarte.getKartennummer()}"
        return f"Kunde: {self.name}, keine Kreditkarte zugewiesen"

if __name__ == '__main__':
    # Test
    kreditkarte = Kreditkarte("1234-5678-9101-1121", 5000)
    kunde = Kunde("Erik Schuster")
    print(f"Name: {kunde.getName()}")
    print(kunde)
    kunde.setKreditkarte(kreditkarte)
    print(f"Kreditkartennummer: {kunde.getKreditkarte().getKartennummer()}")
    print(f"Aktueller Limitbetrag: {kunde.getKreditkarte().getLimitbetrag()}€")
    kunde.getKreditkarte().setLimitbetrag(6000)
    print(f"Neuer Limitbetrag: {kunde.getKreditkarte().getLimitbetrag()}€")
    print(kunde)
