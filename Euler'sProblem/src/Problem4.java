/**
 * Created by prince on 4/28/15.
 */
public class Problem4 {
    public static void main(String[] args) {
        int largestPallindrome=0;
        for (int i = 999 ; i> 100; i--){
            for (int j = 999; j > 100; j--) {
                int product = i*j;
                if (isPalindrome(product)) {
                    if (product>largestPallindrome)
                        largestPallindrome=product;
                }
            }
        }
        System.out.println(largestPallindrome);
    }

    private static boolean isPalindrome(int i) {
        String s = ""+i, temp ="";
        for (i = s.length()-1; i>=0;i--){
            temp=temp+s.charAt(i);
        }
        return temp.equals(s);
    }
}
