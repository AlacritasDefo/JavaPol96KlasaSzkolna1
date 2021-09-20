package schoolclass;

import java.util.*;
import java.util.stream.Collectors;
import org.javatuples.Pair;

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
//        for (Pupil pupil : pupilsList) {
//            System.out.println(pupil.toString());
//        }
        SchoolClassIterator iterator = new SchoolClassIterator(pupilsList);
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
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

    public void addNote(Pupil pupil, String subjectName, double note) {
        Subject subject = getSubject(subjectName).get();
        if (pupil.getBonus() != null) {
            note += pupil.getBonus().up();
        }
        Note newNote = new Note(subject, note);
        pupil.noteList.add(newNote);
    }
    public void addNote(Pupil pupil, Note note) {
        if (pupil.getBonus() != null) {
            Note note1 = new Note(note.getSubject(), note.getNote() + pupil.getBonus().up());
            pupil.noteList.add(note1);
        } else {
            pupil.noteList.add(note);
        }
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

    public Set<Subject> showAbsentNotes(Pupil pupil) {
        Set<Subject> pupilSubject = pupil.getNoteList().stream()
                .map(Note::getSubject)
                .collect(Collectors.toSet());

        return subjectList.stream()
                .filter(subject -> !pupilSubject.contains(subject))
                .collect(Collectors.toSet());
    }

    public List<Pupil> showTheBestPupilIn(String subjectName, int count) {
        PupilAverageNoteForSubjectComparator comparator = new PupilAverageNoteForSubjectComparator(subjectName);
        return pupilsList.stream()
                .sorted(comparator)
                .limit(count)
                .collect(Collectors.toList());

    }

    public Pair<Pupil, Double> showTheLowestAverageNoteIn(String subjectName) {
        PairAverageComparator comparator = new PairAverageComparator();
        return pupilsList.stream()
                .map(pupil -> pupil.getPairForSubject(subjectName))
                .sorted(comparator)
                .limit(1)
                .collect(Collectors.toList())
                .get(0);
    }

    public Map<Integer, List<Pupil>> groupPupilsByYearOfBirth() {
        return pupilsList.stream()
                .collect(Collectors.groupingBy(pupil -> pupil.getBirthDate().getYear()));
    }

    public Map<Integer, List<Pupil>> groupPupilsByAverage() {
        return pupilsList.stream()
                .collect(Collectors.groupingBy(pupil -> (int) averageNotesForPupil(pupil).doubleValue()));
    }
}