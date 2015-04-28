/**
 * Created by prince on 4/28/15.
 */
public class Problem5 {
    public static void main(String[] args) {
        int number = 21;
        boolean check = true;
        while (true){
            for (int i = 1; i < 20; i++) {
                check = true;
                if (number%i!=0){
                    check=false;
                    break;
                }
            }
            if (check){
                System.out.println(number);
                break;
            }
            number++;
        }
    }
}
