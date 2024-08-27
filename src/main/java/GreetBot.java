import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class GreetBot {
    public static void main(String[] args) {
        new GreetBot().run();
    }

    /* solution below inspired by main function in:
    https://github.com/Wincenttjoi/CS2103T-duke-chatbot/blob/master/src/main/java/duke/Duke.java
    */
    private void run() {

        String filePath = "data/greetbot.txt";

        try {
            File storage = new File(filePath);
            Scanner fileScanner = new Scanner(storage);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No such file");
            e.printStackTrace();
            System.exit(0);
        }

        System.out.println("Hello! I'm GreetBot");
        System.out.println("What can I do for you?");
        Scanner scanner = new Scanner(System.in);

        ArrayList<Task> list = new ArrayList<>();

        while (scanner.hasNext()) {

            String currentCommand = scanner.nextLine();

            if (currentCommand.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (currentCommand.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(String.format("%d.%s", i + 1, list.get(i)));
                }
            } else if (currentCommand.startsWith("mark")) {
                int index = Integer.parseInt(currentCommand.substring(currentCommand.indexOf(" ") + 1));
                list.get(index - 1).mark();
            } else if (currentCommand.startsWith("unmark")) {
                int index = Integer.parseInt(currentCommand.substring(currentCommand.indexOf(" ") + 1));
                list.get(index - 1).unmark();
            } else {

                try {
                    Task.decideTask(currentCommand, list);

                    System.out.println(String.format("Now you have %s tasks in the list.", list.size()));
                } catch (EmptyDescriptionException e) {
                    System.out.println(e.getMessage());
                } catch (RandomInputException e) {
                    System.out.println(e.getMessage());
                }
            }


        }



        scanner.close();

    }
}
