# Smart Study Planner

A Java-based console application designed to help students organize, track, and manage their study tasks efficiently.

The project focuses on applying **Core Java, Object-Oriented Programming, Collections, Searching, Sorting, Exception Handling, and File Handling** in a practical application.

---

## 📌 Features

* **Add Study Tasks** with title, subject, deadline, and priority
* **View All Tasks** with complete task details
* **Search Tasks** by title
* **Sort Tasks**

  * By deadline
  * By priority
* **Mark Tasks as Completed**
* **Delete Tasks** using their unique Task ID
* **Study Statistics**

  * Total tasks
  * Completed tasks
  * Pending tasks
  * Completion rate
* **File Persistence**

  * Automatically saves tasks locally
  * Loads previously saved tasks when the application starts
* **Permanent Task IDs**

  * Task IDs remain unchanged even after sorting
* **Input Validation**

  * Handles invalid menu choices and numeric input
  * Validates priority values

---

## 🛠️ Technologies Used

* **Java**
* **ArrayList**
* **File I/O**
* **Comparator**
* **Exception Handling**
* **Object-Oriented Programming**

---

## 🧠 Java Concepts Practiced

This project was developed to strengthen practical understanding of:

* Classes and Objects
* Encapsulation
* Constructors
* Methods
* Access Modifiers
* `ArrayList`
* Searching
* Sorting
* `Comparator`
* Loops and Conditional Statements
* Exception Handling
* File Reading and Writing
* Try-with-resources
* Basic Data Persistence

---

## 📂 Project Structure

```text
Smart-Study_Planner/
│
├── src/
│   ├── Main.java
│   └── StudyTask.java
│
├── .gitignore
└── README.md
```

---

## ⚙️ How It Works

When the application starts, it loads previously saved study tasks from the local data file.

The main menu provides options to manage the tasks:

```text
===== SMART STUDY PLANNER =====

1. Add Study Task
2. View All Tasks
3. Search Task
4. Sort Tasks
5. Mark Task Completed
6. Delete Task
7. Study Statistics
8. Save Tasks
9. Exit
```

Each task contains:

* Task ID
* Task title
* Subject
* Deadline
* Priority
* Completion status

---

## 💾 Data Persistence

The application stores task data locally in:

```text
tasks.txt
```

Tasks are automatically saved when the application exits.

The local data file is excluded from version control through `.gitignore`, so personal study data is not uploaded to the repository.

---

## ▶️ How to Run

### 1. Clone the repository

```bash
git clone <repository-url>
```

### 2. Open the project

Open the project folder in VS Code or any Java-supported IDE.

### 3. Compile the source files

From the `src` directory:

```bash
javac Main.java StudyTask.java
```

### 4. Run the application

```bash
java Main
```

---

## 🎯 Project Objective

The main objective of this project is to build a practical Java application while developing a strong foundation in **Core Java and Object-Oriented Programming**.

It also introduces important programming concepts such as collections, searching, sorting, exception handling, and local file-based data persistence.

---

## 🚀 Future Improvements

Possible future enhancements include:

* Date validation using Java's Date/Time API
* More advanced task filtering
* Subject-wise statistics
* Overdue task detection
* Improved console interface
* Database integration
* GUI or web-based version

---

## 📈 Learning Progress

This project is part of my **Java learning journey**, where projects are used to progressively apply programming concepts and build practical problem-solving skills.
