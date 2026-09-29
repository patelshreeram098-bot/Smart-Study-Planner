public class StudyTask {

    private final int id;
    private final String title;
    private final String subject;
    private final String deadline;
    private final int priority;
    private boolean completed;

    public StudyTask(
            int id,
            String title,
            String subject,
            String deadline,
            int priority) {

        this.id = id;
        this.title = title;
        this.subject = subject;
        this.deadline = deadline;
        this.priority = priority;
        this.completed = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubject() {
        return subject;
    }

    public String getDeadline() {
        return deadline;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        completed = true;
    }

    public void displayTask() {
        System.out.println("--------------------------------");
        System.out.println("Task ID  : " + id);
        System.out.println("Task     : " + title);
        System.out.println("Subject  : " + subject);
        System.out.println("Deadline : " + deadline);
        System.out.println("Priority : " + priority);
        System.out.println("Status   : " + (completed ? "Completed" : "Pending"));
    }
}