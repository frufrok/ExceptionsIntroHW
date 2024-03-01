package Lesson3.MyExceptions;

public class MyIllegalDateFormatException extends IllegalArgumentException {
    
    public MyIllegalDateFormatException() {
        super("Date format is illegal.");
    }
}