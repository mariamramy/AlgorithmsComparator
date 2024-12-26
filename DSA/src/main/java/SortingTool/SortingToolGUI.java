package SortingTool;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;

public class SortingToolGUI extends Application {

    // Shared Components
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Sorting Algorithms GUI");
        HomePage();
    }

    private void HomePage() {
        VBox homeLayout = new VBox(20);
        homeLayout.setPadding(new Insets(20));

        Label title = new Label("Sorting Tool - Choose Mode");
        Button singleSortButton = new Button("Single Algorithm Sort");
        Button multiSortButton = new Button("Multi Algorithm Sort");

        singleSortButton.setOnAction(e -> SingleSortPage());
        multiSortButton.setOnAction(e -> MultiSortPage());

        homeLayout.getChildren().addAll(title, singleSortButton, multiSortButton);

        Scene homeScene = new Scene(homeLayout, 400, 200);
        primaryStage.setScene(homeScene);
        primaryStage.show();
    }

    private void SingleSortPage() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        TextArea arrayInput = new TextArea();
        arrayInput.setPromptText("Enter numbers separated by commas (e.g., 5,3,8,1)");

        ComboBox<String> algorithmChoice = new ComboBox<>();
        algorithmChoice.getItems().addAll("Bubble Sort", "Quick Sort", "Merge Sort", "Selection Sort", "Heap Sort", "Counting Sort", "Radix Sort", "Insertion Sort");
        algorithmChoice.setPromptText("Select Algorithm");

        TextField sizeInput = new TextField();
        sizeInput.setPromptText("Enter array size");

        Button generateButton = new Button("Generate Random Array");
        Button sortButton = new Button("Sort");
        Button backButton = new Button("Back to Home");

        TextArea sortedOutput = new TextArea();
        sortedOutput.setEditable(false);
        TextField stepCounter = new TextField();
        stepCounter.setEditable(false);
        stepCounter.setPromptText("Step Counter");

        // Line chart setup
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Step");
        yAxis.setLabel("Array Value");

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Sorting Progress");

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Array Changes");

        generateButton.setOnAction(e -> generateRandomArray(sizeInput, arrayInput));
        sortButton.setOnAction(e -> performSingleSort(arrayInput, algorithmChoice, sortedOutput, stepCounter, series));
        backButton.setOnAction(e -> HomePage());

        layout.getChildren().addAll(arrayInput, algorithmChoice, sizeInput, generateButton, sortButton, sortedOutput, stepCounter, lineChart, backButton);

        Scene scene = new Scene(layout, 600, 500);
        primaryStage.setScene(scene);
    }

    private void MultiSortPage() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        TextArea arrayInput = new TextArea();
        arrayInput.setPromptText("Enter numbers separated by commas (e.g., 5,3,8,1)");

        ComboBox<String> algorithmChoice1 = new ComboBox<>();
        ComboBox<String> algorithmChoice2 = new ComboBox<>();
        algorithmChoice1.getItems().addAll("Bubble Sort", "Quick Sort", "Merge Sort", "Selection Sort", "Heap Sort", "Counting Sort", "Radix Sort", "Insertion Sort");
        algorithmChoice2.getItems().addAll("Bubble Sort", "Quick Sort", "Merge Sort", "Selection Sort", "Heap Sort", "Counting Sort", "Radix Sort", "Insertion Sort");
        algorithmChoice1.setPromptText("Select Algorithm 1");
        algorithmChoice2.setPromptText("Select Algorithm 2");

        TextField sizeInput = new TextField();
        sizeInput.setPromptText("Enter array size");

        Button generateButton = new Button("Generate Random Array");
        Button sortButton = new Button("Sort");
        Button backButton = new Button("Back to Home");

        TextArea sortedOutput1 = new TextArea();
        TextArea sortedOutput2 = new TextArea();
        sortedOutput1.setEditable(false);
        sortedOutput2.setEditable(false);

        TextField stepCounter1 = new TextField();
        TextField stepCounter2 = new TextField();
        stepCounter1.setEditable(false);
        stepCounter2.setEditable(false);

        // Line chart setup
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Step");
        yAxis.setLabel("Array Value");

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Sorting Progress");

        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
        series1.setName("Algorithm 1 Progress");
        series2.setName("Algorithm 2 Progress");

        generateButton.setOnAction(e -> generateRandomArray(sizeInput, arrayInput));
        sortButton.setOnAction(e -> performMultiSort(arrayInput, algorithmChoice1, sortedOutput1, stepCounter1, algorithmChoice2, sortedOutput2, stepCounter2, series1, series2, lineChart));
        backButton.setOnAction(e -> HomePage());

        layout.getChildren().addAll(arrayInput, algorithmChoice1, algorithmChoice2, sizeInput, generateButton, sortButton, sortedOutput1, stepCounter1, sortedOutput2, stepCounter2, lineChart, backButton);

        Scene scene = new Scene(layout, 600, 600);
        primaryStage.setScene(scene);
    }


    private void generateRandomArray(TextField sizeInput, TextArea arrayInput) {
        int size;
        try {
            size = Integer.parseInt(sizeInput.getText());
        } catch (NumberFormatException e) {
            arrayInput.setText("Please enter a valid number for the size.");
            return;
        }

        if (size <= 0) {
            arrayInput.setText("Array size must be a positive number.");
            return;
        }

        int[] randomArray = Random_Generator.generateRandomArray(size);

        // Convert the generated array into a string for display in the TextArea
        StringBuilder arrayString = new StringBuilder();
        for (int i = 0; i < randomArray.length; i++) {
            arrayString.append(randomArray[i]);
            if (i < randomArray.length - 1) {
                arrayString.append(",");
            }
        }

        arrayInput.setText(arrayString.toString());
    }

    private void performSingleSort(TextArea input, ComboBox<String> algorithm, TextArea output, TextField steps, XYChart.Series<Number, Number> series) {
        String inputText = input.getText();
        String[] inputArray = inputText.split(",");
        int[] arr = Arrays.stream(inputArray).mapToInt(Integer::parseInt).toArray();
        int[] sortedArr = new int[arr.length];
        StepCounter stepCounter = new StepCounter();

        // Clear previous series data
        series.getData().clear();

        // Call the selected algorithm
        switch (algorithm.getValue()) {
            case "Bubble Sort":
                Algorithms.bubbleSort(arr, stepCounter, sortedArr, series);
                break;
            case "Quick Sort":
                Algorithms.quickSort(arr, 0, arr.length - 1, stepCounter, sortedArr, series);
                break;
            case "Merge Sort":
                Algorithms.mergeSort(arr, 0, arr.length - 1, stepCounter, sortedArr, series);
                break;
            case "Selection Sort":
                Algorithms.selectionSort(arr, stepCounter, sortedArr, series);
                break;
            case "Heap Sort":
                Algorithms.heapSort(arr, stepCounter, sortedArr, series);
                break;
            case "Counting Sort":
                Algorithms.countingSort(arr, stepCounter, sortedArr, series);
                break;
            case "Radix Sort":
                Algorithms.radixSort(arr, stepCounter, sortedArr, series);
                break;
            case "Insertion Sort":
                Algorithms.insertionSort(arr, stepCounter, sortedArr, series);
                break;
        }

        // Display results
        output.setText(Arrays.toString(sortedArr));
        steps.setText("Steps: " + stepCounter.getSteps());
    }


    private void performMultiSort(TextArea input, ComboBox<String> algo1, TextArea out1, TextField step1, ComboBox<String> algo2, TextArea out2, TextField step2, XYChart.Series<Number, Number> series1, XYChart.Series<Number, Number> series2, LineChart<Number, Number> lineChart) {
        String inputText = input.getText();
        String[] inputArray = inputText.split(",");
        int[] arr = Arrays.stream(inputArray).mapToInt(Integer::parseInt).toArray();
        int[] sortedArr1 = new int[arr.length];
        int[] sortedArr2 = new int[arr.length];
        StepCounter stepCounter1 = new StepCounter();
        StepCounter stepCounter2 = new StepCounter();

        // Clear previous series data
        series1.getData().clear();
        series2.getData().clear();

        // Call the selected algorithms and plot progress
        switch (algo1.getValue()) {
            case "Bubble Sort":
                Algorithms.bubbleSort(arr, stepCounter1, sortedArr1, series1);
                break;
            case "Quick Sort":
                Algorithms.quickSort(arr, 0, arr.length - 1, stepCounter1, sortedArr1, series1);
                break;
            case "Merge Sort":
                Algorithms.mergeSort(arr, 0, arr.length - 1, stepCounter1, sortedArr1, series1);
                break;
            case "Selection Sort":
                Algorithms.selectionSort(arr, stepCounter1, sortedArr1, series1);
                break;
            case "Heap Sort":
                Algorithms.heapSort(arr, stepCounter1, sortedArr1, series1);
                break;
            case "Counting Sort":
                Algorithms.countingSort(arr, stepCounter1, sortedArr1, series1);
                break;
            case "Radix Sort":
                Algorithms.radixSort(arr, stepCounter1, sortedArr1, series1);
                break;
            case "Insertion Sort":
                Algorithms.insertionSort(arr, stepCounter1, sortedArr1, series1);
                break;
        }

        switch (algo2.getValue()) {
            case "Bubble Sort":
                Algorithms.bubbleSort(arr, stepCounter2, sortedArr2, series2);
                break;
            case "Quick Sort":
                Algorithms.quickSort(arr, 0, arr.length - 1, stepCounter2, sortedArr2, series2);
                break;
            case "Merge Sort":
                Algorithms.mergeSort(arr, 0, arr.length - 1, stepCounter2, sortedArr2, series2);
                break;
            case "Selection Sort":
                Algorithms.selectionSort(arr, stepCounter2, sortedArr2, series2);
                break;
            case "Heap Sort":
                Algorithms.heapSort(arr, stepCounter2, sortedArr2, series2);
                break;
            case "Counting Sort":
                Algorithms.countingSort(arr, stepCounter2, sortedArr2, series2);
                break;
            case "Radix Sort":
                Algorithms.radixSort(arr, stepCounter2, sortedArr2, series2);
                break;
            case "Insertion Sort":
                Algorithms.insertionSort(arr, stepCounter2, sortedArr2, series2);
                break;
        }

        // Display results and step counts
        out1.setText(Arrays.toString(sortedArr1));
        step1.setText("Steps: " + stepCounter1.getSteps());
        out2.setText(Arrays.toString(sortedArr2));
        step2.setText("Steps: " + stepCounter2.getSteps());

        // Add the series to the chart
        lineChart.getData().addAll(series1, series2);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
