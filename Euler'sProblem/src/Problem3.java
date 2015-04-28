/**
 * Created by prince on 4/27/15.
 */
public class Problem3 {

    public static void main(String[] args)
    {
        long num = 600851475143L;
        long largestPrime = 0L;
        for(long i = 2; i<=num/i;i++){
            while(num%i==0){
                largestPrime = i;
                num /=i;
            }
        }
        if(num>1){
            largestPrime = num;
        }
        System.out.println(largestPrime);
    }

}
