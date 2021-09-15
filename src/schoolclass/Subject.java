package schoolclass;

public class Subject {
    private String name;
    private Teacher teacher;

    public Subject(String name) {
        this.name = name;
    }

    public Subject(String name, Teacher teacher) {
        this.name = name;
        this.teacher = teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                ", teacher=" + teacher +
                '}';
    }
}
