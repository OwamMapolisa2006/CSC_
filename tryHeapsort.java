import java.util.BufferedReader;
import java.util.FileReader;

public class tryHeapsort{
     static void swap(String[] arr, int i, int j){
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    static void heapify(String[] arr, int n, int i){
        int largest = i;
        int left = 2*i + 1;
        int right = 2*i + 2;

        if(left < n && arr[left].compareTo(arr[largest]) > 0){
            largest = left;
        }
        if(right < n && arr[right].compareTo(arr[largest]) > 0){
            largest = right;
        }
        if(largest != i){
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }
}