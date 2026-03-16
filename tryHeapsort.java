import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class tryHeapsort {
    public static void main(String[] args) throws Exception {
        String[] words;

        if (args.length == 0) {
            
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

        System.out.println("Before heapsort:");
        System.out.println(Arrays.toString(words));

        heapSort(words);

        System.out.println("After heapsort (lexicographic):");
        System.out.println(Arrays.toString(words));
    }

    private static void heapSort(String[] arr) {
        int n = arr.length;
        // bottom-up heap construction
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // sort by extracting max
        for (int end = n - 1; end > 0; end--) {
            swap(arr, 0, end);
            heapify(arr, end, 0);
        }
    }

    private static void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void heapify(String[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left].compareTo(arr[largest]) > 0) {
            largest = left;
        }
        if (right < n && arr[right].compareTo(arr[largest]) > 0) {
            largest = right;
        }
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }
}