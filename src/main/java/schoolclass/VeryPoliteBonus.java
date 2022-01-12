package schoolclass;

public class VeryPoliteBonus implements IBonus {
    public String getSubjectName() {
        return subjectName;
    }
    String subjectName;
    public VeryPoliteBonus(String subjectName) {
        this.subjectName = subjectName;
    }
    public double up() {
        return 1.0;
    }
}