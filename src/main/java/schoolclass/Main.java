package schoolclass;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        School szkola = School.getInstance("Liceum ogolnokształcące nr 5");
        szkola.setSubjects(
                "Astronomia",
                "Chemia",
                "Fizyka",
                "Historia",
                "Geografia",
                "Informatyka",
                "Religia",
                "Matematyka",
                "Język polski",
                "Język angielski",
                "Język niemiecki",
                "Język francuski",
                "Programowanie w C++",
                "Wychowanie fizyczne"
        );
        szkola.showSubjects();
        Teacher teacher1 = new Teacher("Jan", "Kowalski");
        teacher1.addSubject("Fizyka");
        teacher1.addSubject("Chemia");
        teacher1.addSubject("Matematyka");
        Teacher teacher2 = new Teacher("Aleksandra", "Nowicka");
        teacher2.addSubjects("Język polski", "Język angielski", "Wychowanie fizyczne");
        Teacher teacher3 = new Teacher("Maria", "Berek");
        teacher3.addSubjects("Historia", "Geografia");
        Teacher teacher4 = new Teacher("Maria", "Berek");
        teacher4.addSubjects("Język angielski", "Wychowanie fizyczne", "Religia");
        szkola.addTeacher(teacher1);
        szkola.addTeacher(teacher2);
        szkola.addTeacher(teacher3);
        szkola.addTeacher(teacher4);
        Pupil pupil1 = new Pupil("Kornelia", "Matuszak", LocalDate.of(1994, 1, 18));
        Pupil pupil2 = new Pupil("Janusz", "Rybacki", LocalDate.of(1984, 7, 8));
        Pupil pupil3 = new Pupil("Zofia", "Ananas", LocalDate.of(1978, 5, 28));
        Pupil pupil4 = new Pupil("Ewa", "Muzaj", LocalDate.of(2001, 12, 14));
        Pupil pupil5 = new Pupil("Rafał", "Wagner", LocalDate.of(1992, 11, 7));
        Pupil pupil6 = new Pupil("Wanda", "Mitoraj", LocalDate.of(1999, 3, 13));
        Pupil pupil7 = new Pupil("Józef", "Bosek", LocalDate.of(2001, 5, 29));
        Pupil pupil8 = new Pupil("Anna", "Cieślak", LocalDate.of(1999, 7, 21));
        Pupil pupil9 = new Pupil("Robert", "Hulewicz", LocalDate.of(2004, 11, 11));
        Pupil pupil10 = new Pupil("Grzegorz", "Paciorek", LocalDate.of(1982, 8, 3));

        System.out.println(teacher1);
        System.out.println(teacher2);
        System.out.println(teacher3);
        System.out.println(pupil1);

        SchoolClass klasa1 = new SchoolClass("Klasa 4a");
        SchoolClass klasa2 = new SchoolClass("Klasa 5b");
        try {
            klasa1.addSubjectForClass("Fizyka");
            klasa1.addSubjectForClass("Chemia");
            klasa1.addSubjectForClass("Fizyka");
            klasa1.addSubjectForClass("Historia");
            klasa2.addSubjectForClass("Fizyka");
            klasa2.addSubjectForClass("Chemia");
            klasa2.addSubjectForClass("Matematyka");
            klasa2.addSubjectForClass("Historia");
            klasa2.addSubjectForClass("Język polski");
            // Przydzielenie nauczycieli do przedmiotow uczonych w klasie szkolnej klasa1
            klasa1.addTeachersToSubjects(szkola.getTeachers());
        }
        catch(ClassException ce) {
            System.out.println(ce.getMessage());
            System.exit(1);
        }
        System.out.println(klasa1.getSubjectList());

        klasa1.setSubjectList(new HashSet<Subject>() {{
                                  add(new Subject("Język polski"));
                                  add(new Subject("Programowanie w Javie"));
                              }}
        );
        System.out.println(klasa1.getSubjectList());

        klasa1.setSubjectsList(
                new Subject("Chemia"),
                new Subject("Fizyka"),
                new Subject("Historia"),
                new Subject("Matematyka")
        );
        System.out.println(klasa1.getSubjectList());
        klasa1.addPupil(pupil1);
        klasa1.addPupils(pupil2, pupil3, pupil4, pupil5);
        klasa1.showPupils();
        klasa2.addPupils(pupil6, pupil7, pupil8, pupil9, pupil10);
        Iterator iter = klasa1.iterator();
        System.out.println("=== ITERATOR ===");
        while (iter.hasNext())
            System.out.println(iter.next().toString());
        klasa1.showPupilsSortedByName();
        szkola.addSchoolClass(klasa1);
        //Wyswietlenie informacji o szkole
        szkola.showSchool();
        szkola.saveToFile("szkoła.json");

        // Dodanie ocen dla uczniow
        klasa1.addNote(pupil1, "Chemia", 3.5);
        klasa1.addNote(pupil1, "Chemia", 4.0);
        klasa1.addNote(pupil1, "Fizyka", 3.0);
        klasa1.addNote(pupil1, "Matematyka", 4.5);
        klasa1.addNote(pupil2, "Matematyka", 3.0);
        klasa1.addNote(pupil2, "Fizyka", 2.5);
        klasa1.addNote(pupil2, "Historia", 2.5);
        klasa1.addNote(pupil2, "Chemia", 5.0);
        klasa1.addNote(pupil2, "Fizyka", 4.5);
        klasa1.addNote(pupil2, "Matematyka", 4.5);
        klasa1.addNote(pupil2, "Chemia", 5.0);
        klasa1.addNote(pupil3, "Fizyka", 4.5);
        klasa1.addNote(pupil3, "Fizyka", 4.5);
        klasa1.addNote(pupil4, "Historia", 5.0);
        klasa1.addNote(pupil4, "Fizyka", 2.5);
        klasa1.addNote(pupil4, "Chemia", 4.5);
        klasa1.addNote(pupil4, "Historia", 3.0);
        klasa1.addNote(pupil5, "Fizyka", 3.5);
        klasa1.addNote(pupil5, "Historia", 4.5);
        klasa1.addNote(pupil5, "Chemia", 5.0);
        klasa1.addNote(pupil5, "Matematyka", 4.0);
        klasa1.addNote(pupil5, "Fizyka", 3.5);
        klasa2.addNote(pupil6, "Chemia", 3.0);
        klasa2.addNote(pupil6, "Chemia", 3.0);
        klasa2.addNote(pupil6, "Fizyka", 4.0);
        klasa2.addNote(pupil6, "Matematyka", 3.5);
        klasa2.addNote(pupil7, "Matematyka", 4.0);
        klasa2.addNote(pupil7, "Fizyka", 4.5);
        klasa2.addNote(pupil7, "Historia", 3.5);
        klasa2.addNote(pupil7, "Chemia", 4.0);
        klasa2.addNote(pupil7, "Fizyka", 3.5);
        klasa2.addNote(pupil7, "Matematyka", 3.5);
        klasa2.addNote(pupil7, "Chemia", 4.0);
        klasa2.addNote(pupil8, "Fizyka", 3.5);
        klasa2.addNote(pupil8, "Fizyka", 3.5);
        klasa2.addNote(pupil8, "Historia", 4.0);
        klasa2.addNote(pupil8, "Fizyka", 3.5);
        klasa2.addNote(pupil8, "Chemia", 3.5);
        klasa2.addNote(pupil8, "Historia", 4.0);
        klasa2.addNote(pupil9, "Fizyka", 4.5);
        klasa2.addNote(pupil9, "Historia", 4.5);
        klasa2.addNote(pupil9, "Chemia", 4.0);
        klasa2.addNote(pupil10, "Matematyka", 4.0);
        klasa2.addNote(pupil10, "Fizyka", 4.5);
        //Obliczenia dotyczace ocen uczniow
        System.out.println("Average notes amount for pupil: " + pupil1.getFirstName() +" " + pupil1.getLastName()+ " = " + klasa1.averageNotesForPupil(pupil1));
        System.out.format("Average notes for the subject chemia %.2f %n", klasa1.averageNotesForAllPupils("Chemia"));
        System.out.println("Pupil with the highest average: " +  klasa1.pupilWithBiggestAverage());


        System.out.println("Average notes amount for pupil: " + pupil1.getFirstName() +" " + pupil1.getLastName()+ " = " + klasa1.averageNotesForPupil(pupil1));
       System.out.format("Average notes for the subject chemia %.2f %n", klasa1.averageNotesForAllPupils("Chemia"));
       System.out.println("Pupil with the highest average: " +  klasa1.pupilWithBiggestAverage());

        System.out.println(klasa1.showAbsentNotes(pupil1));
        klasa1.showTheBestPupilIn("Fizyka", 2).forEach(pupil -> pupil.showAverageNoteForSubject("Fizyka"));

        System.out.println("The lowest average note in Fizyka " + klasa1.showTheLowestAverageNoteIn("Fizyka"));

        Map<Integer, List<Pupil>> pupilsGroupByYear = klasa1.groupPupilsByYearOfBirth();
        for (Map.Entry<Integer, List<Pupil>> entry : pupilsGroupByYear.entrySet()) {
            System.out.println("Birth in " + entry.getKey());
            for (Pupil pupil : entry.getValue()) {
                System.out.println(pupil.getFirstName() + " " + pupil.getLastName());
            }
        }

        Map<Integer, List<Pupil>> pupilsGroupByAverage = klasa1.groupPupilsByAverage();
        for (Map.Entry<Integer, List<Pupil>> entry : pupilsGroupByAverage.entrySet()) {
            System.out.println("Average " + entry.getKey());
            for (Pupil pupil : entry.getValue()) {
                System.out.println(pupil.getFirstName() + " " + pupil.getLastName());
            }
        }

       klasa1.showPupils();
       szkola.saveToFile("school.json");
       szkola = School.loadFromFile("school.json");
       System.out.println("OUR SCHOOL RETRIEVED FROM JSON FILE:");
       System.out.println(szkola);

    }

}
