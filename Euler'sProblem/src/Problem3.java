/**
 * Created by prince on 4/27/15.
 */
public class Problem3 {

    public static void main(String[] args)
    {

        long num = 600851475143L;
        long prime = 2;
        while (true)
        {
            long tmp = 600851475143L / prime;
            if ( num % tmp == 0 &&  isPrimeNumber(tmp) )
            {
                System.out.println(tmp);
                break;
            }
            prime++;
        }
    }

    public static boolean isPrimeNumber(long number){

        for(long i=2; i<=number/2; i++){
            if(number % i == 0){
                return false;
            }
        }
        return true;
    }

}
