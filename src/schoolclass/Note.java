package schoolclass;

public class Note {
    private Subject subject;
    private double note;

    public Note(Subject subject, double note) {
        this.subject = subject;
        this.note = note;
    }

    public Subject getSubject() {
        return subject;
    }

    public double getNote() {
        return note;
    }
}