class Fuehrerschein:
    def __init__(self, nummer, ausstellungsdatum):
        self.nummer = nummer
        self.ausstellungsdatum = ausstellungsdatum

    def getNummer(self):
        return self.nummer

    def getAusstellungsdatum(self):
        return self.ausstellungsdatum

    def __str__(self):
        return f"Fuehrerschein: {self.nummer}, Ausstellungsdatum: {self.ausstellungsdatum}"


class Fahrer:
    def __init__(self, name, fuehrerschein=None):
        self.name = name
        self.fuehrerschein = fuehrerschein

    def getName(self):
        return self.name

    def getFuehrerschein(self):
        return self.fuehrerschein

    def setFuehrerschein(self, fuehrerschein):
        self.fuehrerschein = fuehrerschein

    def __str__(self):
        if self.fuehrerschein:
            return f"Fahrer: {self.name}, Fuehrerschein: {self.fuehrerschein.getNummer()}"
        return f"Fahrer: {self.name}, kein Fuehrerschein zugewiesen"

if __name__ == '__main__':
    # Test
    fuehrerschein = Fuehrerschein("F12345", "12/2025")
    fahrer = Fahrer("Lisa Müller")
    print(f"Name: {fahrer.getName()}")
    print(fahrer)
    fahrer.setFuehrerschein(fuehrerschein)
    print(f"Fuehrerscheinnummer: {fahrer.getFuehrerschein().getNummer()}")
    print(f"Ausstellungsdatum: {fahrer.getFuehrerschein().getAusstellungsdatum()}")
    print(fahrer)
