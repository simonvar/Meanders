package xyz.tritin.meanders;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Simon
 * @version 1.0
 * @since 14.06.2017
 *
 * Класс генерирования меандров
 */
public class Generator {

    private ArrayList<Integer> array;
    private int count;

    private ArrayList<Integer> evn;
    private ArrayList<Integer> odd;

    private ArrayList<Permutation> meanders;

    public Generator() {

    }

    public Generator(int n) {
        meanders = new ArrayList<>();
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

    /**
     * Алгоритм генерации меандров 2-го поколения
     * */
    public void generate2G() {
        count = 0;
        meanders = new ArrayList<>();

        long t1 = System.currentTimeMillis();

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
                    meanders.add(new Permutation(new ArrayList<>(array)));
                }
            } while (next(evn));

            for (int i = 0, k = 2; i < evn.size(); i++, k += 2) {
                evn.set(i, k);
            }
        } while (next(odd));
        System.out.println("Количество: " + count);

        long t2 = System.currentTimeMillis();
        System.out.println("Время в миллисекундах: " + (t2 - t1));

    }


    /**
     * Алгоритм генерации мендров 1-го поколения
     *
     * @deprecated уже есть алгоритм 2-го поколения
     * @see Generator#generate2G()
     * */
    public void generate1G(){
        count = 1;
        meanders = new ArrayList<>();

        long t1 = System.currentTimeMillis();

        Permutation permutation;
        meanders.add(new Permutation(new ArrayList<>(array)));



        while (next(array)){
            permutation = new Permutation(array);
            permutation.checkForMeander();

            if (permutation.isMeander()){
                count++;
                meanders.add(new Permutation(new ArrayList<>(array)));
            }

        }

        System.out.println("Количество: " + count);

        long t2 = System.currentTimeMillis();
        System.out.println("Время в миллисекундах: " + (t2 - t1));
    }


    /**
     * Генерация перестновок без повторений в лекцикографичесок порядке
     * */
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

    public void out(){
        for (Permutation meander : meanders) {
            meander.out();
        }
    }

    public ArrayList<Permutation> getMeanders() {
        return meanders;
    }


}
