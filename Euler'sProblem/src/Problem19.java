/**
 * Created by prince on 4/29/15.
 */
public class Problem19 {
    public static void main(String[] args) {
        int sunday = 0;
        for (int i = 1900; i < 2000; i++) {
            for (int j = 1; j < 13; j++) {
                int d = 0;
                int m = (j-3)%12+1;
                int Y;
                if (j==1 || j ==2){
                    Y = i-1;
                }else {
                    Y = i;
                }
                int y = Y%100;
                int c = (Y - y)/100;
                if (((d + Math.floor(2.6*m-0.2)+y+Math.floor(y/4)+Math.floor(c/4)-2*c)%7)==0)
                    sunday++;
            }
        }
        System.out.println(sunday);
    }
}
