package Lesson3.MyExceptions;

public class MyIllegalArgsNumberException extends IllegalArgumentException {
    private final int expectedNumber;
    private final int actualNumber;

    public MyIllegalArgsNumberException(int expectedNumber, int actualNumber, Exception e) {
        super(String.format("Method was called with %d args while expected %d.", actualNumber, expectedNumber), e);
        this.expectedNumber = expectedNumber;
        this.actualNumber = actualNumber;
    }

    public MyIllegalArgsNumberException(int expectedNumber, int actualNumber) {
        super(String.format("Method was called with %d args while expected %d.", actualNumber, expectedNumber));
        this.expectedNumber = expectedNumber;
        this.actualNumber = actualNumber;
    }

    public int getExpectedNumber() {
        return this.expectedNumber;
    }

    public int getActualNumber() {
        return this.actualNumber;
    }
}
