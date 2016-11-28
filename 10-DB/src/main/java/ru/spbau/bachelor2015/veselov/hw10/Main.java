package ru.spbau.bachelor2015.veselov.hw10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Main class which implements interface of console application.
 */
public class Main {
    private static final int promptCommand = 8;

    private static final String prompt =
            "0 - quit\n" +
            "1 - add new entry\n" +
            "2 - find telephones by name\n" +
            "3 - find names by telephone\n" +
            "4 - delete entry name-telephone\n" +
            "5 - change name of name-telephone entry\n" +
            "6 - change telephone of name-telephone entry\n" +
            "7 - print all entries\n" +
            "8 - print this prompt";

    private static final String incorrectCommandMsg = "Incorrect command. Enter " + promptCommand + " to see prompt.";

    /**
     * Entry point of application.
     *
     * @param args no arguments required.
     * @throws IOException any IOException which can occur during IO interaction with user.
     */
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to console phone book!");
        System.out.println("Please be careful, each token is read until end of line.");
        System.out.println("Enter a command:");
        System.out.println(prompt);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            mainLoop: while (true) {
                String commandString = in.readLine();


                int command;

                try {
                    command = Integer.parseInt(commandString);
                } catch (NumberFormatException e) {
                    System.out.println(incorrectCommandMsg);
                    continue;
                }

                String name;
                String newName;
                String telephone;
                String newTelephone;
                switch (command) {
                    case 0:
                        break mainLoop;

                    case 1:
                        System.out.println("Enter <name> and <telephone> to add new entry.");

                        name = in.readLine();
                        telephone = in.readLine();
                        System.out.println("Unsupported command");
                        break;

                    case 2:
                        System.out.println("Enter <name> to find telephones of this person.");

                        name = in.readLine();
                        System.out.println("Unsupported command");
                        break;

                    case 3:
                        System.out.println("Enter <telephone> to find it's owners.");

                        telephone = in.readLine();
                        System.out.println("Unsupported command");
                        break;

                    case 4:
                        System.out.println("Enter <name> and <telephone> to delete entry.");

                        name = in.readLine();
                        telephone = in.readLine();
                        System.out.println("Unsupported command");
                        break;

                    case 5:
                        System.out.println("Enter <old name>, <telephone> and <new name> to change entry.");

                        name = in.readLine();
                        telephone = in.readLine();
                        newName = in.readLine();
                        System.out.println("Unsupported command");
                        break;

                    case 6:
                        System.out.println("Enter <name>, <old telephone> and <new telephone> to change entry.");

                        name = in.readLine();
                        telephone = in.readLine();
                        newTelephone = in.readLine();
                        System.out.println("Unsupported command");
                        break;

                    case 7:
                        System.out.println("Unsupported command");
                        break;

                    case 8:
                        System.out.println(prompt);
                        break;

                    default:
                        System.out.println(incorrectCommandMsg);
                }
            }
        }
    }
}
