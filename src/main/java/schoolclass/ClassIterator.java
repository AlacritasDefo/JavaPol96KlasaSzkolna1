package schoolclass;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Klasa iteratora operujacego na dowolnym typie T
 * @param <T>
 */
public class ClassIterator<T> implements Iterator<T> {
    List<T> list;
    int current;
    /**
     * Zainicjowanie iteratora w konstruktorze
     */
    public ClassIterator(List<T> list) {
        if (list.size() > 0) {
            current = 0;
            this.list = list;
        } else {
            current = -1;
            this.list = new ArrayList<T>();
        }
    }

    /**
     * Metoda zwraca true jesli w kolekcji istnieje element za biezacym elementem
     * @return
     */
    public boolean hasNext() {
        if (list.size() > current)
            return true;
        else
            return false;
    }

    /**
     * Metoda zwraca nastepny element
     * @return
     */
    public T next() {
        T element = list.get(current);
        current++;
        return element;
    }

    /**
     * Metoda usuwa biezacy element (metoda nie jest zaimplementowana)
     */
    public void remove() {
        throw new UnsupportedOperationException();
    }
}


