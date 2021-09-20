package schoolclass;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class School {

   private List<SchoolClass> classes;
   private List<Teacher> teachers;
   private List<Subject> subjects;
   private  String schoolName;
   public static School instance = null;

   private School (String schoolName){
       this.schoolName = schoolName;
       teachers = new ArrayList<Teacher>();
       classes = new ArrayList<SchoolClass>();
       subjects = new ArrayList<Subject>();
   }

    public static School getInstance(String schoolName) {
        if (instance == null) {
            instance = new School(schoolName);
        }
        return instance;
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

    public void saveToFile(String fileName) {
       //Gson gson = new Gson();
       Gson gson = new GsonBuilder().setPrettyPrinting().create();
       String text = gson.toJson(this);
       File file = new File(fileName);
        try {
            FileWriter wr = new FileWriter(file);
            wr.write(text);
            wr.close();
        }
        catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public School loadFromFile(String fileName) {
       Gson gson = new Gson();
       try {
           FileReader re = new FileReader(fileName);
           BufferedReader bu = new BufferedReader(re);
           String text = "";
           String line;
           while((line = bu.readLine()) != null) {
               text += line;
           }
           bu.close();
           re.close();
           System.out.println(text);
           School sc = gson.fromJson(text, School.class);
           return sc;
       }
       catch (IOException ioe) {
           System.out.println(ioe.getMessage());
       }
       return null;
    }
}
