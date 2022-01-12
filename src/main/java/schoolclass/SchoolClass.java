package schoolclass;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.javatuples.Pair;

public class SchoolClass {
    private String className;
    private List<Pupil> pupilsList;
    private List<Teacher> teachersList;

    public void setSubjectList(Set<Subject> subjectList) {
        this.subjectList.clear();
        this.subjectList.addAll(subjectList);
    }
    /**
     * Pobranie listy przedmiotow uczonych w klasie szkolnej
     */
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

    /**
     * Dodanie uczniow do listy uczniow z klasy szkolnej
     * @param objs
     */
    public void addPupils(Pupil... objs) {
        Collections.addAll(pupilsList, objs);
    }

    /**
     * Dodanie przedmiotu dla klasy z uwzglednieniem tego czy w szkole uczy sie tego przedmiotu
     * @param subjectName
     * @throws ClassException
     */
    public void addSubjectForClass(String subjectName) throws ClassException {
        for (Subject subject : subjectList) {
            if (subject.getName().equals(subjectName)) {
                return;
            }
        }
        boolean jest = false;
        for(Subject subject : School.getSubjects())
            if (subject.getName().equals(subjectName)) {
                jest = true;
                break;
            }
        if (jest == false)
            throw new ClassException("There is no subject " + subjectName + " in the school!");
        subjectList.add(new Subject(subjectName));
    }

    /**
     * Deklaracja iteratora w postaci ClassIterator<Pupil>
     * @return
     */
    public Iterator iterator() {
        return new ClassIterator<Pupil>(pupilsList);
    }

    /**
     * Przydzial nauczyciela do kazdego z przedmiotow uczonych w klasie szkolnej
     * @param teachers
     * @throws ClassException
     */
    public void addTeachersToSubjects(List<Teacher> teachers) throws ClassException {
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
                throw new ClassException("Teacher for subject " + subject.getName() + " not found !" );
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

    /**
     * Zapisanie wszystkich danych klasy szkolnej w pliku JSON
     * @param fileName
     */
    public void saveToFile(String fileName) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
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

    /**
     * Deserializacja klasy szkolnej z pliku JSON
     * @param fileName
     * @return
     */
    public static SchoolClass loadFromFile(String fileName) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        Gson gson = new GsonBuilder()
                .create();
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
            SchoolClass sc = gson.fromJson(text, SchoolClass.class);
            return sc;
        }
        catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        return null;
    }

    public void showPupilsSortedByName() {
        System.out.println("CLASS SORTED BY PUPIL'S LASTNAME");
        PupilNameComparator comparator = new PupilNameComparator();
        Collections.sort(pupilsList, comparator);
        for(Pupil pupil : pupilsList)
            System.out.println(pupil.toString());
    }
}