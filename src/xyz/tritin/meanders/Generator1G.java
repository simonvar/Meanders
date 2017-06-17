package xyz.tritin.meanders;

import java.util.ArrayList;

/**
 * @author Simon
 * @since 17.06.17
 *
 * Алгоритм генерации мендров 1-го поколения
 *
 * @deprecated уже есть алгоритм 2-го поколения
 * @see Generator2G
 * */
public class Generator1G extends MeandersGenerator {

    private ArrayList<Integer> array;


    @Override
    public void generate(int n) {
        meanders = new ArrayList<>();
        array = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            array.add(i);
        }

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

        long t2 = System.currentTimeMillis();
        time = t2 - t1;
    }

}
