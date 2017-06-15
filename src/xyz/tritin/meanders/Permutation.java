package xyz.tritin.meanders;

import java.util.ArrayList;

/**
 * @author Simon
 * @version 1.0
 * @since 14.06.2017
 *
 * Класс перестановки.
 *
 */
public class Permutation {

    /**
     * Направление "реки"
     * */
    enum direction {Up, Down};

    /**
     * Числа перестановки
     * */
    private ArrayList<Integer> numbers;

    /**
     * Является ли пеестановка меандром
     * */
    private boolean isMeander;

    public Permutation(ArrayList<Integer> numbers) {
        this.numbers = numbers;
    }

    /**
     * Проверяет правильную расстановку четных и нечетных элементов,
     * и пересечения.
     *
     * */
    public void checkForMeander(){
        direction d = direction.Down;
        int start = 1;
        int max = 0;

        ArrayList<Diapason> RangesDown = new ArrayList<>();
        ArrayList<Diapason> RangesUp = new ArrayList<>();
        ArrayList<Diapason> ranges;

        for (int i = 0; i < numbers.size(); i++){
            int num = numbers.get(i);

            if (num > max){
                max = num;
            }

            if ((i + 1) % 2 != 0 && num % 2 == 0){
                this.isMeander = false;
                return;
            }

            if (d.equals(direction.Down)){
                ranges = RangesDown;
                d = direction.Up;
            } else {
                ranges = RangesUp;
                d = direction.Down;
            }

            for (Diapason range : ranges){
                if (range.inRange(num) && !range.inRange(numbers.get(i - 1))){
                    this.isMeander = false;
                    return;
                }
            }

            ranges.add(new Diapason(start, num));

            start = num;

        }

        if (numbers.size() != max){
            this.isMeander = false;
            return;
        }

        this.isMeander = true;
    }

    /**
     * Проверяет только пересечения
     * */
    public void checkForMeanderInGen(){
        direction d = direction.Down;
        int start = 1;

        ArrayList<Diapason> RangesDown = new ArrayList<>();
        ArrayList<Diapason> RangesUp = new ArrayList<>();
        ArrayList<Diapason> ranges;


        for (int i = 0; i < numbers.size(); i++){
            int num = numbers.get(i);

            if (d.equals(direction.Down)){
                ranges = RangesDown;
                d = direction.Up;
            } else {
                ranges = RangesUp;
                d = direction.Down;
            }

            for (Diapason range : ranges){
                if (range.inRange(num) && !range.inRange(numbers.get(i - 1))){
                    this.isMeander = false;
                    return;
                }
            }

            ranges.add(new Diapason(start, num));

            start = num;
        }

        this.isMeander = true;
    }

    /**
     * Вывод перестановки
     * */
    public void out(){
        for (Integer number : numbers){
            System.out.print(number + " ");
        }
        System.out.println();
    }

    public ArrayList<Integer> getNumbers() {
        return numbers;
    }

    public boolean isMeander() {
        return isMeander;
    }


}
