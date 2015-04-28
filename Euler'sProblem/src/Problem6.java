/**
 * Created by prince on 4/28/15.
 */
public class Problem6 {
    public static void main(String[] args) {
        int sumOfSquares = (100*101*201)/6;
        int squareOfSum = (int) Math.pow(100*101/2,2);
        System.out.println(squareOfSum-sumOfSquares);
    }
}
