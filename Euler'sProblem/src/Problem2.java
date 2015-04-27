/**
 * Created by prince on 4/27/15.
 */
public class Problem2 {
    public static void main(String[] args) {
        int a1=1,a2=2;
        int sum = 0,a3=0;
        if (a1%2==0){
            sum+=a1;
        }
        if (a2%2==0){
            sum+=a2;
        }
        for (int i = 0; i < 4000000 && a3<4000000; i++) {
            a3= a1+a2;
            a1=a2;
            a2=a3;
            if (a3%2==0) {
                sum += a3;
            }
        }
        System.out.println(sum);
    }
}
