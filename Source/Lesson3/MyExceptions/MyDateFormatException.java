package Lesson3.MyExceptions;

public class MyDateFormatException extends IllegalArgumentException {
    public MyDateFormatException(String pattern, String actualValue) {
        super(String.format("Date value \"%s\" not corresponds to pattern \"%s\".", actualValue, pattern));
    }
    public MyDateFormatException(String message) {
        super(String.format("Date value is incorrect. %s", message));
    }
    public MyDateFormatException() {
        super("Date format is incorrect.");
    }
}