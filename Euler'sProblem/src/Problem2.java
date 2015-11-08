/**
 * Created by prince on 4/27/15.
 */
public class Problem2 {
    public static void main(String[] args) {
        inint first = 1;
		int second = 2;
		int next = 0;
		int sum = second;
		while (next<4000000 && (first+second)<4000000) {
			next = first + second;
			first = second;
			second = next;
			sum += next%2 == 0 ? next : 0;
		}
		System.out.println(sum);
    }
}
