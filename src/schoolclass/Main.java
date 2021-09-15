package schoolclass;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Teacher teacher1 = new Teacher("Jan", "Kowalski");
        teacher1.addSubject("Fizyka");
        teacher1.addSubject("Chemia");
        Pupil pupil1 = new Pupil("Kornelia", "Matuszak", LocalDate.of(1994, 01, 18));


        System.out.println(teacher1);
        System.out.println(pupil1);
        SchoolClass klasa = SchoolClass.getInstance("Klasa 4a");
        klasa.addSubject("Fizyka");
        klasa.addSubject("Chemia");
        klasa.addSubject("Fizyka");
        System.out.println(klasa.getSubjectList());
        
        klasa.setSubjectList(new HashSet<Subject>() {{
                add(new Subject("Język polski"));
                add(new Subject("Programowanie w Javie"));
        }}
        );
        System.out.println(klasa.getSubjectList());

        klasa.setSubjectsList(new Subject("Chemia"), new Subject("Język angielski"), new Subject("Programowanie w C++"));
        System.out.println(klasa.getSubjectList());

        Subject s1 = new Subject("Chemia");
        Subject s2 = new Subject("Fizyka");
        Subject s3 = new Subject("Historia");
        klasa.setSubjectList(Set.of(s1,s2, s3));
        System.out.println(klasa.getSubjectList());

        klasa.addPupil(pupil1);
        klasa.showPupils();
    }




}
