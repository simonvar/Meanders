package xyz.tritin.meanders;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by simon on 13.06.17.
 */
public class Generator implements Runnable {

    private ArrayList<Integer> array;
    private int count;

    public Generator(int n) {
        array = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            array.add(i);
        }

        count = 1;
    }

    public void generate() {
        Permutation permutation = new Permutation(array);
        permutation.out();

        while (next(array)){
            permutation = new Permutation(array);
            permutation.checkForMeander();

            if (permutation.isMeander()) {
                count++;
                permutation.out();
            }
        }
        System.out.println("Количество: " + count);
    }

    private boolean next(ArrayList<Integer> a) {
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


    @Override
    public void run() {

    }
}
