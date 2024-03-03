package Lesson3;
import Lesson3.MyExceptions.*;

import java.io.IOException;
import java.util.*;

class Program {
    public static void main(String[] args) {
        Person person;
        if (args.length > 0) {
            person = parsePerson(args);
        } else {
            print("Введите информацию о себе в формате:");
            print("Фамилия Имя Отчество дд.мм.гггг *********** п");
            print("где дд.мм.гггг - дата рождения, *********** - номер телефона,");
            print("    п - пол (указывается m для мужчин и f для женщин):");
            Scanner consoleInput = new Scanner(System.in);
            person = parsePerson(consoleInput.nextLine().split(" "));
        }
        if (person != null) {
            writeToFile(person);
            print("Program termination.");
        }
    }

    private static Person parsePerson(String[] args) {
        print("Parsing input...");
        try {
            Parser myParser = new Parser(args);
            if (myParser.isValid()) {
                print("String was successfully parsed!");
                return myParser.getPerson();
            }
            else {
                print("Input data was incorrect. List of errors:");
                for (int i = 0; i < myParser.getExceptions().size(); i++) {
                    print(String.format("\t%d.) %s", i + 1, myParser.getExceptions().get(i).getMessage()));
                }
                return null;
            }
        }
        catch (MyIllegalArgsNumberException e) {
            print(String.format("Incorrect input: there were %d arguments received while expected %d.",
                    e.getActualNumber(), e.getExpectedNumber()));
            return null;
        }
    }

    private static String writeToFile(Person person) {
        print("Writing to file...");
        try {
            PersonToFileWriter personWriter = new PersonToFileWriter(person);
            if (!personWriter.getWarnings().isEmpty()) {
                print("There are some warnings. Please, read it before continue:");
                for (int i = 0; i < personWriter.getWarnings().size(); i++) {
                    print(personWriter.getWarnings().get(i));
                }
                print("Do you want to continue writing file? Enter 'y' to continue, 'n' to stop or 'f' to append new line without checking (all warnings will be ignored).");
                try (Scanner consoleInput = new Scanner(System.in)) {
                    while (true) {
                        String command = consoleInput.next();
                        if (command.equals("y")) {
                            personWriter.write(false);
                            print(String.format("File \"%s\" was successfully written!", personWriter.getPath()));
                            return personWriter.getPath();
                        }
                        else if (command.equals("f")) {
                            personWriter.write(true);
                            print(String.format("File \"%s\" was successfully written!", personWriter.getPath()));
                            return personWriter.getPath();
                        }
                        else if (command.equals("n")) {
                            print("Writing file was aborted.");
                            return null;
                        }
                        else {
                            print("Wrong key. Try again.");
                        }
                    }
                }
            }
            else {
                personWriter.write(false);
                print(String.format("File \"%s\" was successfully written!", personWriter.getPath()));
                return personWriter.getPath();
            }
        }
        catch (IOException e) {
            print(String.format("Error writing File. %s", e.getMessage()));
            return null;
        }
    }

    private static void print(String text) {
        System.out.println(text);
    }
}
