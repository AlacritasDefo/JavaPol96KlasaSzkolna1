package schoolclass;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        Teacher teacher1 = new Teacher("Jan", "Kowalski");
        teacher1.addSubject("Fizyka");
        teacher1.addSubject("Chemia");
        Pupil pupil1 = new Pupil("Kornelia", "Matuszak", LocalDate.of(1994, 01, 18));


        System.out.println(teacher1);
        System.out.println(pupil1);

        }



    }
