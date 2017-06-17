package xyz.tritin.meanders;

import java.util.ArrayList;

/**
 * @author Simon
 * @since 17.06.17
 *
 * Алгоритм генерации меандров 2-го поколения
 * Используются два массива.
 * */
public class Generator2G extends MeandersGenerator{

    private ArrayList<Integer> array;

    /**
     * Массив четных позиций
     * */
    private ArrayList<Integer> evn;

    /**
     * Массив нечетных позиций
     * */
    private ArrayList<Integer> odd;


    @Override
    public void generate(int n) {
        array = new ArrayList<>();
        meanders = new ArrayList<>();
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

        count = 0;
        meanders = new ArrayList<>();

        long t1 = System.currentTimeMillis();

        if (array.size() == 1){
            ArrayList<Integer> perm = new ArrayList<>();
            perm.add(1);
            meanders.add(new Permutation(perm));
            count = 1;
            time = 1;
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
                    meanders.add(new Permutation(new ArrayList<>(array)));
                }
            } while (next(evn));

            for (int i = 0, k = 2; i < evn.size(); i++, k += 2) {
                evn.set(i, k);
            }
        } while (next(odd));

        long t2 = System.currentTimeMillis();
        time = t2 - t1;
    }
}
