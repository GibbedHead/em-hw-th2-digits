package ru.chaplyginma.threadeddigits.printer;

import ru.chaplyginma.threadeddigits.counter.Counter;

import java.util.function.Predicate;

public class DigitsPrinter extends Thread {


    private final Counter counter;
    private final Predicate<Integer> predicate;

    public DigitsPrinter(String name, Counter counter, final Predicate<Integer> predicate) {
        super(name);
        this.counter = counter;
        this.predicate = predicate;
    }

    @Override
    public void run() {
        synchronized (counter) {
            while (!counter.isMaxReached()) {
                while (!predicate.test(counter.getCount())) {
                    try {
                        counter.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                System.out.printf("%s: %d%n", Thread.currentThread().getName(), counter.getCount());
                counter.increment();
                counter.notifyAll();
            }
        }
    }
}
