import java.math.BigInteger;

/**
 * Created by prince on 5/2/15.
 */
public class Problem20{
    public static void main (String[] args) {
        BigInteger inc = new BigInteger("1");
        BigInteger fact = factorial(new BigInteger("100"));
        int sum =0;
        String number = fact.toString();
        for(int i = 0; i <= number.length() - 1; i ++){
            String s ="" + number.charAt(i);
            int n1 = Integer.parseInt(s);
            sum += n1;
        }
        System.out.println(sum);
    }

    public static BigInteger factorial( BigInteger n )
    {
        if ( n.compareTo(BigInteger.ZERO) <= 0 ) {
            return BigInteger.ONE;
        }
        else  {
            return ( n.multiply(factorial ( n.subtract(BigInteger.ONE)) ) );
        }
    }
}