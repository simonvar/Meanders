package xyz.tritin.meanders;

/**
 * Created by simon on 13.06.17.
 */
public class Diapason {
    private int left;
    private int right;

    public Diapason(int left, int right) {
        if (left < right){
            this.left = left;
            this.right = right;
        } else {
            this.right = left;
            this.left = right;
        }
    }

    public boolean inRange(int a){
        return (a <= right) && (a >= left);
    }

    public void out(){
        System.out.println(left + " - " + right);
    }
}
