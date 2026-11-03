# Design Patterns – Auto Service

Java desktop application (Swing + MySQL) for managing car service work orders, built as a seminar project for the course *Software Patterns* at the Faculty of Organizational Sciences, University of Belgrade (Prof. Dr. Siniša Vlajić).

Each GoF design pattern is applied in a separate NetBeans project on the same domain (work order – mechanic).

## Implemented patterns

| Category | Pattern | Project |
|---|---|---|
| Creational | Abstract Factory | `seminarski/AF- Auto Servis/AF-AutoServis` |
| Creational | Builder | `seminarski/BU-AutoServis` |
| Creational | Factory Method | `seminarski/FM-AutoServis/FM-AutoServis` |
| Creational | Prototype | `seminarski/PR-AutoServis` |
| Creational | Singleton | `seminarski/SI-AutoServis` |

## Tech stack

Java, Swing, JDBC, MySQL/MariaDB, NetBeans (Ant). Libraries are included in each project in `komponente/`.

## How to run

1. Create the database with `src/MySQLDatabase/autoservis.sql`.
2. Open the project in NetBeans and run it (main class `Client.Sef`, or `Direktor.Sef` for Builder).

## Credits

The project structure is based on the framework by Prof. Dr. Siniša Vlajić (Software Engineering Laboratory – SILAB, FON).
