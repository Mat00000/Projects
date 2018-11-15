public class ElementQueue {
    private int value;
    private double priority;

    ElementQueue(int value, double priority) {
        this.value = value;
        this.priority = priority;
    }

    public int getValue() {
        return value;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public double getPriority() {
        return priority;
    }


}
