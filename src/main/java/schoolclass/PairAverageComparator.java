package schoolclass;

import org.javatuples.Pair;

import java.util.Comparator;

/**
 * Comparator dzialajacy na typie Pair, gdzie pierwszy element jest typu Pupil a drugi typu double
 */

public class PairAverageComparator implements Comparator<Pair<Pupil, Double>> {
    @Override
    public int compare(Pair<Pupil, Double> o1, Pair<Pupil, Double> o2) {
        return (int) (o1.getValue1() * 100 - o2.getValue1() * 100);
    }
}
