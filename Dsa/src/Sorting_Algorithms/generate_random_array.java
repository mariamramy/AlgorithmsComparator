package Sorting_Algorithms;
import java.util.*;
public class generate_random_array {
	public static int[] generateRandomArray(int size) {
        int[] arr =new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(1001);
        }
        return arr;
    }
    public static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
