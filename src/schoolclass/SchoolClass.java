package schoolclass;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SchoolClass {
    private String className;
    private List<Pupil> pupilList;
    private List<Teacher> teacherList;
    private HashSet<Subject> subjectList;
    private static SchoolClass instance = null;

    private SchoolClass(String className) {
        this.className = className;
        pupilList = new ArrayList<>();
        teacherList = new ArrayList<>();
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
    public void addPupil (Pupil pupil){
        pupilList.add(pupil);
    }
    public void showPupils(){
        System.out.println("Pupils in classs " + className + ": "  );
        for (Pupil pupil : pupilList){
            System.out.println(pupil.toString());
        }
    }

}
