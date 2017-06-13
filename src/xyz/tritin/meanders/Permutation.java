package xyz.tritin.meanders;

import java.util.ArrayList;

/**
 * Created by simon on 13.06.17.
 */
public class Permutation {

    enum direction {Up, Down};

    private ArrayList<Integer> numbers;
    private boolean isMeander;

    public Permutation(ArrayList<Integer> numbers) {
        this.numbers = numbers;
    }

    public void checkForMeander(){
        direction d = direction.Down;
        int start = 1;
        int max = 0;

        ArrayList<Diapason> RangesDown = new ArrayList<>();
        ArrayList<Diapason> RangesUp = new ArrayList<>();

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
                for (Diapason range : RangesDown){
                    if (range.inRange(num) && !range.inRange(numbers.get(i - 1))){
                        this.isMeander = false;
                        return;
                    }
                }

                RangesDown.add(new Diapason(start, num));
                d = direction.Up;
            } else {
                for (Diapason range : RangesUp){
                    if (range.inRange(num) && !range.inRange(numbers.get(i - 1))){
                        this.isMeander = false;
                        return;
                    }
                }

                RangesUp.add(new Diapason(start, num));
                d = direction.Down;
            }

            start = num;

        }

        if (numbers.size() != max){
            this.isMeander = false;
            return;
        }

        this.isMeander = true;
    }

    public void out(){
        for (Integer number : numbers){
            System.out.print(number + " ");
        }
        System.out.println();
    }

    public ArrayList<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(ArrayList<Integer> numbers) {
        this.numbers = numbers;
    }

    public boolean isMeander() {
        return isMeander;
    }

    public void setMeander(boolean meander) {
        isMeander = meander;
    }

}
