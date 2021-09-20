package schoolclass;

/**
 * Abstrakcyjna klasa Person
 */
public abstract class Person {
    public String firstName;
    public String lastName;

    /**
     * Konstruktor klasy Person
     */
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Bezparametrowy konstruktor klasy Person
     */
    public Person() {
    }

    /**
     * Metoda zwraca imie osoby
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Metoda nadaje imie osobie
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Metoda zwraca nazwisko osoby
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Metoda nadaje nazwisko osobie
     * @param lastName
     */

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Metoda zwraca imie i nazwisko osoby
     * @return
     */
    @Override
    public String toString() {
        return firstName + "." + lastName;
    }
}
