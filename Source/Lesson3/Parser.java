package Lesson3;
import java.lang.RuntimeException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Lesson3.MyExceptions.*;

public class Parser {
    private final String lastName;
    private final String firstName;
    private final String secondName;
    private LocalDate dateOfBirth;
    private Long phone;
    private Boolean isMale;
    private final List<RuntimeException> exceptions = new ArrayList<>();

    public Parser(String[] args) throws MyIllegalArgsNumberException {
        if (args.length != 6) {
            throw new MyIllegalArgsNumberException(6, args.length);
        } else {
            this.lastName = args[0];
            this.firstName = args[1];
            this.secondName = args[2];
            try {
                this.dateOfBirth = getDateOfBirth(args[3]);
            } catch (RuntimeException e) {
                this.dateOfBirth = null;
                this.exceptions.add(e);
            }

            try {
                this.phone = getPhone(args[4]);
            } catch (RuntimeException e) {
                this.phone = null;
                this.exceptions.add(e);
            }

            try {
                this.isMale = getIsMale(args[5]);
            } catch (RuntimeException e) {
                this.isMale = null;
                this.exceptions.add(e);
            }
        }
    }

    public Parser(String oneLineArgs) {
        this(oneLineArgs.split(" "));
    }

    public Boolean isValid() {
        return this.exceptions.isEmpty();
    }

    public Person getPerson() {
        return this.isValid() ? new Person(this.lastName, this.firstName, this.secondName, this.dateOfBirth, this.phone, this.isMale) : null;
    }

    public List<Exception> getExceptions() {
        return new ArrayList<>(this.exceptions);
    }
    
    private LocalDate getDateOfBirth(String date) throws MyDateFormatException {
        try {
            int[] dateArray = Arrays.stream(date.split("\\.")).mapToInt(Integer::parseInt).toArray();
            if (dateArray.length == 3) {
                    return LocalDate.of(dateArray[2], dateArray[1], dateArray[0]);
            }
            else {
                throw new RuntimeException();
            }
        }
        catch (DateTimeException e) {
            throw new MyDateFormatException(e.getMessage());
        }
        catch (RuntimeException e) {
            throw new MyDateFormatException("dd.mm.yyyy", date);
        }
    }

    private boolean getIsMale(String sex) throws MyIllegalSexCharException {
        if (sex.toLowerCase().equals("m")) {
            return true;
        } else if (sex.toLowerCase().equals("f")) {
            return false;
        }
        else {
            throw new MyIllegalSexCharException(new Character[] {'m', 'f'}, sex);
        }
    }

    private Long getPhone(String phone) {
        try {
            long result = Long.parseLong(phone);
            if (result < 0) {
                throw new MyPhoneFormatException("Phone number can't be negative.");
            }
            else {
                if (String.format("%d", result).length() != 11) {
                    throw new MyPhoneFormatException("Phone number must contain 11 digits.");
                }
                else {
                    if (((long) (result/(Math.pow(10, 10))) != 8)) {
                        throw  new MyPhoneFormatException("Phone number must begin with the '8'.");
                    }
                    else {
                        return result;
                    }
                }
            }
        }
        catch (NumberFormatException e) {
            throw new MyPhoneFormatException(e.getMessage());
        }
    }

    
    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %s, %s", this.lastName,
                this.firstName, this.secondName,
                this.dateOfBirth != null ? this.dateOfBirth.toString() : "null",
                this.phone != null ? this.phone.toString() : "null",
                this.isMale != null? this.isMale.toString() : "null");
    }
}
