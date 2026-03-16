import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class tryHeapsort {
    public static void main(String[] args) throws Exception {
        String[] words;

        if (args.length == 0) {
            System.out.println("No args: running 20-word sample test.");
            words = new String[]{
                    "listen", "silent", "enlist", "inlets", "stone",
                    "tones", "notes", "apple", "pale", "leap",
                    "pear", "reap", "pare", "hello", "world",
                    "java", "heap", "sort", "algorithm", "binary"
            };
        } else if (args.length == 1 && Files.exists(Paths.get(args[0]))) {
            List<String> rawLines = Files.readAllLines(Paths.get(args[0]));
            List<String> filtered = new ArrayList<>();
            for (String line : rawLines) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\s+");
                for (String w : parts) {
                    String clean = w.replaceAll("[0123456789(,.;:_!?\\-\\[\\].)]", "");
                    if (!clean.isEmpty()) filtered.add(clean);
                }
            }
            words = filtered.toArray(new String[0]);
            System.out.println("Loaded " + words.length + " words from " + args[0]);
        } else {
            words = args;
        }

        System.out.println("Input word count: " + words.length);
        System.out.println("Input sample: " + Arrays.toString(Arrays.copyOf(words, Math.min(words.length, 20))));

        runAndReport("Bottom-up build", words, true);
        runAndReport("Top-down build", words, false);
    }

    private static void runAndReport(String runName, String[] source, boolean bottomUp) {
        String[] arr = Arrays.copyOf(source, source.length);

        long t0 = System.nanoTime();
        if (bottomUp) {
            buildHeapBottomUp(arr);
        } else {
            arr = buildHeapTopDown(source);
        }
        long t1 = System.nanoTime();

        String[] sorted = Arrays.copyOf(arr, arr.length);
        long t2 = System.nanoTime();
        heapSortInPlace(sorted);
        long t3 = System.nanoTime();
        System.out.println("\n=== " + runName + " ===");
        System.out.printf("build time: %.3f ms\n", (t1 - t0) / 1_000_000.0);
        System.out.printf("sort time: %.3f ms\n", (t3 - t2) / 1_000_000.0);
        System.out.printf("total   : %.3f ms\n", (t3 - t0) / 1_000_000.0);

        System.out.println("heap sample (first 20): " + Arrays.toString(Arrays.copyOf(arr, Math.min(arr.length, 20))));
        System.out.println("sorted sample (first 20): " + Arrays.toString(Arrays.copyOf(sorted, Math.min(sorted.length, 20))));

        if (sorted.length <= 40) {
            System.out.println("sorted full: " + Arrays.toString(sorted));
        }
    }

    private static void buildHeapBottomUp(String[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            siftDown(arr, i, n);
        }
    }

    private static String[] buildHeapTopDown(String[] source) {
        String[] heap = new String[source.length];
        int size = 0;
        for (String word : source) {
            heap[size] = word;
            siftUp(heap, size);
            size++;
        }
        return heap;
    }

    private static void heapSortInPlace(String[] arr) {
        int n = arr.length;
        for (int end = n - 1; end > 0; end--) {
            swap(arr, 0, end);
            siftDown(arr, 0, end);
        }
    }

    private static void siftDown(String[] arr, int i, int length) {
        int largest = i;
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < length && arr[left].compareTo(arr[largest]) > 0) {
                largest = left;
            }
            if (right < length && arr[right].compareTo(arr[largest]) > 0) {
                largest = right;
            }

            if (largest == i) break;
            swap(arr, i, largest);
            i = largest;
        }
    }

    private static void siftUp(String[] heap, int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (heap[parent].compareTo(heap[i]) >= 0) break;
            swap(heap, parent, i);
            i = parent;
        }
    }

    private static void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}