# Sistema Veterinaria

This project is a **Java application for managing a veterinary clinic**, designed with a layered architecture and following the **MVC (Model-View-Controller)** pattern.

## 📌 Description
The system includes the following components:

- **DAO (AnimalDAOImpl):** Handles communication with the **MySQL/MariaDB database (VeterinariaDB)**, performing CRUD operations on animals.  
- **Service (AnimalServiceImpl):** Contains the business logic for managing animals, calling DAO methods to access data.  
- **Controller (ClinicaController):** Coordinates requests from the user interface and connects them with the service and DAO.  
- **View (ConsoleUI):** A **console-based user interface** where the user interacts with the system.  

The main execution flow starts in the **Main** class, which:  
1. Configures the connection to the database.  
2. Creates instances of each layer (DAO, Service, Controller, UI).  
3. Runs the interface with `ui.start()`.  

## 🛠️ Tech Stack
- **Language:** Java  
- **Database:** MySQL / MariaDB  
- **Architecture:** Layered + MVC pattern  
- **IDE:** IntelliJ IDEA  

## ▶️ How to Run
1. Import the project into **IntelliJ IDEA**.  
2. Make sure MySQL/MariaDB is running.  
3. Create a database named `VeterinariaDB` and execute the script `database.sql` (included in this repo) to set up tables.  
4. Open the `Main.java` class.  
5. Run the project → the console-based UI will start.  

## 📚 Learning Purpose
This project was developed to practice:
- **Layered architecture (DAO, Service, Controller, UI)**  
- **MVC design pattern**  
- **Database integration (MySQL/MariaDB)**  
- **Object-Oriented Programming (OOP)** principles applied to a real-world use case  

---

✍️ **Author:** D’Jehovann Dixon Lawrence
