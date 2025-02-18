import os

# Dateiname für die To-Do-Liste
TODO_FILE = "todo.txt"

def add_task(task):
    """Fügt eine neue Aufgabe zur To-Do-Liste hinzu."""
    with open(TODO_FILE, "a") as f:
        f.write(task + "\n")
    print(f"Aufgabe '{task}' hinzugefügt.")

def list_tasks():
    """Listet alle Aufgaben in der To-Do-Liste auf."""
    if not os.path.exists(TODO_FILE):
        print("Die To-Do-Liste ist leer.")
        return

    with open(TODO_FILE, "r") as f:
        tasks = f.readlines()
        if not tasks:
            print("Die To-Do-Liste ist leer.")
            return

        print("\nTo-Do-Liste:")
        for i, task in enumerate(tasks):
            print(f"{i+1}. {task.strip()}")  # Nummerierung und Entfernung von Leerzeichen

def complete_task(task_number):
    """Markiert eine Aufgabe als erledigt (entfernt sie aus der Liste)."""
    try:
        task_number = int(task_number)
    except ValueError:
        print("Ungültige Aufgabennummer.")
        return

    if not os.path.exists(TODO_FILE):
        print("Die To-Do-Liste ist leer.")
        return

    with open(TODO_FILE, "r") as f:
        tasks = f.readlines()

    if 1 <= task_number <= len(tasks):
        completed_task = tasks.pop(task_number - 1).strip()
        print(f"Aufgabe '{completed_task}' als erledigt markiert.")

        with open(TODO_FILE, "w") as f:
            f.writelines(tasks)  # Schreibe die aktualisierte Liste zurück
    else:
        print("Ungültige Aufgabennummer.")

def main():
    """Hauptfunktion der To-Do-Listen-App."""
    while True:
        print("\nTo-Do-Liste (Kommandozeile)")
        print("1. Aufgabe hinzufügen")
        print("2. Aufgaben auflisten")
        print("3. Aufgabe als erledigt markieren")
        print("4. Beenden")

        choice = input("Bitte wähle eine Option: ")

        if choice == "1":
            task = input("Gib die neue Aufgabe ein: ")
            add_task(task)
        elif choice == "2":
            list_tasks()
        elif choice == "3":
            list_tasks()
            print("----------------------")
            task_number = input("Gib die Nummer der zu erledigenden Aufgabe ein: ")
            complete_task(task_number)
        elif choice == "4":
            print("Auf Wiedersehen!")
            break
        else:
            print("Ungültige Eingabe. Bitte wähle eine Option von 1 bis 4.")

if __name__ == "__main__":
    main()
