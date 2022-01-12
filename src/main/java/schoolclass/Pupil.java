package schoolclass;

import org.javatuples.Pair;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pupil extends Person {
    private LocalDate birthDate;
    List<Note> noteList;
    Set<BONUS_BAHAVIOUR> behaviours;
    //TODO: ustawienie 'transient' dla pola bonus powoduje, ze serializator Gson nie widzi tego pola
    private transient IBonus bonus;
    /**
     * Dodanie uczniowi ponadstandardowego zachowania (np. bohaterski czyn, udzial w olimpiadzie
     * @param behaviour
     */
    public void addBehaviour(BONUS_BAHAVIOUR behaviour) {
        behaviours.add(behaviour);
        if (behaviours.contains(BONUS_BAHAVIOUR.HERO))
            setBonus(new BehaviorBonus());
    }
    /**
     * Konstruktor klasy Pupil
     * @param firstName
     * @param lastName
     * @param birthDate
     */
    public Pupil(String firstName, String lastName, LocalDate birthDate) {
        super(firstName, lastName);
        this.birthDate = birthDate;
        noteList = new ArrayList<>();
        behaviours = new HashSet<>();
    }

    /**
     * Dodanie oceny uczniowi
     * @param newNote
     */
    public void addNote(Note newNote) {
        noteList.add(newNote);

    }

    /**
     * Pobranie daty urodzenia ucznia
     * @return
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Pobranie ocen ucznia (wszystkich)
     * @return
     */
    public List<Note> getNoteList() {
        return noteList;
    }

    /**
     * Pobranie ocen ucznia z danego przedmiotu
     * @param subjectName
     * @return
     */
    public List<Note> getNoteList(String subjectName) {
        List<Note> subjectNotes = new ArrayList<Note>();
        for (Note note : noteList) {
            if (note.getSubject().getName().equals(subjectName)) {
                subjectNotes.add(note);
            }
        }
        return subjectNotes;
    }

    /**
     * Obliczenie sredniej oceny ucznia
     * @return
     */
    public double averageNotes() {
        return noteList.stream()
                .mapToDouble(Note::getNote)
                .average().getAsDouble();
    }

    /**
     * Obliczenie sredniej oceny ucznia z danego przedmiotu
     * @param subjectName
     * @return
     */
    public double calculateAverageNote(String subjectName) {
        return noteList.stream()
                .filter(note -> note.getSubject().getName().equals(subjectName))
                .mapToDouble(Note::getNote)
                .average().getAsDouble();
    }

    /**
     * Wyswietlenie sredniej oceny ucznia z danego przedmiotu
     * @param subjectName
     */
    public void showAverageNoteForSubject(String subjectName) {
        System.out.printf("Średnia ocena z %s dla %s %s - %.2f%n", subjectName, firstName, lastName, calculateAverageNote(subjectName));
    }

    /**
     * Zwrocenie pary: pierwszy element to uczen, a drugi element to srednia ocena ucznia
     * @param subjectName
     * @return
     */
    public Pair<Pupil, Double> getPairForSubject(String subjectName) {
        return new Pair<>(this, calculateAverageNote(subjectName));
    }

    /**
     * Pobranie premii dla ucznia
     * @return
     */
    public IBonus getBonus() {
        return bonus;
    }

    /**
     * Ustalenie premii dla ucznia
     * @param bonus
     */
    public void setBonus(IBonus bonus) {
        this.bonus = bonus;
    }

    /**
     * Wyswietlenie danych ucznia
     * @return
     */
    @Override
    public String toString() {
        return "Pupil{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", noteList=" + noteList +
                '}';
    }
}