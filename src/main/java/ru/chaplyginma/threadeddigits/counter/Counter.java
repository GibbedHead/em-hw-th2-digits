package ru.chaplyginma.threadeddigits.counter;

/**
 * The Counter class represents a simple counter that tracks
 * the current value and a maximum value it can reach.
 */
public class Counter {

    private final int max;
    private int count = 0;

    /**
     * Constructs a Counter instance with a specified maximum value.
     *
     * @param max the maximum value for the counter
     */
    public Counter(final int max) {
        this.max = max;
    }

    /**
     * Increments the current count by one.
     */
    public void increment() {
        count++;
    }

    /**
     * Retrieves the current count value.
     *
     * @return the current count
     */
    public int getCount() {
        return count;
    }

    /**
     * Checks if the current count is less than the maximum value.
     *
     * @return true if the current count is less than max, otherwise false
     */
    public boolean isLesserThanMax() {
        return count < max;
    }
}
