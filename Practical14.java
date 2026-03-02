import java.util.*;
public class Practical14{
   

    static int N = 1 << 20; // 2^20 = 1,048,576
    static int MAX_DATA = 950000;
    static int REPETITIONS = 30;

    public static void main(String[] args) {

        Pair[] data = generateData();

        double[] loadFactors = {0.75, 0.80, 0.85, 0.90, 0.95};

        System.out.println("Average Time (seconds)");
        System.out.println("Load\tOpenHash\tChainedHash");
        System.out.println("---------------------------------------");

        for (double alpha : loadFactors) {

            int nEntries = (int)(MAX_DATA * alpha / 0.95);
            int m = (int)(nEntries / alpha);

            double openTime = timeOpenHash(data, nEntries, m);
            double chainedTime = timeChainedHash(data, nEntries, m);

            System.out.printf("%.0f%%\t%.6f\t%.6f\n",
                    alpha * 100,
                    openTime,
                    chainedTime);
        }
        
    }

 }