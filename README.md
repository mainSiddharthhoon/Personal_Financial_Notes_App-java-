# Personal Finance Manager - Development Overview

This document provides a detailed explanation of the **Personal Finance Manager** project, including its folder structure, the purpose of each file, and how the components are interconnected. This will help developers and AI systems understand the development workflow and the role of each part of the project.

---

## Project Overview

The **Personal Finance Manager** is a Java-based desktop application designed to help users manage their personal finances. It includes features such as user authentication, transaction management, budget tracking, and financial reporting. The application uses a layered architecture with clear separation of concerns, ensuring modularity and maintainability.

---

## Folder Structure and Purpose

The project is organized into the following folders:

### 1. **`model/`** - Data Models
This folder contains the core data models that represent the application's entities. These classes are simple Java objects (POJOs) with fields, constructors, getters, setters, and `toString()` methods.

- **`Budget.java`**: Represents a user's budget for a specific category.
- **`Transaction.java`**: Represents a financial transaction made by a user.
- **`User.java`**: Represents a user of the application, including their credentials and personal details.

**How it connects:**
- These models are used across the `dao/`, `service/`, and `ui/` layers to represent and manipulate data.

---

### 2. **`dao/`** - Data Access Objects
This folder handles all file-based data persistence. Each DAO class is responsible for reading from and writing to specific text files in the `data/` folder.

- **`FileUtil.java`**: A utility class for reading and writing to files.
- **`UserDAO.java`**: Manages user data stored in `data/users.txt`.
- **`TransactionDAO.java`**: Manages transaction data stored in `data/transactions.txt`.
- **`BudgetDAO.java`**: Manages budget data stored in `data/budgets.txt`.

**How it connects:**
- DAO classes interact with the `model/` classes to convert data between objects and text file formats.
- DAO classes are used by the `service/` layer to perform CRUD operations on data.

---

### 3. **`service/`** - Business Logic
This folder contains the service classes that implement the application's core business logic. These classes act as intermediaries between the `dao/` layer and the `ui/` layer.

- **`AuthService.java`**: Handles user authentication (login, registration, password recovery).
- **`TransactionService.java`**: Manages transaction-related operations, such as adding and retrieving transactions.
- **`BudgetService.java`**: Manages budget-related operations, including adding budgets and generating alerts.
- **`ReportService.java`**: Generates financial reports, such as spending by category and monthly summaries.

**How it connects:**
- Service classes use DAO classes to fetch and persist data.
- Service classes are called by the `ui/` layer to perform operations requested by the user.

---

### 4. **`ui/`** - User Interface
This folder contains the Swing-based graphical user interface (GUI) classes. Each class represents a specific screen in the application.

- **`LoginScreen.java`**: The login screen where users can log in, register, or recover their password.
- **`RegistrationScreen.java`**: The registration screen for creating new user accounts.
- **`RecoverPasswordScreen.java`**: The screen for recovering forgotten passwords.
- **`DashboardScreen.java`**: The main dashboard that provides navigation to other features.
- **`AddTransactionScreen.java`**: The screen for adding new transactions.
- **`ViewTransactionsScreen.java`**: The screen for viewing a user's transaction history.
- **`BudgetScreen.java`**: The screen for managing budgets and viewing alerts.
- **`ReportScreen.java`**: The screen for generating and viewing financial reports.

**How it connects:**
- UI classes call methods from the `service/` layer to perform operations.
- UI classes display data fetched from the `service/` layer and allow users to interact with the application.

---

### 5. **`data/`** - Data Storage
This folder contains the text files used for data persistence. Each file stores data in a simple, comma-separated format.

- **`users.txt`**: Stores user information (username, password, name, age).
- **`transactions.txt`**: Stores transaction records (username, date, time, amount, purpose).
- **`budgets.txt`**: Stores budget settings (username, category, limit).
- **`reports.txt`**: Stores generated reports (username, month, report type, value).

**How it connects:**
- DAO classes read from and write to these files.
- The `service/` layer uses DAO classes to manipulate the data in these files.

---

### 6. Root Files
- **`Main.java`**: The entry point of the application. It initializes the application, sets the look and feel, and launches the `LoginScreen`.
- **`README.md`**: Documentation for the project.

**How it connects:**
- `Main.java` launches the application and sets up the initial environment.

---

## Development Workflow

Here’s how the different layers interact during the application's execution:

1. **User Interaction**:
   - The user interacts with the GUI (`ui/` classes) to perform actions like logging in, adding transactions, or viewing reports.

2. **Service Layer**:
   - The `ui/` classes call methods in the `service/` layer to handle the requested operations.
   - The `service/` layer contains the business logic and ensures data integrity.

3. **Data Access**:
   - The `service/` layer interacts with the `dao/` layer to fetch or persist data.
   - The `dao/` layer reads from or writes to the text files in the `data/` folder.

4. **Data Models**:
   - The `model/` classes are used to represent data throughout the application.

---

## Example: Adding a Transaction

Here’s how the components work together when a user adds a transaction:

1. **UI Layer**:
   - The user enters transaction details in the `AddTransactionScreen` and clicks "Save Transaction."
   - The `AddTransactionScreen` calls the `addTransaction` method in the `TransactionService`.

2. **Service Layer**:
   - The `TransactionService` validates the input and creates a `Transaction` object.
   - It then calls the `saveTransaction` method in the `TransactionDAO`.

3. **DAO Layer**:
   - The `TransactionDAO` converts the `Transaction` object into a string and writes it to `data/transactions.txt` using `FileUtil`.

4. **Data Persistence**:
   - The transaction is saved in the `transactions.txt` file, ensuring it is available for future use.

---

## Key Design Principles

- **Separation of Concerns**: Each layer has a specific responsibility, ensuring modularity and easier maintenance.
- **Data Persistence**: All data is stored in text files, making the application lightweight and portable.
- **Scalability**: The architecture allows for easy addition of new features or modifications.

---

## How to Run the Application

1. Compile the application:
   ```sh
   javac -d . Main.java model/*.java dao/*.java service/*.java ui/*.java
   ```

2. Run the application:
   ```sh
   java Main
   ```

3. Use the following credentials to log in:
   - Username: `admin`, Password: `admin123`
   - Username: `demo`, Password: `demo123`

---

## Conclusion

This project is a well-structured Java application that demonstrates the use of layered architecture, file-based data persistence, and Swing for GUI development. Each folder and file has a specific role, and the components work together seamlessly to provide a functional and user-friendly application.
## 📌 Project Status

This project was developed as a learning exercise to explore Java application architecture, including layered design, file-based data persistence, and Swing-based GUI development.

The application is fully functional in its current state. Further development is not currently planned, but the repository remains available for learning and reference purposes.
