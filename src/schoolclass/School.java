package schoolclass;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class School {

   private List<SchoolClass> classes;
   private List<Teacher> teachers;
   private List<Subject> subjects;
   private  String schoolName;

   public School (String schoolName){
       this.schoolName = schoolName;
       teachers = new ArrayList<Teacher>();
       classes = new ArrayList<SchoolClass>();
       subjects = new ArrayList<Subject>();
   }

    @Override
    public String toString() {
        return "School{" +
                "classes=" + classes +
                ", teachers=" + teachers +
                ", subjects=" + subjects +
                ", schoolName='" + schoolName + '\'' +
                '}';
    }

    public void addSchoolClass (SchoolClass schoolClass){
       classes.add(schoolClass);
   }
   public void addTeacher (Teacher teacher){
       teachers.add(teacher);
   }
   public void addSubject (Subject subject){
       subjects.add(subject);
   }
   public void addSubjects (Subject ... newSubjects){
       Collections.addAll(subjects, newSubjects);
   }
   public void addSubjects (String ... newSubjectNames){
       for(String subjectName : newSubjectNames){
           subjects.add(new Subject(subjectName));
       }
   }

    /**
     * Dodawanie przedmiotu dla konkretnej klasy szkolnej
     */
   public void addSubjectForClass (String subjectName, SchoolClass schoolClass) throws ClassExeption{
       for(Subject subject : subjects){
           if(subject.getName().equals(subjectName)){
               schoolClass.addSubject(new Subject (subjectName));
               return;
           }
       }
       throw new ClassExeption ("No subject "+ subjectName +" in school");
   }

    public void showClasses (){
        System.out.println(" Classes in school " + schoolName + " : ");
        for (SchoolClass schoolClass : classes){
            System.out.println(schoolClass);
        }
   }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }
}
