package Lesson3;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class Person {
    private final String lastName;
    private final String firstName;

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Long getPhone() {
        return phone;
    }

    public Boolean getMale() {
        return isMale;
    }

    private final String secondName;
    private final LocalDate dateOfBirth;
    private final Long phone;
    private final Boolean isMale;

    public Person(String lastName, String firstName, String secondName, LocalDate dateOfBirth, Long phone, Boolean isMale) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondName = secondName;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.isMale = isMale;
    }

    private String getDateOfBirthString() {
        return String.format("%d.%d.%d", this.dateOfBirth.getDayOfMonth(),
                this.dateOfBirth.getMonthValue(), this.dateOfBirth.getYear());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(lastName, person.lastName) && Objects.equals(firstName, person.firstName) && Objects.equals(secondName, person.secondName) && Objects.equals(dateOfBirth, person.dateOfBirth) && Objects.equals(phone, person.phone) && Objects.equals(isMale, person.isMale);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, secondName, dateOfBirth, phone, isMale);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %d %s",
                this.lastName, this.firstName, this.secondName,
                this.getDateOfBirthString(), this.phone, this.isMale? 'm': 'f');
    }
}
