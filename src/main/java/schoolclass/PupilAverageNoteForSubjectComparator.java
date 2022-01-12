package schoolclass;

import java.util.Comparator;

public class PupilAverageNoteForSubjectComparator implements Comparator<Pupil> {
    private String subjectName;

    public PupilAverageNoteForSubjectComparator(String subjectName) {
        this.subjectName = subjectName;
    }
    @Override
    public int compare(Pupil o1, Pupil o2) {
        return (int) (o2.calculateAverageNote(subjectName) * 100 - o1.calculateAverageNote(subjectName) * 100);
    }
}
