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
    private static SchoolClass instance = null;

    private SchoolClass(String className) {
        this.className = className;
        pupilsList = new ArrayList<>();
        subjectList = new HashSet<>();
    }

    public static SchoolClass getInstance(String className) {
        if (instance == null) {
            instance = new SchoolClass(className);
        }
        return instance;
    }

    public void addSubject(String subjectName) {
        for (Subject subject : subjectList) {
            if (subject.getName().equals(subjectName)) {
                return;
            }
        }
        subjectList.add(new Subject(subjectName));
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
        Note newNote = new Note(new Subject(subjectName), note);
        pupil.noteList.add(newNote);
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
                throw new ClassExeption ("Teacher for subject" + subject.getName() + " not found !" );
        }
    }
}