class Schulbuch:
    def __init__(self, titel, buch_id):
        self.titel = titel
        self.buch_id = buch_id

    def getTitel(self):
        return self.titel

    def __str__(self):
        return f"Schulbuch: {self.titel} (ID: {self.buch_id})"


class Schueler:
    def __init__(self, name, schulbuch=None):
        self.name = name
        self.schulbuch = schulbuch

    def getName(self):
        return self.name

    def getSchulbuch(self):
        return self.schulbuch

    def setSchulbuch(self, schulbuch):
        self.schulbuch = schulbuch

    def __str__(self):
        if self.schulbuch:
            return f"Schueler: {self.name}, Buch: {self.schulbuch.getTitel()}"
        return f"Schueler: {self.name}, kein Buch zugewiesen"


if __name__ == '__main__':
    # Test
    buch = Schulbuch("Mathematik", 101)
    schueler = Schueler("Max Mustermann")
    print(f"Name: {schueler.getName()}")
    print(schueler)
    schueler.setSchulbuch(buch)
    print(f"Buchtitel: {schueler.getSchulbuch().getTitel()}")
    print(schueler)
    print(schueler.getSchulbuch())
