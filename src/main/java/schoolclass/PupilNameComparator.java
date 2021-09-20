package schoolclass;

import java.util.Comparator;

/**
 * Klasa dostarczajaca comparatora operujacego na kolekcji typu Pupil. Porownanie dotyczy nazwisk uczniow
 */
public class PupilNameComparator implements Comparator<Pupil> {
    @Override
    public int compare(Pupil firstPupil, Pupil secondPupil) {
        return firstPupil.getLastName().compareTo(secondPupil.getLastName());
    }

}