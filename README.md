# 📝 CoreStruct Task System

[![Java](https://img.shields.io/badge/Java-17%2B-blue)](https://www.oracle.com/java/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A **console-based task management system** built entirely with custom data structures and algorithms. Developed as part of the **CM1602 Data Structures and Algorithms for Artificial Intelligence** module to showcase practical application of fundamental computer science concepts.

![Demo Screenshot](docs/demo.png) <!-- Optional: add a screenshot later -->

---

## 🚀 Features

- ✅ Add, edit, delete, and view tasks with title, priority (low/medium/high), and completion status
- 🔄 Undo last added task using a LIFO stack
- 📋 Process tasks in FIFO order with a queue
- 🔍 Search tasks by ID via linear search
- 📊 Sort tasks by priority (high → medium → low) using bubble sort
- 💾 Persistent in-memory storage with a custom singly linked list
- 🧪 Robust input validation and edge-case handling (empty list, invalid IDs, etc.)

---

## 📚 Data Structures & Algorithms

| Structure / Algorithm       | Implementation | Complexity (Avg) | Purpose                          |
|-----------------------------|----------------|-----------------|----------------------------------|
| **Singly Linked List**      | `CWLinkedList`, `CWNode` | O(1) add, O(n) search | Core task storage               |
| **Queue (FIFO)**            | `CWQueue`      | O(1) enqueue/dequeue | Fair task processing            |
| **Stack (LIFO)**            | `CWStack` (composition) | O(1) push/pop | Undo functionality              |
| **Bubble Sort**             | `CWLinkedList.bubbleSortByPriority` | O(n²) | Priority-based ordering         |
| **Linear Search**           | `CWLinkedList.getById` | O(n) | Task retrieval by ID            |

All implementations are **hand-written**, without using Java Collections Framework classes.

---

## 🏁 Getting Started

### Prerequisites

- Java Development Kit (JDK) 17 or higher  
- Terminal / Command Prompt

### Running the Application

1. **Clone the repository**
```bash
git clone https://github.com/yourusername/DSA-TaskManagement-System.git
cd DSA-TaskManagement-System
```

2. **Compile the Java files**
```bash
javac *.java
```

3. **Run the program**
```bash
java Main
```

You will see the menu:

```
Arosha's Task Management System
----------------------
Main Menu:
1. Add Task
2. Undo Last Added Task
3. Discard First Available Task
4. Remove a Task
5. View Tasks in Priority Order
6. View Tasks in Added Order
7. Edit Task
8. View and Mark Tasks as Complete
9. Exit
Kindly choose an option:
```

---

## 💡 Usage Examples

### Adding a Task
```
Add New Task
------------
Enter task title: Finish project report
Enter priority (low/medium/high): high
Successfully added the task!
```

### Viewing Tasks in Priority Order
```
Sorting tasks by priority (High > Medium > Low)...
Tasks sorted successfully!

Tasks in Priority Order:
-----------------------
Task ID: 2 | Title: Finish project report | Priority: high | Status: Ongoing ⏳
Task ID: 3 | Title: Water plants | Priority: medium | Status: Ongoing ⏳
Task ID: 1 | Title: Buy groceries | Priority: low | Status: Completed ✅
```

### Undo Last Addition
```
Most recently added task: Task ID: 3 | Title: Water plants | Priority: medium
Proceed with undoing this addition? (y/n): y
Undo complete! Task has been removed.
```

---

## 🗂 Project Structure

```
.
├── CWLinkedList.java       # Singly linked list with bubble sort & linear search
├── CWNode.java             # Node class for linked structures
├── CWQueue.java            # FIFO queue using linked nodes
├── CWStack.java            # LIFO stack composed with CWLinkedList
├── CWTask.java             # Task entity (ID, title, priority, status)
├── Main.java               # Console UI and orchestration
└── README.md
```

---

## 🧪 Testing & Validation

Tested against multiple scenarios:

- Adding tasks with mixed priorities  
- Sorting and verifying order  
- Removing tasks by ID (valid/invalid)  
- Undo operations (single/multiple)  
- FIFO discarding  
- Empty list handling  
- Marking tasks as complete  

For detailed test cases, see the coursework report in the repository.

---

## 📊 Complexity Summary

| Operation            | Time Complexity | Space Complexity |
|----------------------|----------------|----------------|
| Add task             | O(1)           | O(1)           |
| Remove by ID         | O(n)           | O(1)           |
| Search by ID         | O(n)           | O(1)           |
| Bubble sort          | O(n²)          | O(1)           |
| Stack push/pop       | O(1)           | O(1)           |
| Queue enqueue/dequeue| O(1)           | O(1)           |

---

## 🙏 Acknowledgments

- Module Leader: Ms. Malsha Fernando  
- Inspired by: *Introduction to Algorithms* (Cormen et al.) and *Algorithms* (Sedgewick & Wayne)  
- Developed as part of the BSc (Hons) Artificial Intelligence and Data Science at IIT / RGU

---

## 📜 License

This project is licensed under the MIT License - see the LICENSE file for details.

**Happy task managing! 🚀**
