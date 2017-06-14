package xyz.tritin.meanders;

/**
 * @author Simon
 * @version 1.0
 * @since 14.06.2017
 *
 * Диапазон. Хранит левое и правое знаачение отрезка.
 */
public class Diapason {
    private int left;
    private int right;

    /**
     * @param first первый конец отрезка
     * @param second второй конец отрезка
     *
     * В конструкторе меняет first и second,
     * чтобы левый конец был всегда меньше правого.
     * */
    public Diapason(int first, int second) {
        if (first < second){
            this.left = first;
            this.right = second;
        } else {
            this.right = first;
            this.left = second;
        }
    }

    /**
     * @param a число, которое надо проверить
     *
     * Проверяет, входит ли число в отрезок.
     * */
    public boolean inRange(int a){
        return (a <= right) && (a >= left);
    }

    public void out(){
        System.out.println(left + " - " + right);
    }
}
