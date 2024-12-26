package SortingTool;

public class StepCounter {
    private int steps;

    public StepCounter() {
        steps = 0;
    }

    public void increment() {
        steps++;
    }

    public int getSteps() {
        return steps;
    }

    public void reset() {
    }
}
