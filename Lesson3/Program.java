package Lesson3;
import Lesson3.MyExceptions.*;

import java.util.Scanner;

class Program {
    public static void main(String[] args) {
        // TODO: Delete newt row
        parse("Гарипов Азат Ильшатович 25.03.1996 89213009110 m".split(" "));
        /*
         * if (args.length > 0) {
         * parse(args);
         * } else {
         * print("Введите информацию о себе в формате:");
         * print("Фамилия Имя Отчество дд.мм.гггг *********** п");
         * print("где дд.мм.гггг - дата рождения, *********** - номер телефона,");
         * print("    п - пол (указывается m для мужчин и f для женщин):");
         * try (Scanner consoleInput = new Scanner(System.in)) {
         * parse(consoleInput.nextLine().split(" "));
         * }
         * }
         */
    }

    private static void parse(String[] args) {
        try {
            Parser myParser = new Parser(args);
            print(myParser.toString());
        }
        catch (MyIllegalArgsNumberException e) {
            print(String.format("Incorrect input: there were %d arguments received while expected %d.",
                    e.getActualNumber(), e.getExpectedNumber()));
        }
        
    }

    private static void print(String text) {
        System.out.println(text);
    }
}
