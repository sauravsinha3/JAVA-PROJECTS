import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Task class to represent individual tasks
class Task {
    private String description;
    private boolean isCompleted;
    private LocalDateTime creationTime;
    private LocalDateTime reminderTime;

    // Constructor to initialize a task
    public Task(String description, LocalDateTime reminderTime) {
        this.description = description;
        this.isCompleted = false;
        this.creationTime = LocalDateTime.now();
        this.reminderTime = reminderTime;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Getter for completion status
    public boolean isCompleted() {
        return isCompleted;
    }

    // Setter for completion status
    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    // Getter for creation time
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    // Getter for reminder time
    public LocalDateTime getReminderTime() {
        return reminderTime;
    }

    // Overriding the toString() method to provide a custom string representation of the task
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return (isCompleted ? "[Completed] " : "[Pending] ") + description +
                " (Created: " + creationTime.format(formatter) + ")" +
                " (Reminder: " + reminderTime.format(formatter) + ")";
    }
}

// ToDoList class to manage a list of tasks
class ToDoList {
    private List<Task> tasks;

    // Constructor to initialize the task list
    public ToDoList() {
        tasks = new ArrayList<>();
    }

    // Method to add a task to the list
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Task added: " + task.getDescription());
    }

    // Method to view all tasks in the list
    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    // Method to mark a task as completed
    public void markTaskAsCompleted(int taskNumber) {
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            tasks.get(taskNumber - 1).setCompleted(true);
            System.out.println("Task marked as completed.");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    // Method to delete a task from the list
    public void deleteTask(int taskNumber) {
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            tasks.remove(taskNumber - 1);
            System.out.println("Task deleted.");
        } else {
            System.out.println("Invalid task number.");
        }
    }
}

// Main class to run the To-Do List application
public class ToDoListApp {
    public static void main(String[] args) {
        ToDoList toDoList = new ToDoList();
        Scanner scanner = new Scanner(System.in);

        // Loop to display the menu and handle user input
        while (true) {
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Handle user choice using a switch statement
            switch (choice) {
                case 1:
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter reminder date and time (yyyy-MM-dd HH:mm): ");
                    String reminderDateTimeStr = scanner.nextLine();
                    LocalDateTime reminderDateTime = LocalDateTime.parse(reminderDateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    toDoList.addTask(new Task(description, reminderDateTime)); // Create and add a new task
                    break;
                case 2:
                    toDoList.viewTasks(); // View all tasks
                    break;
                case 3:
                    System.out.print("Enter task number to mark as completed: ");
                    int taskNumber = scanner.nextInt();
                    toDoList.markTaskAsCompleted(taskNumber); // Mark a task as completed
                    break;
                case 4:
                    System.out.print("Enter task number to delete: ");
                    int taskNum = scanner.nextInt();
                    toDoList.deleteTask(taskNum); // Delete a task
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close(); // Close the scanner
                    System.exit(0); // Exit the program
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}