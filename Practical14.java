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

    }static Pair[] generateData() {

        Pair[] data = new Pair[N];
        List<String> keys = new ArrayList<>();

        for (int i = 1; i <= N; i++)
            keys.add(String.valueOf(i));

        Collections.shuffle(keys);

        for (int i = 0; i < N; i++)
            data[i] = new Pair(keys.get(i), String.valueOf(i + 1));

        return data;
    }


    static double timeOpenHash(Pair[] data, int nEntries, int m) {

        double totalTime = 0;

        for (int r = 0; r < REPETITIONS; r++) {

            OpenHash table = new OpenHash(m);

            for (int i = 0; i < nEntries; i++)
                table.insert(data[i].key, data[i].value);

            long start = System.currentTimeMillis();

            for (int i = 0; i < nEntries; i++)
                table.lookup(data[i].key);

            long end = System.currentTimeMillis();

            totalTime += (end - start) / 1000.0;
        }

        return totalTime / REPETITIONS;
    }

    static double timeChainedHash(Pair[] data, int nEntries, int m) {

        double totalTime = 0;

        for (int r = 0; r < REPETITIONS; r++) {

            ChainedHash table = new ChainedHash(m);

            for (int i = 0; i < nEntries; i++)
                table.insert(data[i].key, data[i].value);

            long start = System.currentTimeMillis();

            for (int i = 0; i < nEntries; i++)
                table.lookup(data[i].key);

            long end = System.currentTimeMillis();

            totalTime += (end - start) / 1000.0;
        }

        return totalTime / REPETITIONS;
    }static Pair[] generateData() {

        Pair[] data = new Pair[N];
        List<String> keys = new ArrayList<>();

        for (int i = 1; i <= N; i++)
            keys.add(String.valueOf(i));

        Collections.shuffle(keys);

        for (int i = 0; i < N; i++)
            data[i] = new Pair(keys.get(i), String.valueOf(i + 1));

        return data;
    }

    // -------------------------------
    // Timing Open Hash
    // -------------------------------

    static double timeOpenHash(Pair[] data, int nEntries, int m) {

        double totalTime = 0;

        for (int r = 0; r < REPETITIONS; r++) {

            OpenHash table = new OpenHash(m);

            for (int i = 0; i < nEntries; i++)
                table.insert(data[i].key, data[i].value);

            long start = System.currentTimeMillis();

            for (int i = 0; i < nEntries; i++)
                table.lookup(data[i].key);

            long end = System.currentTimeMillis();

            totalTime += (end - start) / 1000.0;
        }

        return totalTime / REPETITIONS;
    }

   