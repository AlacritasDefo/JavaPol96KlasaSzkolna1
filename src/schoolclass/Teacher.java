package schoolclass;

import java.util.HashSet;
import java.util.Set;

public class Teacher extends Person{
    Set<String> subjects;

    Teacher () {
        subjects = new HashSet<String>;
    }

    Teacher (Set<String> subjects) {
        this.subjects = subjects;
    }

    public void setSubjects(Set<String> subjects) {
        this.subjects = subjects;
    }

    public Set<String> getSubjects() {
        return subjects;
    }
}
