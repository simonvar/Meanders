package xyz.tritin.meanders;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Simon
 * @since 17.06.17
 */
public abstract class MeandersGenerator {

    protected ArrayList<Permutation> meanders;

    protected long count;
    protected long time;

    /**
     * @param n количество мостов
     * */
    public abstract void generate(int n);

    /**
     * Генерация перестновок без повторений в лекcикографическом порядке
     * */
    protected boolean next(ArrayList<Integer> a) {
        int n = a.size();

        int j = n - 2;
        while (j != -1 && a.get(j) >= a.get(j + 1)) {
            j--;
        }

        if (j == -1) {
            return false; // больше перестановок нет
        }

        int k = n - 1;

        while (a.get(j) >= a.get(k)) {
            k--;
        }

        Collections.swap(a, j, k);

        int l = j + 1;
        int r = n - 1; // сортируем оставшуюся часть последовательности

        while (l < r) {
            Collections.swap(a, l++, r--);
        }

        return true;
    }


    public ArrayList<Permutation> getMeanders() {
        return meanders;
    }


    public long getCount() {
        return count;
    }

    public long getTime() {
        return time;
    }
}
