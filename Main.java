import java.util.Scanner;

public class Main {
    private static final CWLinkedList taskList = new CWLinkedList();
    private static CWQueue taskQueue = new CWQueue();
    private static final CWStack undoStack = new CWStack();
    private static final Scanner scanner = new Scanner(System.in);
    private static int nextId = 1;

    public static void main(String[] args) {
        System.out.println("Arosha's Task Management System");
        System.out.println("----------------------");

        while (true) {
            displayMenu();
            int choice = getIntInput("Kindly choose an option: ", 9);

            switch (choice) {
                case 1: addTask(); break;
                case 2: undoLastAddedTask(); break;
                case 3: discardFirstTask(); break;
                case 4: removeTaskById(); break;
                case 5: sortTasksByPriority(); break;
                case 6: viewTasksByAddedOrder(); break;
                case 7: editTask(); break;
                case 8: viewAndMarkTasks(); break;
                case 9:
                    System.out.println("Exiting system...");
                    System.exit(0);
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Add Task");
        System.out.println("2. Undo Last Added Task");
        System.out.println("3. Discard First Available Task");
        System.out.println("4. Remove a Task ");
        System.out.println("5. View Tasks in Priority Order");
        System.out.println("6. View Tasks in Added Order");
        System.out.println("7. Edit Task");
        System.out.println("8. View and Mark Tasks as Complete");
        System.out.println("9. Exit");
    }

    private static void addTask() {
        System.out.println("\nAdd New Task");
        System.out.println("------------");

        String title = getStringInput();
        String priority = getPriorityInput();

        CWTask newTask = new CWTask(nextId++, title, priority);
        taskList.add(newTask);
        taskQueue.enqueue(newTask);
        undoStack.push(newTask);

        System.out.println("Successfully added the task!");
    }

    private static void undoLastAddedTask() {
        if (undoStack.isEmpty()) {
            System.out.println("There are currently no tasks available for undo.");
            return;
        }

        CWTask lastAdded = undoStack.peek();
        System.out.println("Most recently added task: " + lastAdded);

        System.out.print("Proceed with undoing this addition? (y/n): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("y")) {
            lastAdded = undoStack.pop();
            taskList.removeById(lastAdded.getId());

            CWQueue newQueue = new CWQueue();
            boolean found = false;
            CWTask[] tasks = taskQueue.getAllTasks();
            for (CWTask task : tasks) {
                if (task.getId() != lastAdded.getId()) {
                    newQueue.enqueue(task);
                } else {
                    found = true;
                }
            }

            if (found) {
                taskQueue = newQueue;
                System.out.println("Undo complete! Task has been removed. " + lastAdded);
            } else {
                System.out.println("Error: Task not found in queue during undo");
            }
        } else {
            System.out.println("Undo operation cancelled.");
        }
    }

    private static void discardFirstTask() {
        if (taskQueue.isEmpty()) {
            System.out.println("No tasks available to discard!");
            return;
        }

        CWTask nextTask = taskQueue.peek();
        System.out.println("Next task to be discarded: " + nextTask);

        System.out.print("Are you sure you want to discard this task? (y/n): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("y")) {
            CWTask discarded = taskQueue.dequeue();
            if (discarded != null) {
                boolean removed = taskList.removeById(discarded.getId());
                if (removed) {
                    System.out.println("Discarded task: " + discarded);
                } else {
                    System.out.println("Error: Task was dequeued but not found in main list");
                    taskQueue.enqueue(discarded);
                }
            }
        } else {
            System.out.println("Discard operation cancelled.");
        }
    }

    private static void removeTaskById() {
        if (taskList.isEmpty()) {
            System.out.println("No tasks available!");
            return;
        }

        int id = getIntInput("Enter the task ID to remove: ", nextId - 1);
        CWTask removed = taskList.getById(id);

        if (removed != null && taskList.removeById(id)) {
            // Remove from queue
            CWQueue newQueue = new CWQueue();
            CWTask[] tasks = taskQueue.getAllTasks();
            for (CWTask task : tasks) {
                if (task.getId() != id) {
                    newQueue.enqueue(task);
                }
            }
            taskQueue = newQueue;

            System.out.println("Task removed successfully: " + removed);
        } else {
            System.out.println("Task with ID " + id + " not found!");
        }
    }

    private static void sortTasksByPriority() {
        System.out.println("\nSorting tasks by priority (High > Medium > Low)...");
        taskList.bubbleSortByPriority();
        System.out.println("Tasks sorted successfully!");

        System.out.println("\nTasks in Priority Order:");
        System.out.println("-----------------------");
        CWTask[] tasks = taskList.getAllTasks();
        for (CWTask task : tasks) {
            System.out.println(task);
        }
    }

    private static void viewTasksByAddedOrder() {
        if (taskQueue.isEmpty()) {
            System.out.println("No tasks available!");
            return;
        }

        System.out.println("\nTasks in Added Order:");
        System.out.println("---------------------");
        CWTask[] tasks = taskQueue.getAllTasks();
        for (CWTask task : tasks) {
            System.out.println(task);
        }
    }

    private static void editTask() {
        if (taskList.isEmpty()) {
            System.out.println("No tasks available to edit!");
            return;
        }

        int id = getIntInput("Enter task ID to edit: ", nextId - 1);
        CWTask task = taskList.getById(id);

        if (task == null) {
            System.out.println("Task with ID " + id + " not found!");
            return;
        }

        System.out.println("Current task details: " + task);
        System.out.println("\nEdit Task (leave blank to keep current value)");

        String newTitle = getStringInput("Enter new title [" + task.getTitle() + "]: ", true);
        if (!newTitle.isEmpty()) {
            task.setTitle(newTitle);
        }

        String newPriority = getPriorityInput("Current priority [" + task.getPriority() + "]: ", true);
        if (!newPriority.isEmpty()) {
            task.setPriority(newPriority);
        }

        System.out.println("Task updated successfully: " + task);
    }

    private static void viewAndMarkTasks() {
        if (taskList.isEmpty()) {
            System.out.println("No tasks available!");
            return;
        }

        System.out.println("\nAll Tasks:");
        System.out.println("----------");
        CWTask[] tasks = taskList.getAllTasks();
        for (CWTask task : tasks) {
            System.out.println(task);
        }

        while (true) {
            System.out.print("\nEnter task ID to mark as complete (or 0 to go back): ");
            if (scanner.hasNextInt()) {
                int id = scanner.nextInt();
                scanner.nextLine();

                if (id == 0) break;

                CWTask task = taskList.getById(id);
                if (task == null) {
                    System.out.println("Invalid task ID!");
                } else if (task.isCompleted()) {
                    System.out.println("Task is already completed!");
                } else {
                    task.setCompleted(true);
                    System.out.println("Task marked as complete: " + task);
                }
            } else {
                System.out.println("Please enter a valid number!");
                scanner.nextLine();
            }
        }
    }

    private static int getIntInput(String prompt, int max) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                scanner.nextLine();
                if (input >= 1 && input <= max) {
                    return input;
                }
            } else {
                scanner.nextLine();
            }
            System.out.println("Invalid input! Please enter a number between " + 1 + " and " + max + ".");
        }
    }

    private static String getStringInput() {
        return getStringInput("Enter task title: ", false);
    }

    private static String getStringInput(String prompt, boolean allowBlank) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!allowBlank && input.isEmpty()) {
                System.out.println("Input cannot be empty!");
            } else {
                return input;
            }
        }
    }

    private static String getPriorityInput() {
        return getPriorityInput("Enter priority (low/medium/high): ", false);
    }

    private static String getPriorityInput(String prompt, boolean allowBlank) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (allowBlank && input.isEmpty()) {
                return input;
            }
            if (input.equals("low") || input.equals("medium") || input.equals("high")) {
                return input;
            }
            System.out.println("Invalid priority! Please enter 'low', 'medium', or 'high'.");
        }
    }
}