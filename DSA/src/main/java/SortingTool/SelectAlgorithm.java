package SortingTool;

import javafx.scene.chart.XYChart;

import java.util.Arrays;
import java.util.Scanner;

public class SelectAlgorithm {

    public static void chooseAlgorithms() {
        Scanner scanner = new Scanner(System.in);

        // List of available algorithms
        String[] algorithms = {
                "Bubble Sort", "Counting Sort", "Quick Sort",
                "Selection Sort", "Heap Sort", "Radix Sort",
                "Merge Sort", "Insertion Sort", "Shell Sort", "Bucket Sort"
        };

        System.out.println("Write 1 to compare one algorithm or 2 to compare multiple algorithms: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume the newline left over by nextInt()

        System.out.print("Enter the size of the array to be sorted: ");
        int size = scanner.nextInt();

        // Generate a random array
        int[] arr = SortingTool.Random_Generator.generateRandomArray(size);
        System.out.println("Original Array:");
        System.out.println(Arrays.toString(arr));
        printSeparator();
        int[] sortedArr = new int[size];

        // Creating a sample XYChart.Series for visual updates (can be customized as needed)
        XYChart.Series<Number, Number> series = new XYChart.Series<>();

        if (choice == 1) {
            // Choose one algorithm for comparison
            System.out.println("Please choose one algorithm to compare:");
            displayAlgorithms(algorithms);

            int chosenAlgorithm = scanner.nextInt();
            runAlgorithm(chosenAlgorithm, arr, sortedArr, series);

        } else if (choice == 2) {
            // Choose multiple algorithms for comparison
            System.out.println("Please choose multiple algorithms to compare (comma separated numbers):");
            displayAlgorithms(algorithms);
            scanner.nextLine();  // Consume the newline left over by nextInt()
            String chosenAlgorithms = scanner.nextLine();
            String[] chosenList = chosenAlgorithms.split(",");

            for (String algo : chosenList) {
                int algorithmChoice = Integer.parseInt(algo.trim());
                runAlgorithm(algorithmChoice, arr, sortedArr, series);
            }
        } else {
            System.out.println("Invalid choice! Please restart the program.");
            System.exit(0);
        }
        System.out.println("Sorted Array:");
        System.out.println(Arrays.toString(arr));
        scanner.close();
    }

    private static void displayAlgorithms(String[] algorithms) {
        for (int i = 0; i < algorithms.length; i++) {
            System.out.println((i + 1) + ". " + algorithms[i]);
        }
    }

    private static void runAlgorithm(int choice, int[] arr, int[] sortedArr, XYChart.Series<Number, Number> series) {
        StepCounter stepCounter = new StepCounter();
        switch (choice) {
            case 1:
                SortingTool.Algorithms.bubbleSort(arr, stepCounter, sortedArr, series);
                System.out.println("Bubble Sort: " + stepCounter.getSteps() + " steps");
                printSeparator();
                break;
            case 2:
                SortingTool.Algorithms.countingSort(arr, stepCounter, sortedArr, series);
                System.out.println("Counting Sort: " + stepCounter.getSteps() + " steps");
                printSeparator();
                break;
            case 3:
                SortingTool.Algorithms.quickSort(arr, 0, arr.length - 1, stepCounter, sortedArr, series);
                System.out.println("Quick Sort: " + stepCounter.getSteps() + " steps");
                printSeparator();
                break;
            case 4:
                SortingTool.Algorithms.selectionSort(arr, stepCounter, sortedArr, series);
                System.out.println("Selection Sort: " + stepCounter.getSteps() + " steps");
                printSeparator();
                break;
            case 5:
                SortingTool.Algorithms.heapSort(arr, stepCounter, sortedArr, series);
                System.out.println("Heap Sort: " + stepCounter.getSteps() + " steps");
                printSeparator();
                break;
            case 6:
                SortingTool.Algorithms.radixSort(arr, stepCounter, sortedArr, series);
                System.out.println("Radix Sort: " + stepCounter.getSteps() + " steps");
                printSeparator();
                break;
            case 7:
                SortingTool.Algorithms.mergeSort(arr, 0, arr.length - 1, stepCounter, sortedArr, series);
                System.out.println("Merge Sort: " + stepCounter.getSteps() + " steps");
                printSeparator();
                break;
            case 8:
                SortingTool.Algorithms.insertionSort(arr, stepCounter, sortedArr, series);
                System.out.println("Insertion Sort: " + stepCounter.getSteps() + " steps");
                printSeparator();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private static void printSeparator() {
        System.out.println("\n---------------------------------------------------------\n");
    }
}
