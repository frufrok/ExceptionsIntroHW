package Lesson3;
import java.lang.RuntimeException;
import java.sql.Date;
import java.util.List;

import Lesson3.MyExceptions.*;

public class Parser {
    private final String lastName;
    private final String firstName;
    private final String secondName;
    private Date dateOfBirth;
    private Long phone;
    private Boolean isMale;

    public List<RuntimeException> exceptions;

    public Parser(String[] args) throws MyIllegalArgsNumberException {
        if (args.length != 6) {
            throw new MyIllegalArgsNumberException(6, args.length);
        } else {
            this.lastName = args[0];
            this.firstName = args[1];
            this.secondName = args[2];
            try {
                this.dateOfBirth = getDateOfBirth(args[3]);
            } catch (MyIllegalDateFormatException e) {
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

    public Boolean isValid() {
        return this.exceptions.size() == 0 ? true : false;
    }
    
    private Date getDateOfBirth(String date) throws MyIllegalDateFormatException {
        return new Date(1);
    }

    private Boolean getIsMale(String sex) {
        // TODO: do some logic
        return true;
    }

    private Long getPhone(String phone) {
        // TODO: do some logic
        return null;
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
