/**
 * Created by prince on 4/28/15.
 */
public class Problem10 {
    public static void main(String[] args) {
        long sum =0;
        for (int i = 2; i < 2000000; i++) {
            if (isPrime(i)) {
                sum += i;
            }
        }
        System.out.println(sum);
    }

    private static boolean isPrime(long n)
    {
        boolean check = true;
        int condition = (int)Math.sqrt(n);
        for (int i=2; i<=  condition && check; i++)
        {
            check = !(n % i == 0);
        }
        return check;
    }
}
