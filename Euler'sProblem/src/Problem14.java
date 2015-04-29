import java.util.LinkedList;

/**
 * Created by prince on 4/28/15.
 */
public class Problem14 {
    public static void main(String[] args)
    {
        LinkedList<Long> list = new LinkedList<Long>();

        long length = 0;
        int track = 0;
        int count = 0, largeCount =0;

        for(int j = 10; j < 1000000; j++)
        {
            long i = j;
            while (i != 1)
            {
                if (i % 2 == 0)
                {
                    i /= 2;
                    count++;
                }
                else
                {
                    i = (3 * i) + 1;
                    count++;
                }
            }

            if(count>largeCount) {
                largeCount = count;
                track = j;
            }
            count=0;
            }
        System.out.print(track);
        }

    }

