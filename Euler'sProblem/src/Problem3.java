/**
 * Created by prince on 4/27/15.
 */
public class Problem3 {

    public static void main(String[] args) {
        int a =0;
        for (int i = 1; i < 5143; i++) {
            if (5143%i==0 && isPrimeNumber(i)){
                a=i;
            }
        }
        System.out.println(a);
    }

    public static boolean isPrimeNumber(int number){

        for(int i=2; i<=number/2; i++){
            if(number % i == 0){
                return false;
            }
        }
        return true;
    }

}
