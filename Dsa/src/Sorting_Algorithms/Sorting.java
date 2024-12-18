package Sorting_Algorithms;
import java.util.*;
public class Sorting extends generate_random_array {
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
    
    public static void merge(int[] A, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;
        int[] L = new int[n1 + 1];
        int[] R = new int[n2 + 1];

        for (int i = 0; i < n1; i++) {
            L[i] = A[p + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = A[q + 1 + j];
        }
        L[n1] = Integer.MAX_VALUE;
        R[n2] = Integer.MAX_VALUE;
        int i = 0, j = 0;
        for (int k = p; k <= r; k++) {
            if (L[i] <= R[j]) {
                A[k] = L[i];
                i++;
            } else {
                A[k] = R[j];
                j++;
            }
        }
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
    public static void insertionSort(int[] A) {
        for (int j = 1; j < A.length; j++) { 
            int key = A[j];
            int i = j - 1;
            while (i >= 0 && A[i] > key) {
                A[i + 1] = A[i];
                i = i - 1;
            }
            A[i + 1] = key;
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

    public static void chooseAlgorithm(int[] data) {
    	Scanner j = new Scanner(System.in);
    	int[] arr = new int[data.length];
    	arr=data;
        System.out.println("Choose a sorting algorithm:\n 1. Insertion Sort\n 2. Merge Sort\n 3. Quick sort\n 4. Selection sort \n 5. Heap Sort \n 6. bubble Sort \n 7. radix sort \n 8. counting sort \n Choice: ");
        int choice1 = j.nextInt();
        switch (choice1) {
        case 1:
        	insertionSort(data);
            System.out.print("insertion Sort Result: ");
            printArray(data);
            break;
        case 2:
        	mergeSort(data, 0, data.length - 1);
            System.out.print("Merge Sort Result: ");
            printArray(data);
            break;
        case 3:
        	quickSort(data, 0, data.length - 1);
            System.out.print("Quick Sort Result: ");
            printArray(data);
            break;
        case 4:
        	selectionSort(data);
        	System.out.print("selection Sort Result: ");
        	printArray(data);
            break;
        default:
            System.out.println("Invalid choice!");
            return;
        }
        if(0 < choice1 && choice1 < 9)
        {
            System.out.println("Choose a sorting algorithm:\n 1. Insertion Sort\n 2. Merge Sort\n 3. Quick sort\n 4. Selection sort \n 5. Heap Sort \n 6. bubble Sort \n 7. radix sort \n 8. counting sort \n Choice: ");
            int choice2= j.nextInt();
            switch (choice2) {
            case 1:
            	insertionSort(arr);
                System.out.print("insertion Sort Result: ");
                printArray(arr);
                break;
            case 2:
            	mergeSort(arr, 0, arr.length - 1);
                System.out.print("Merge Sort Result: ");
                printArray(arr);
                break;
            case 3:
            	quickSort(arr, 0, arr.length - 1);
                System.out.print("Quick Sort Result: ");
                printArray(arr);
                break;
            case 4:
            	selectionSort(arr);
            	System.out.print("selection Sort Result: ");
            	printArray(arr);
                break;
            default:
                System.out.println("Invalid choice!");
                return;
            }
        }

    }
}
