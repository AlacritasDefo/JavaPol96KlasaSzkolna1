package schoolclass;

import java.util.Iterator;
import java.util.List;

public class SchoolClassIterator implements Iterator<Pupil> {
    private List<Pupil> pupilList;
    private int current;

    public SchoolClassIterator(List<Pupil> pupilList) {
        this.pupilList = pupilList;
        if (pupilList.size() > 0) {
            current = 0;
        } else {
            current = -1;
        }
    }

    @Override
    public boolean hasNext() {
        if (pupilList.size() > current)
            return true;
        else
            return false;
    }

    @Override
    public Pupil next() {
        return pupilList.get(current++);
    }
}
