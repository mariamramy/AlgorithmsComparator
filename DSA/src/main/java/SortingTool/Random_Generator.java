package SortingTool;

import java.util.Random;

public class Random_Generator {
    // Method to generate random array
    public static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        Random rand = new Random(); // Generating random numbers

        int range = size * 10; // Maximum value for random numbers

        // Generate and store the values in the array
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(range + 1); // Generates numbers from 0 to range
        }
        return arr;
    }
}
