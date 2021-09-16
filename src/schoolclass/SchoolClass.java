package schoolclass;

import java.util.*;

public class SchoolClass {
    private String className;
    private List<Pupil> pupilsList;
    private List<Teacher> teachersList;

    public void setSubjectList(Set<Subject> subjectList) {
        this.subjectList.clear();
        this.subjectList.addAll(subjectList);
    }

    private HashSet<Subject> subjectList;


    public SchoolClass(String className) {
        this.className = className;
        pupilsList = new ArrayList<>();
        subjectList = new HashSet<>();
    }

    public void addSubject(Subject subject){
        subjectList.add(subject);
    }



    public HashSet<Subject> getSubjectList() {
        return subjectList;
    }

    public void addPupil(Pupil pupil) {
        pupilsList.add(pupil);
    }

    public void showPupils() {
        System.out.println("Pupils in classs " + className + ": ");
        for (Pupil pupil : pupilsList) {
            System.out.println(pupil.toString());
        }
    }

    /**
     * Metoda pozwalająca wstawiać do zbioru zestaw kilku obiektów
     *
     * @param objs
     * @return
     */
    public void setSubjectsList(Subject... objs) {
        subjectList.clear();
        Collections.addAll(subjectList, objs);
    }

    public void setSubjectsList(Set<Subject> set) {
        subjectList.clear();
        subjectList.addAll(set);
    }

    public void setPupilsList(Pupil... objs) {
        pupilsList.clear();
        Collections.addAll(pupilsList, objs);
    }

    public void adNote(Pupil pupil, String subjectName, double note) {
        Subject subject = getSubject(subjectName).get();
        Note newNote = new Note(subject, note);
        pupil.noteList.add(newNote);
    }
    public void adNote(Pupil pupil, Note note) {
        pupil.noteList.add(note);
    }

    public void showSubjects() {
        System.out.println(" Subjects in class " + className + " : ");
        for (Subject subject : subjectList) {
            System.out.println(subject);
        }
    }

    public void addTeachersToSubjects(List<Teacher> teachers) throws ClassExeption {
        for (Subject subject : subjectList) {
            if ( subject.getTeacher() != null)
                continue;
            boolean teacherFound = false;
            for (Teacher teacher : teachers) {
                for (String subjectName : teacher.subjects) {
                    if (subjectName.equals(subject.getName())) {
                        subject.setTeacher(teacher);
                        teacherFound = true;
                        break;
                    }
                }
                if (teacherFound == true)
                    break;
            }
            if (teacherFound == false)
                throw new ClassExeption ("Teacher for subject " + subject.getName() + " not found !" );
        }
    }

    public Optional<Subject> getSubject(String subjectName) {
        return subjectList.stream()
                .filter(o->o.getName() == subjectName)
                .findAny();
    }

    public Double averageNotesForPupil(Pupil pupil) {
        double averageNotes = 0;
        for(Note n : pupil.getNoteList()) {
            averageNotes += n.getNote();
        }
        return averageNotes / pupil.getNoteList().size();
    }

    public Double averageNotesForAllPupils(String subjectName) {
        OptionalDouble averageMark = pupilsList.stream()
                .flatMap(p -> p.getNoteList().stream())
                .mapToDouble(n -> n.getNote())
                .average();

        return averageMark.getAsDouble();
    }

    public Pupil pupilWithBiggestAverage() {
        double max = 0;
        Pupil pupilWithMaxAv = null;

        for (Pupil p :pupilsList) {
            double average = averageNotesForPupil(p);
            if (average > max) {
                max = average;
                pupilWithMaxAv = p;
            }
        }
        return pupilWithMaxAv;
    }

}