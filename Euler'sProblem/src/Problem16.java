import java.math.BigInteger;

/**
 * Created by prince on 4/28/15.
 */
public class Problem16 {
    public static void main(String[] args) {
        String num = "10715086071862673209484250490600018105614048117055336074437503883703510511249361224931983788156958581275946729175531468251871452856923140435984577574698574803934567774824230985421074605062371141877954182153046474983581941267398767559165543946077062914571196477686542167660429831652624386837205668069376";
        int sum =0;
        for (int i = 0; i < num.length(); i++) {
            Character c = new Character(num.charAt(i));
            sum += Integer.parseInt(c.toString());
        }
        System.out.print(sum);
    }
}
