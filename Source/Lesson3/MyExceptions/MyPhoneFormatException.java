package Lesson3.MyExceptions;

public class MyPhoneFormatException extends IllegalArgumentException{
    public MyPhoneFormatException(String pattern, String actualValue) {
        super(String.format("Phone number \"%s\" not corresponds to pattern \"%s\".", actualValue, pattern));
    }
    public MyPhoneFormatException(String message) {
        super(String.format("Phone number is incorrect. %s", message));
    }
    public MyPhoneFormatException() {
        super("Phone number is incorrect.");
    }

}
