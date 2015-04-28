/**
 * Created by prince on 4/28/15.
 */
    public class Problem7 {
    public static void main(String[] args) {
        int count = 0, result = 0;
        int i = 2;
        while (count < 10001) {
            if (isPrime(i)) {
                count++;
                result = i;
            }
            i++;
        }
        System.out.println(result);
    }

    private static boolean isPrime(long n) {
        boolean check = true;
        for (int i = 2; i <= Math.sqrt(n) && check; i++) {
            check = !(n % i == 0);
        }
        return check;
    }
}


