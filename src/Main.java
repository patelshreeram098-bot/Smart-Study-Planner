import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    private static final Path FILE_PATH = Path.of("tasks.txt");

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

            ArrayList<StudyTask> tasks = loadTasks();

            System.out.println("Loaded " + tasks.size() + " task(s).");

            boolean running = true;

            while (running) {

                System.out.println("\n===== SMART STUDY PLANNER =====");
                System.out.println("1. Add Study Task");
                System.out.println("2. View All Tasks");
                System.out.println("3. Search Task");
                System.out.println("4. Sort Tasks");
                System.out.println("5. Mark Task Completed");
                System.out.println("6. Delete Task");
                System.out.println("7. Study Statistics");
                System.out.println("8. Save Tasks");
                System.out.println("9. Exit");

                System.out.print("Enter your choice: ");

                int choice;

                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                    continue;
                }

                switch (choice) {

                    case 1 -> addTask(scanner, tasks);

                    case 2 -> viewTasks(tasks);

                    case 3 -> searchTask(scanner, tasks);

                    case 4 -> sortTasks(scanner, tasks);

                    case 5 -> markCompleted(scanner, tasks);

                    case 6 -> deleteTask(scanner, tasks);

                    case 7 -> showStatistics(tasks);

                    case 8 -> saveTasks(tasks);

                    case 9 -> {
                        saveTasks(tasks);
                        running = false;
                        System.out.println("Tasks saved.");
                        System.out.println("Exiting Smart Study Planner...");
                    }

                    default -> System.out.println("Invalid choice. Try again.");
                }
            }
        }
    }

    private static void addTask(
            Scanner scanner,
            ArrayList<StudyTask> tasks) {

        System.out.println("\n===== ADD STUDY TASK =====");

        int id = getNextId(tasks);

        System.out.print("Enter task title: ");
        String title = scanner.nextLine();

        System.out.print("Enter subject: ");
        String subject = scanner.nextLine();

        System.out.print("Enter deadline (YYYY-MM-DD): ");
        String deadline = scanner.nextLine();

        int priority;

        while (true) {
            System.out.print("Enter priority (1-5): ");

            try {
                priority = Integer.parseInt(scanner.nextLine());

                if (priority >= 1 && priority <= 5) {
                    break;
                }

                System.out.println("Priority must be between 1 and 5.");

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        StudyTask task =
                new StudyTask(
                        id,
                        title,
                        subject,
                        deadline,
                        priority
                );

        tasks.add(task);

        System.out.println("Task added successfully.");
        System.out.println("Task ID: " + id);
    }

    private static void viewTasks(ArrayList<StudyTask> tasks) {

        System.out.println("\n===== ALL STUDY TASKS =====");

        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        for (StudyTask task : tasks) {
            task.displayTask();
        }
    }

    private static void searchTask(
            Scanner scanner,
            ArrayList<StudyTask> tasks) {

        System.out.println("\n===== SEARCH TASK =====");

        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        System.out.print("Enter task title to search: ");
        String search = scanner.nextLine().toLowerCase();

        boolean found = false;

        for (StudyTask task : tasks) {

            if (task.getTitle().toLowerCase().contains(search)) {
                task.displayTask();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching task found.");
        }
    }

    private static void sortTasks(
            Scanner scanner,
            ArrayList<StudyTask> tasks) {

        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        System.out.println("\n===== SORT TASKS =====");
        System.out.println("1. Sort by Deadline");
        System.out.println("2. Sort by Priority");

        System.out.print("Enter choice: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1 -> {
                    tasks.sort(
                            Comparator.comparing(StudyTask::getDeadline)
                    );

                    System.out.println("Tasks sorted by deadline.");
                }

                case 2 -> {
                    tasks.sort(
                            Comparator.comparingInt(StudyTask::getPriority)
                    );

                    System.out.println("Tasks sorted by priority.");
                }

                default -> System.out.println("Invalid choice.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    private static void markCompleted(
            Scanner scanner,
            ArrayList<StudyTask> tasks) {

        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        viewTasks(tasks);

        System.out.print("\nEnter Task ID to mark completed: ");

        try {
            int id = Integer.parseInt(scanner.nextLine());

            StudyTask task = findTaskById(tasks, id);

            if (task == null) {
                System.out.println("Invalid Task ID.");
                return;
            }

            if (task.isCompleted()) {
                System.out.println("Task is already completed.");
            } else {
                task.markCompleted();
                System.out.println("Task marked as completed.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    private static void deleteTask(
            Scanner scanner,
            ArrayList<StudyTask> tasks) {

        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        viewTasks(tasks);

        System.out.print("\nEnter Task ID to delete: ");

        try {
            int id = Integer.parseInt(scanner.nextLine());

            StudyTask task = findTaskById(tasks, id);

            if (task == null) {
                System.out.println("Invalid Task ID.");
                return;
            }

            tasks.remove(task);

            System.out.println(
                    "Deleted task: " + task.getTitle()
            );

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    private static void showStatistics(
            ArrayList<StudyTask> tasks) {

        System.out.println("\n===== STUDY STATISTICS =====");

        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        int completed = 0;
        int pending = 0;

        for (StudyTask task : tasks) {

            if (task.isCompleted()) {
                completed++;
            } else {
                pending++;
            }
        }

        double completionRate =
                (completed * 100.0) / tasks.size();

        System.out.println("Total Tasks      : " + tasks.size());
        System.out.println("Completed Tasks  : " + completed);
        System.out.println("Pending Tasks    : " + pending);
        System.out.printf(
                "Completion Rate  : %.1f%%%n",
                completionRate
        );
    }

    private static void saveTasks(ArrayList<StudyTask> tasks) {

        try (BufferedWriter writer = Files.newBufferedWriter(
                FILE_PATH,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING)) {

            for (StudyTask task : tasks) {

                writer.write(
                        task.getId() + "|" +
                        escape(task.getTitle()) + "|" +
                        escape(task.getSubject()) + "|" +
                        escape(task.getDeadline()) + "|" +
                        task.getPriority() + "|" +
                        task.isCompleted()
                );

                writer.newLine();
            }

            System.out.println("Tasks saved successfully.");

        } catch (IOException e) {
            System.out.println(
                    "Error saving tasks: " + e.getMessage()
            );
        }
    }

    private static ArrayList<StudyTask> loadTasks() {

        ArrayList<StudyTask> tasks = new ArrayList<>();

        if (!Files.exists(FILE_PATH)) {
            return tasks;
        }

        try (BufferedReader reader =
                     Files.newBufferedReader(FILE_PATH)) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|", -1);

                if (data.length != 6) {
                    continue;
                }

                int id = Integer.parseInt(data[0]);
                String title = unescape(data[1]);
                String subject = unescape(data[2]);
                String deadline = unescape(data[3]);
                int priority = Integer.parseInt(data[4]);
                boolean completed =
                        Boolean.parseBoolean(data[5]);

                StudyTask task =
                        new StudyTask(
                                id,
                                title,
                                subject,
                                deadline,
                                priority
                        );

                if (completed) {
                    task.markCompleted();
                }

                tasks.add(task);
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println(
                    "Error loading tasks: " + e.getMessage()
            );
        }

        return tasks;
    }

    private static int getNextId(
            ArrayList<StudyTask> tasks) {

        int maxId = 0;

        for (StudyTask task : tasks) {
            if (task.getId() > maxId) {
                maxId = task.getId();
            }
        }

        return maxId + 1;
    }

    private static StudyTask findTaskById(
            ArrayList<StudyTask> tasks,
            int id) {

        for (StudyTask task : tasks) {

            if (task.getId() == id) {
                return task;
            }
        }

        return null;
    }

    private static String escape(String value) {
        return value.replace("|", "\\|");
    }

    private static String unescape(String value) {
        return value.replace("\\|", "|");
    }
}