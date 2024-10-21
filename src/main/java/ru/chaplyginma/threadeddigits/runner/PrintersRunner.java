package ru.chaplyginma.threadeddigits.runner;

import ru.chaplyginma.threadeddigits.counter.Counter;
import ru.chaplyginma.threadeddigits.printer.DigitsPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * This class manages the printing of numbers by multiple threads, alternating between even and odd numbers.
 * It creates two {@link DigitsPrinter} threads, one for even numbers and one for odd numbers.
 * The threads are started and joined, ensuring that they complete their printing tasks before the main thread continues.
 */
public class PrintersRunner {

    private final Counter counter;

    /**
     * Constructs a new PrintersRunner with the given maximum value.
     *
     * @param max the maximum value for the counter
     */
    public PrintersRunner(int max) {
        this.counter = new Counter(max);
    }

    /**
     * Starts two threads, one printing even numbers and the other printing odd numbers,
     * ensuring they alternate in printing.
     *
     * @throws InterruptedException if the thread is interrupted
     */
    public void print() throws InterruptedException {
        Predicate<Integer> evenPredicate = c -> c % 2 == 0;
        Predicate<Integer> oddPredicate = c -> c % 2 == 1;

        List<Thread> threads = new ArrayList<>(
                List.of(
                        new DigitsPrinter("Even printer", counter, evenPredicate),
                        new DigitsPrinter("Odd printer", counter, oddPredicate)
                )
        );

        threads.forEach(Thread::start);

        for (Thread thread : threads) {
            thread.join();
        }
    }
}
