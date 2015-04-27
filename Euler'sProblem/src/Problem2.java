/**
 * Created by prince on 4/27/15.
 */
public class Problem2 {
    public static void main(String[] args) {
        int a1=1,a2=2;
        int sum = 0;
        for (int i = 0; i <100;i++) {
            int a3=a1+a2;
            a1=a2;
            a2=a3;
            System.out.println(a3);
//            if (i%3==0 || i%5==0)
//                sum+=i;
        }

//        System.out.println(sum);

    }
}
