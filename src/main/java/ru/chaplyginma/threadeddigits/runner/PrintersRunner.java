package ru.chaplyginma.threadeddigits.runner;

import ru.chaplyginma.threadeddigits.counter.Counter;
import ru.chaplyginma.threadeddigits.printer.DigitsPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PrintersRunner {

    private final Counter counter;

    public PrintersRunner(int max) {
        this.counter = new Counter(max);
    }

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
