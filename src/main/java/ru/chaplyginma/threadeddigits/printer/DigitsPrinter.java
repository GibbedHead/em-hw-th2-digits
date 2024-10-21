package ru.chaplyginma.threadeddigits.printer;

import ru.chaplyginma.threadeddigits.counter.Counter;

import java.util.function.Predicate;

/**
 * This class represents a thread that prints numbers according to a given predicate.
 * It uses a shared {@link Counter} to track the numbers being printed and synchronizes access to the counter using
 * {@code synchronized} blocks and {@code wait()}/{@code notifyAll()} methods.
 */
public class DigitsPrinter extends Thread {

    private final Counter counter;
    private final Predicate<Integer> predicate;

    /**
     * Constructs a new DigitsPrinter with the given name, counter, and predicate.
     *
     * @param name      the name of the thread
     * @param counter   the counter to use for printing
     * @param predicate the predicate to apply to the counter's value
     */
    public DigitsPrinter(String name, Counter counter, final Predicate<Integer> predicate) {
        super(name);
        this.counter = counter;
        this.predicate = predicate;
    }

    /**
     * Runs the thread, printing numbers that satisfy the predicate.
     */
    @Override
    public void run() {
        synchronized (counter) {
            while (counter.isLesserThanMax()) {
                if (predicate.test(counter.getCount())) {
                    System.out.printf("%s:\t%d%n", Thread.currentThread().getName(), counter.getCount());
                    counter.increment();
                    counter.notifyAll();
                } else {
                    try {
                        counter.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        }
    }
}
