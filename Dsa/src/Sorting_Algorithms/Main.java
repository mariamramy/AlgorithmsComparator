package Sorting_Algorithms;
import java.util.*;
public class Main extends Sorting {

	public static void main(String[] args) {
	Scanner i = new Scanner(System.in);
	System.out.print("enter the size of array : ");
	int size = i.nextInt();
	int [] arr = generateRandomArray(size);
	System.out.print("generated array: ");
	printArray(arr);
	chooseAlgorithm(arr);
	}

}
