/**
 * Created by prince on 4/28/15.
 */
public class Problem15 {
    public static void main(String[] args) {
        int n = 40, k =20;
        long result = 1;
        for (int i=0; i<k; i++) {
            result = result * (n - i);
            result = result / (i + 1);
        }
        System.out.println(result);
    }
}
