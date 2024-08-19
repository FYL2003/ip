public class Task {
    protected String description;
    protected boolean isDone;

    public static Task decideTask(String type, String rest) {
        if (type.equals("deadline")) {
            return new Task("test");
        } else if (type.equals("todo")) {
            return new Todo(rest);
        } else {
            return new Task("test");
        }
    }

    private Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.toString());
    }

    public void unmark(){
        this.isDone = false;
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(this.toString());
    }
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", (isDone ? "X" : " "), this.description);
    }

    /* the subclass of Task */
    private static class Deadline extends Task {
        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }
    }

    private static class Todo extends Task {

        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    private static class Event extends Task {
        protected String by;

        public Event(String description, String by) {
            super(description);
            this.by = by;
        }
    }

}
