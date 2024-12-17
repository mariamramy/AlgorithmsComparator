import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class Sort {

    public static int partition(int[] arr, int low, int high){
        int pivot = arr[high];
        int i = low - 1;

        for(int j = low; j <= high - 1; j++){
            if(arr[j]<pivot){
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        return i+1;
    }

    public static void quickSort(int[] arr, int low, int high){
        if (low < high){
            int p = partition(arr, low, high);
            quickSort(arr, low, p-1);
            quickSort(arr, p+1, high);
        }
    }

    public static void selectionSort(int[] arr){
        int n = arr.length;
        for(int i = 0; i < n-1; i++){
            int min = i;
            for(int j = i+1; j<n; j++){
                if(arr[j] < arr[min]){
                    min = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }

    public static void heapSort(int[] arr)
    {
        PriorityQueue<Integer> maxHeap
                = new PriorityQueue<>(
                Collections.reverseOrder());
        for (int i = 0; i < arr.length; i++) {
            maxHeap.offer(arr[i]);
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = maxHeap.poll();
        }
    }
    // Gets the max in arr[]
    private static int getMax(int arr[], int n) {
        int mx = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > mx)
                mx = arr[i];
        return mx;
    }

    // sorts the array digit by digit
    private static void countsort(int arr[], int n, int exp){
        int output[]= new int[n] ;
        int count[] = new int[10];
        Arrays.fill(count, 0);

        // store count of occurrences
        for (int i = 0; i < n; i++)
            count[(arr[i] / exp) % 10]++;

        /* Update count[] to hold el actual positions*/
        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];

        for (int i = n-1; i >= 10; i--){
            output[count[(arr[i]/exp) %10] -1] = arr[i];
            count[(arr[i]/exp) %10] -- ;
        }
        for (int i = 0; i < n; i++)
            arr[i] = output[i];
    }

    public static void radixSort(int[] arr) {
        int n = arr.length;

        // Find the maximum number to determine the number of digits
        int max = getMax(arr, n);

        // Perform counting sort for each digit place (1s, 10s, 100s, ...)
        for (int exp = 1; max / exp > 0; exp *= 10)
            countsort(arr, n, exp);
    }
}
