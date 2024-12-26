package SortingTool;

import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

import java.util.Arrays;

public class Algorithms {

    // Bubble Sort Algorithm
    public static void bubbleSort(int[] arr, StepCounter stepCounter, int[] sortedArr, Series<Number, Number> series) {
        int n = arr.length;
        System.arraycopy(arr, 0, sortedArr, 0, n);
        updateChart(series, sortedArr, stepCounter.getSteps()); // Initial chart update

        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                stepCounter.increment();
                if (sortedArr[j] > sortedArr[j + 1]) {
                    swap(sortedArr, j, j + 1);
                    swapped = true;
                }
                updateChart(series, sortedArr, stepCounter.getSteps()); // Chart update within loop
            }
            if (!swapped) break;
        }
        updateChart(series, sortedArr, stepCounter.getSteps()); // Final chart update
    }

    // Quick Sort Algorithm
    public static void quickSort(int[] arr, int low, int high, StepCounter stepCounter, int[] sortedArr, Series<Number, Number> series) {
        System.arraycopy(arr, 0, sortedArr, 0, arr.length);
        updateChart(series, sortedArr, stepCounter.getSteps()); // Initial chart update
        if (low < high) {
            int pi = partition(sortedArr, low, high, stepCounter, series);
            quickSort(sortedArr, low, pi - 1, stepCounter, sortedArr, series);
            quickSort(sortedArr, pi + 1, high, stepCounter, sortedArr, series);
        }
        updateChart(series, sortedArr, stepCounter.getSteps()); // Final chart update
    }

    // Partition method for Quick Sort
    private static int partition(int[] arr, int low, int high, StepCounter stepCounter, Series<Number, Number> series) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            stepCounter.increment();
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
            updateChart(series, arr, stepCounter.getSteps()); // Chart update within loop
        }
        swap(arr, i + 1, high); // Place pivot in the correct position
        updateChart(series, arr, stepCounter.getSteps()); // Final chart update after partition
        return i + 1;
    }

    // Merge Sort Algorithm
    public static void mergeSort(int[] arr, int left, int right, StepCounter stepCounter, int[] sortedArr, Series<Number, Number> series) {
        System.arraycopy(arr, 0, sortedArr, 0, arr.length);
        updateChart(series, sortedArr, stepCounter.getSteps()); // Initial chart update
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(sortedArr, left, middle, stepCounter, sortedArr, series);
            mergeSort(sortedArr, middle + 1, right, stepCounter, sortedArr, series);
            merge(sortedArr, left, middle, right, stepCounter, sortedArr, series);
        }
        updateChart(series, sortedArr, stepCounter.getSteps()); // Final chart update
    }

    // Merge helper function for Merge Sort
    private static void merge(int[] arr, int left, int middle, int right, StepCounter stepCounter, int[] sortedArr, Series<Number, Number> series) {
        int n1 = middle - left + 1;
        int n2 = right - middle;
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        System.arraycopy(arr, left, leftArr, 0, n1);
        System.arraycopy(arr, middle + 1, rightArr, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            stepCounter.increment();
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
            updateChart(series, arr, stepCounter.getSteps()); // Chart update within loop
        }

        // Copy remaining elements from leftArr if any
        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
            updateChart(series, arr, stepCounter.getSteps()); // Chart update after merging left
        }

        // Copy remaining elements from rightArr if any
        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
            updateChart(series, arr, stepCounter.getSteps()); // Chart update after merging right
        }
    }

    // Selection Sort Algorithm
    public static void selectionSort(int[] arr, StepCounter stepCounter, int[] sortedArr, Series<Number, Number> series) {
        int n = arr.length;
        System.arraycopy(arr, 0, sortedArr, 0, n);
        updateChart(series, sortedArr, stepCounter.getSteps()); // Initial chart update

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                stepCounter.increment();
                if (sortedArr[j] < sortedArr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(sortedArr, minIndex, i);
            updateChart(series, sortedArr, stepCounter.getSteps()); // Chart update within loop
        }
        updateChart(series, sortedArr, stepCounter.getSteps()); // Final chart update
    }

    // Helper method for swapping elements in an array
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Heap Sort Algorithm
    public static void heapSort(int[] arr, StepCounter stepCounter, int[] sortedArr, Series<Number, Number> series) {
        int n = arr.length;
        System.arraycopy(arr, 0, sortedArr, 0, n);
        updateChart(series, sortedArr, stepCounter.getSteps()); // Initial chart update

        // Build the heap (rearrange the array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(sortedArr, n, i, stepCounter, series);
        }

        // One by one extract elements from heap
        for (int i = n - 1; i >= 0; i--) {
            swap(sortedArr, 0, i);
            stepCounter.increment(); // Increment for each swap
            updateChart(series, sortedArr, stepCounter.getSteps()); // Update chart after swap
            heapify(sortedArr, i, 0, stepCounter, series); // Heapify the reduced heap
        }

        updateChart(series, sortedArr, stepCounter.getSteps()); // Final chart update
    }

    // Heapify helper function for Heap Sort
    private static void heapify(int[] arr, int n, int i, StepCounter stepCounter, Series<Number, Number> series) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // If left child is larger than root
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // If right child is larger than largest so far
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // If largest is not root
        if (largest != i) {
            swap(arr, i, largest);
            stepCounter.increment(); // Increment for each swap
            updateChart(series, arr, stepCounter.getSteps()); // Update chart after swap

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest, stepCounter, series);
        }
    }

    // Counting Sort Algorithm
    public static void countingSort(int[] arr, StepCounter stepCounter, int[] sortedArr, Series<Number, Number> series) {
        int max = Arrays.stream(arr).max().getAsInt();
        int[] count = new int[max + 1];
        System.arraycopy(arr, 0, sortedArr, 0, arr.length);
        updateChart(series, sortedArr, stepCounter.getSteps()); // Initial chart update

        for (int i : arr) {
            count[i]++;
            stepCounter.increment();
        }

        int sortedIndex = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                sortedArr[sortedIndex++] = i;
                count[i]--;
                stepCounter.increment();
            }
            updateChart(series, sortedArr, stepCounter.getSteps()); // Chart update after each pass
        }
        updateChart(series, sortedArr, stepCounter.getSteps()); // Final chart update
    }

    // Radix Sort Algorithm
    public static void radixSort(int[] arr, StepCounter stepCounter, int[] sortedArr, Series<Number, Number> series) {
        int max = Arrays.stream(arr).max().getAsInt();
        int exp = 1;
        System.arraycopy(arr, 0, sortedArr, 0, arr.length);
        updateChart(series, sortedArr, stepCounter.getSteps()); // Initial chart update
        while (max / exp > 0) {
            countingSortByDigit(sortedArr, exp, stepCounter, series);
            exp *= 10;
        }
        updateChart(series, sortedArr, stepCounter.getSteps()); // Final chart update
    }

    // Counting Sort helper function for Radix Sort
    private static void countingSortByDigit(int[] arr, int exp, StepCounter stepCounter, Series<Number, Number> series) {
        int[] output = new int[arr.length];
        int[] count = new int[10];
        for (int i : arr) {
            count[(i / exp) % 10]++;
            stepCounter.increment();
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
            stepCounter.increment();
        }

        System.arraycopy(output, 0, arr, 0, arr.length);
        updateChart(series, arr, stepCounter.getSteps()); // Chart update after each digit pass
    }

    // Insertion Sort Algorithm
    public static void insertionSort(int[] arr, StepCounter stepCounter, int[] sortedArr, Series<Number, Number> series) {
        int n = arr.length;
        System.arraycopy(arr, 0, sortedArr, 0, n);
        updateChart(series, sortedArr, stepCounter.getSteps()); // Initial chart update
        for (int i = 1; i < n; i++) {
            int key = sortedArr[i];
            int j = i - 1;
            while (j >= 0 && sortedArr[j] > key) {
                sortedArr[j + 1] = sortedArr[j];
                j--;
                stepCounter.increment();
            }
            sortedArr[j + 1] = key;
            updateChart(series, sortedArr, stepCounter.getSteps()); // Chart update within loop
        }
        updateChart(series, sortedArr, stepCounter.getSteps()); // Final chart update
    }

    // Method to update the chart with the latest array state
    public static void updateChart(Series<Number, Number> series, int[] sortedArr, int step) {
        series.getData().clear();
        for (int i = 0; i < sortedArr.length; i++) {
            series.getData().add(new XYChart.Data<>(i, sortedArr[i]));
        }
    }
}
