package schoolclass;
/*
firstName (imię),
- lastName (nazwisko),
- getterami do pól
- metoda toString() : imię + nazwisko
 */
public abstract class Person {
    public String firstName;

    public Person() {
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String lastName;
    @Override
    public String toString() {
        return firstName + "." + lastName;
    }
}
