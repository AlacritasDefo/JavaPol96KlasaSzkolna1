package schoolclass;

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

    /*
    public void addNote(Note note) {
        noteList.add(note);
    }
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

    @Override
    public String toString() {
        return "Pupil{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}