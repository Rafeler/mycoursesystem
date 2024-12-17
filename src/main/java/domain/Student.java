package domain;

import java.sql.Date;

public class Student extends BaseEntity {
    private String firstName;
    private String lastName;
    private Date birthDate;

    public Student(Long id, String firstName, String lastName, Date birthDate) throws InvalidValueException {
        super(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setBirthDate(birthDate);
    }

    public Student(String firstName, String lastName, Date birthDate) throws InvalidValueException {
        super(null);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setBirthDate(birthDate);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws InvalidValueException {
        if (firstName != null && !firstName.trim().isEmpty()) {
            this.firstName = firstName;
        } else {
            throw new InvalidValueException("Vorname darf nicht leer sein");
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws InvalidValueException {
        if (lastName != null && !lastName.trim().isEmpty()) {
            this.lastName = lastName;
        } else {
            throw new InvalidValueException("Nachname darf nicht leer sein");
        }
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) throws InvalidValueException {
        if (birthDate != null) {
            this.birthDate = birthDate;
        } else {
            throw new InvalidValueException("Geburtsdatum darf nicht null sein");
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + this.getId() +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
