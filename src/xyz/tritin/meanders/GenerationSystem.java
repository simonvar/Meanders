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
public class GenerationSystem {

    private MeandersGenerator meandersGenerator;

    public GenerationSystem() {
        meandersGenerator = new Generator2G();
    }

    public void performGenerate(int n){
        meandersGenerator.generate(n);
    }


    public void out(){
        for (Permutation meander : meandersGenerator.getMeanders()) {
            meander.out();
        }
    }

    public void printTime(){
        System.out.println("Время в миллисекундах: " + meandersGenerator.getTime());
    }

    public void printCount(){
        System.out.println("Количестов меандров: " + meandersGenerator.getCount());
    }

    public ArrayList<Permutation> getMeanders() {
        return meandersGenerator.getMeanders();
    }



    public void setMeandersGenerator(MeandersGenerator meandersGenerator) {
        this.meandersGenerator = meandersGenerator;
    }
}
