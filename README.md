# Tutorial 4 - Interface & Polymorphism (Combined GUI)

Four **separate Java Swing programs** presented through **one main menu**.
Original Java interfaces, classes, and program logic are retained in their respective folders.

## Requirements

- Java JDK (e.g., JDK 21), with `java` and `javac` on `PATH`.
- Windows Command Prompt, PowerShell, or VS Code terminal.

## Quick start (Windows)

Download/extract this ZIP or clone the repository. Open a terminal **in this folder** (`Tutorial4_GUI`) and run:

```cmd
run.cmd
```

Alternatively, double-click `run.cmd` in File Explorer. This compiles all four projects, starts the main menu, and provides buttons to open each program.

**Important:** Each program opens in its own window. You can close that window and return to the menu. The launcher starts each program in a separate Java process so the two different `SmartPhone` classes do not conflict.

## Manual terminal commands (Windows)

From `Tutorial4_GUI`:

```cmd
cd DataSetApp
javac *.java
cd ..\EdibleApp
javac *.java
cd ..\MobileApp
javac *.java
cd ..\PriceApp
javac *.java
cd ..
javac Tutorial4App.java
java Tutorial4App
```

## Files

- `Tutorial4App.java` - main menu and launcher
- `run.cmd` - one-command compilation and launch
- `DataSetApp/` - `Measurable`, `DataSet`, `BankAccount`, `Coin`, `Student`
- `EdibleApp/` - `Edible`, `Animal`, `Fruit`, `Orange`, `Apple`, `Person`
- `MobileApp/` - `RadioTuner`, `MobileDevice`, `SmartPhone`, `BasicPhone`
- `PriceApp/` - `Price`, `Product`, `SmartPhone`, `Laptop`, `Car`, `Customer`

All business logic is in the original program classes. This project is a menu to open each UI; it does not combine the four underlying exercises into one class or change their original salary rules.
