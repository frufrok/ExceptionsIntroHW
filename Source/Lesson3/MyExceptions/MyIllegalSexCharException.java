package Lesson3.MyExceptions;

import java.util.ArrayList;

public class MyIllegalSexCharException extends IllegalArgumentException {
    public MyIllegalSexCharException(Character[] acceptableValues, String actualValue) {
        super(String.format("Sex char '%s' is illegal. Acceptable values are: %s.", actualValue, toString(acceptableValues, ", ")));
    }
    public MyIllegalSexCharException() {
        super("Sex char is illegal.");
    }

    private static String toString(Character[] values, String separator) {
        if (values.length > 0) {
            StringBuilder result = new StringBuilder();
            result.append(values[0]);
            for (int i = 1; i < values.length; i++ ){
                result.append(separator);
                result.append(values[i]);
            }
            return result.toString();
        }
        else  {
            return "";
        }
    }
}
