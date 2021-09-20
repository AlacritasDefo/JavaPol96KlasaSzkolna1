package schoolclass;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Teacher teacher1 = new Teacher("Jan", "Kowalski");
        teacher1.addSubject("Fizyka");
        teacher1.addSubject("Chemia");
        teacher1.addSubject("Historia");
        Pupil pupil1 = new Pupil("Kornelia", "Matuszak", LocalDate.of(1994, 01, 18));
        Pupil pupil2 = new Pupil("Amelia", "Domańska", LocalDate.of(1998, 06, 28));


        System.out.println(teacher1);
        System.out.println(pupil1);
        SchoolClass klasa = new SchoolClass("Klasa IVa");
        //  klasa.addSubject("Fizyka");
        //  klasa.addSubject("Chemia");
        //  klasa.addSubject("Fizyka");
        //  System.out.println(klasa.getSubjectList());
        //
        //  klasa.setSubjectList(new HashSet<Subject>() {{
        //          add(new Subject("Język polski"));
        //          add(new Subject("Programowanie w Javie"));
        //  }}
        //  );
        //  System.out.println(klasa.getSubjectList());
//
        //  klasa.setSubjectsList(new Subject("Chemia"), new Subject("Język angielski"), new Subject("Programowanie w C++"));
        //  System.out.println(klasa.getSubjectList());
//
        Subject s1 = new Subject("Chemia");
        Subject s2 = new Subject("Fizyka");
        Subject s3 = new Subject("Historia");


        klasa.addPupil(pupil1);
        klasa.addPupil(pupil2);
        klasa.showPupils();
        School school = School.getInstance("Szkoła Podstawowa nr 1");
        school.addSchoolClass(klasa);
        school.addTeacher(teacher1);
        school.addSubjects("Język angielski", "Plastyka", "Technika", "Fizyka", "Chemia", "Historia");
        try {
            school.addSubjectForClass("Chemia", klasa);
            school.addSubjectForClass("Fizyka", klasa);
            school.addSubjectForClass("Historia", klasa);
            klasa.addTeachersToSubjects(school.getTeachers());
        } catch (ClassExeption classExeption) {
            System.out.println(classExeption.getMessage());
            System.exit(-1);
        }

        Subject chemia = klasa.getSubject("Chemia").get();
        Subject fizyka = klasa.getSubject("Fizyka").get();
        Note note1 = new Note(chemia, 5);
        Note note2 = new Note(fizyka, 4);
        Note note3 = new Note(chemia, 3);

        Note note4 = new Note(chemia, 3);
        Note note5 = new Note(fizyka, 5);
        Note note6 = new Note(chemia, 2);


        klasa.adNote(pupil1, note1);
        klasa.adNote(pupil1, note2);
        klasa.adNote(pupil1, note3);

        klasa.adNote(pupil2, note4);
        klasa.adNote(pupil2, note5);
        klasa.adNote(pupil2, note6);

        klasa.adNote(pupil1, "Chemia", 5);
        System.out.println(pupil1);

//        System.out.println("Average notes amount for pupil: " + pupil1.getFirstName() +" " + pupil1.getLastName()+ " = " + klasa.averageNotesForPupil(pupil1));
//
//        System.out.format("Average notes for the subject chemia %.2f %n", klasa.averageNotesForAllPupils("Chemia"));
//        System.out.println("Pupil with the highest average: " +  klasa.pupilWithBiggestAverage());

        System.out.println(klasa.showAbsentNotes(pupil1));
        klasa.showTheBestPupilIn("Fizyka", 2).forEach(pupil -> pupil.showAverageNoteForSubject("Fizyka"));

        System.out.println("The lowest average note in Fizyka " + klasa.showTheLowestAverageNoteIn("Fizyka"));

        Map<Integer, List<Pupil>> pupilsGroupByYear = klasa.groupPupilsByYearOfBirth();
        for (Map.Entry<Integer, List<Pupil>> entry : pupilsGroupByYear.entrySet()) {
            System.out.println("Birth in " + entry.getKey());
            for (Pupil pupil : entry.getValue()) {
                System.out.println(pupil.getFirstName() + " " + pupil.getLastName());
            }
        }

        Map<Integer, List<Pupil>> pupilsGroupByAverage = klasa.groupPupilsByAverage();
        for (Map.Entry<Integer, List<Pupil>> entry : pupilsGroupByAverage.entrySet()) {
            System.out.println("Average " + entry.getKey());
            for (Pupil pupil : entry.getValue()) {
                System.out.println(pupil.getFirstName() + " " + pupil.getLastName());
            }
        }

        klasa.showPupils();
        school.saveToFile("school.json");
        school = school.loadFromFile("school.json");
        System.out.println("OUR SCHOOL RETRIEVED FROM JSON FILE:");
        System.out.println(school);
    }

}
