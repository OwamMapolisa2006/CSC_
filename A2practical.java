import java.util.Arrays;
import java.util.Random;

public class A2practical {

    static Random rand = new Random();

    public static int[] slowshuffle(int N) {
        int[] shuffled = new int[N];
        boolean[] isNotPresent = new boolean[N + 1]; // index 1..N

        Arrays.fill(isNotPresent, true);

        int count = 0;


        while (count < N - 1) {
            int r = rand.nextInt(N) + 1; // [1..N]

            if (isNotPresent[r]) {
                shuffled[count] = r;
                isNotPresent[r] = false;
                count++;
            }
        }


        for (int r = 1; r <= N; r++) {
            if (isNotPresent[r]) {
                shuffled[count] = r;
                break;
            }
        }

        return shuffled;
    }
    public static int[] biasedshuffle(int N) {
        int[] shuffled = new int[N];
        for (int i = 0; i < N; i++) {
            shuffled[i] = i + 1;
        }


        for (int i = 0; i < N; i++) {
            int r = rand.nextInt(N); // [0..N-1]
            int temp = shuffled[i];
            shuffled[i] = shuffled[r];
            shuffled[r] = temp;
        }

        return shuffled;
    }


    public static int[] shuffle(int N) {
        int[] shuffled = new int[N];

        for (int i = 0; i < N; i++) {
            shuffled[i] = i + 1;
        }


        for (int i = 0; i < N; i++) {
            int r = i + rand.nextInt(N - i);
            int temp = shuffled[i];
            shuffled[i] = shuffled[r];
            shuffled[r] = temp;
        }

        return shuffled;
    }

    public static void testShuffleDistribution() {
        int trials = 60000;
        int N = 3;

        int[][] countsBiased = new int[6][1];
        int[][] countsUnbiased = new int[6][1];

        for (int t = 0; t < trials; t++) {
            int[] b1 = biasedshuffle(N);
            int[] b2 = shuffle(N);

            countsBiased[getPermutationIndex(b1)][0]++;
            countsUnbiased[getPermutationIndex(b2)][0]++;
        }

        String[] perms = {
                "[1,2,3]", "[1,3,2]", "[2,1,3]",
                "[2,3,1]", "[3,1,2]", "[3,2,1]"
        };

        System.out.println("Biased shuffle counts:");
        for (int i = 0; i < 6; i++) {
            System.out.println(perms[i] + " -> " + countsBiased[i][0]);
        }

        System.out.println("\nUnbiased shuffle counts:");
        for (int i = 0; i < 6; i++) {
            System.out.println(perms[i] + " -> " + countsUnbiased[i][0]);
        }
    }


    private static int getPermutationIndex(int[] arr) {
        if (Arrays.equals(arr, new int[]{1,2,3})) return 0;
        if (Arrays.equals(arr, new int[]{1,3,2})) return 1;
        if (Arrays.equals(arr, new int[]{2,1,3})) return 2;
        if (Arrays.equals(arr, new int[]{2,3,1})) return 3;
        if (Arrays.equals(arr, new int[]{3,1,2})) return 4;
        return 5;
    }


    public static void main(String[] args) {

        int N = 10;

        System.out.println("Slow shuffle:");
        System.out.println(Arrays.toString(slowshuffle(N)));

        System.out.println("\nBiased shuffle:");
        System.out.println(Arrays.toString(biasedshuffle(N)));

        System.out.println("\nUnbiased shuffle:");
        System.out.println(Arrays.toString(shuffle(N)));

        System.out.println("\nDistribution test (N=3, 60000 trials):");
        testShuffleDistribution();
    }
}

