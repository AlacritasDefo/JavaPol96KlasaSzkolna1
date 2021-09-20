package schoolclass;

import org.javatuples.Pair;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pupil extends Person {
    private LocalDate birthDate;
    List<Note> noteList;

    public Pupil(String firstName, String lastName, LocalDate birthDate) {
        super(firstName, lastName);
        this.birthDate = birthDate;
        noteList = new ArrayList<>();
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public List<Note> getNoteList() {
        return noteList;
    }

    public List<Note> getNoteList(String subjectName) {
        List<Note> subjectNotes = new ArrayList<Note>();
        for (Note note : noteList) {
            if (note.getSubject().getName().equals(subjectName)) {
                subjectNotes.add(note);
            }

        }
        return subjectNotes;
    }

    public double calculateAverageNote(String subjectName) {
        return noteList.stream()
                .filter(note -> note.getSubject().getName().equals(subjectName))
                .mapToDouble(Note::getNote)
                .average().getAsDouble();
    }

    public void showAverageNoteForSubject(String subjectName) {
        System.out.printf("Średnia ocena z %s dla %s %s - %.2f%n", subjectName, firstName, lastName, calculateAverageNote(subjectName));
    }

    public Pair<Pupil, Double> getPairForSubject(String subjectName) {
        return new Pair<>(this, calculateAverageNote(subjectName));
    }

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