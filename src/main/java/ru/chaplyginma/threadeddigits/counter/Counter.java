package ru.chaplyginma.threadeddigits.counter;

public class Counter {

    private final int max;
    private int count = 0;

    public Counter(final int max) {
        this.max = max;
    }

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }

    public boolean isMaxReached() {
        return count >= max;
    }
}
