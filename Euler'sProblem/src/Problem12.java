/**
 * Created by prince on 4/28/15.
 */
public class Problem12 {
    public static void main(String[] args) {
        long n = 1,triangle =0;
        boolean check = true;
        while(n!=0 && check){
            triangle=n *(n+1)/2;
            int count=2;
            for(int i=2;i<=Math.sqrt(triangle) && check;i++){
                if((triangle%i)==0) {
                    count+=2;
                }
                if(count>=500){
                    check=false;
                }
            }
            n++;
        }
        System.out.print(triangle);
    }
}
