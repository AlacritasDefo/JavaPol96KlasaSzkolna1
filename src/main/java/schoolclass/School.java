package schoolclass;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class School {

   private List<SchoolClass> classes;
   private List<Teacher> teachers;
   private static List<Subject> subjects;
   private String schoolName;
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
     * Ustalenie listy przedmiotow uczonych w szkole
     * @param subjectNames
     */
    public static void setSubjects(String... subjectNames) {
        subjects.clear();
        for(String subName : subjectNames) {
            boolean jest = false;
            for (Subject subject : subjects) {
                if (subject.getName().equals(subName)) {
                    jest = true;
                    break;
                }
            }
            if (jest == false)
                subjects.add(new Subject(subName));
        }
    }
    /**
     * Metoda pozwalająca wstawiać do zbioru zestaw kilku obiektów
     * @param objs
     * @return
     */
    public void setSubjectsList(Subject... objs) {
        subjects.clear();
        Collections.addAll(subjects, objs);
    }

    /**
     * Ustalenie listy przedmiotow uczonych w szkole
     * @param set
     */
    public void setSubjectsList(Set<Subject> set) {
        subjects.clear();
        subjects.addAll(set);
    }

    /**
     * Dodawanie przedmiotu dla konkretnej klasy szkolnej
     */
   public void addSubjectForClass (String subjectName, SchoolClass schoolClass) throws ClassException {
       for(Subject subject : subjects){
           if(subject.getName().equals(subjectName)){
               schoolClass.addSubject(new Subject (subjectName));
               return;
           }
       }
       throw new ClassException("No subject "+ subjectName +" in school");
   }

    /**
     * Wyswietlenie klas szkolnych w szkole
     */
    public void showClasses (){
        System.out.println(" Classes in school " + schoolName + " : ");
        for (SchoolClass schoolClass : classes){
            System.out.println(schoolClass);
        }
   }

    /**
     * Wyswietlenie informacji o przedmiotach uczonych w szkole
     */
    public void showSubjects() {
        System.out.println("SUBJECTS IN THE SCHOOL " + schoolName);
        for(Subject subject : subjects)
            System.out.println(subject.getName());
    }

    public static List<Subject> getSubjects() {
        return subjects;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void saveToFile(String fileName) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        Gson gson = builder.
                create();
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

    public static School loadFromFile(String fileName) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        Gson gson = gsonBuilder.create();
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

    public void showSchool() {
        System.out.println(this);
    }
}
