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
            "2 - find phones by name\n" +
            "3 - find names by phone\n" +
            "4 - delete entry name-phone\n" +
            "5 - change name of name-phone entry\n" +
            "6 - change phone of name-phone entry\n" +
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

        DataBase database = new DataBase("ru-spbau-bachelor2015-veselov-hw10");
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
                String phone;
                String newPhone;
                switch (command) {
                    case 0:
                        break mainLoop;

                    case 1:
                        System.out.println("Enter <name> and <phone> to add new entry.");

                        name = in.readLine();
                        phone = in.readLine();
                        if (database.addNewEntry(name, phone) == DataBase.Respond.ENTRY_ALREADY_EXISTS) {
                            System.out.println("Entry with such name and phone already exists.");
                        }

                        break;

                    case 2:
                        System.out.println("Enter <name> to find phones of this person.");

                        name = in.readLine();
                        for (PhoneBookEntry entry : database.findByName(name)) {
                            System.out.println(entry.getPhone());
                        }

                        break;

                    case 3:
                        System.out.println("Enter <phone> to find it's owners.");

                        phone = in.readLine();
                        for (PhoneBookEntry entry : database.findByPhone(phone)) {
                            System.out.println(entry.getName());
                        }

                        break;

                    case 4:
                        System.out.println("Enter <name> and <phone> to delete entry.");

                        name = in.readLine();
                        phone = in.readLine();
                        if (database.deleteEntry(name, phone) == DataBase.Respond.NO_SUCH_ENTRY) {
                            System.out.println("Entry with such name and phone doesn't exist.");
                        }

                        break;

                    case 5:
                        System.out.println("Enter <old name>, <phone> and <new name> to change entry.");

                        name = in.readLine();
                        phone = in.readLine();
                        newName = in.readLine();
                        switch (database.changeName(name, phone, newName)) {
                            case NO_SUCH_ENTRY:
                                System.out.println("Entry with such name and phone doesn't exist.");
                                break;

                            case ENTRY_ALREADY_EXISTS:
                                System.out.println("Entry with given new name and phone already exists.");
                                break;

                            default:
                                break;
                        }

                        break;

                    case 6:
                        System.out.println("Enter <name>, <old phone> and <new phone> to change entry.");

                        name = in.readLine();
                        phone = in.readLine();
                        newPhone = in.readLine();
                        switch (database.changePhone(name, phone, newPhone)) {
                            case NO_SUCH_ENTRY:
                                System.out.println("Entry with such name and phone doesn't exist.");
                                break;

                            case ENTRY_ALREADY_EXISTS:
                                System.out.println("Entry with given name and new phone already exists.");
                                break;

                            default:
                                break;
                        }

                        break;

                    case 7:
                        for (PhoneBookEntry entry : database.getAllEntries()) {
                            System.out.println("name: " + entry.getName() + ", phone: " + entry.getPhone());
                        }

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
