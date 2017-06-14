package xyz.tritin.meanders;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by simon on 13.06.17.
 */
public class Generator implements Runnable {

    private ArrayList<Integer> array;
    private int count;

    ArrayList<Integer> evn;
    ArrayList<Integer> odd;

    public Generator(int n) {
        array = new ArrayList<>();
        evn = new ArrayList<>();
        odd = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            array.add(i);
            if (i % 2 == 0) {
                evn.add(i);
            } else {
                odd.add(i);
            }
        }

        count = 0;
    }

    public void generate() {
        if (array.size() == 1){
            System.out.println("Количество: 1");
            return;
        }

        Permutation permutation;

        do {
            do {
                for (int i = 0, k = 0, l = 0; i < array.size(); i++) {
                    if ((i + 1) % 2 == 0) {
                        array.set(i, evn.get(k));
                        k++;
                    } else {
                        array.set(i, odd.get(l));
                        l++;
                    }
                }

                permutation = new Permutation(array);
                permutation.checkForMeanderInGen();

                if (permutation.isMeander()) {
                    count++;
                    permutation.out();
                }
            } while (next(evn));

            for (int i = 0, k = 2; i < evn.size(); i++, k += 2) {
                evn.set(i, k);
            }
        } while (next(odd));
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

    /*
    *
    * 1 2 3 4
    * 1 2 4 3 -
    * 1 3 2 4 -
    * 1 3 4 2
    * 1 4 2 3 -
    * 1 4 3 2
    *
    *
    * */


    @Override
    public void run() {

    }
}
