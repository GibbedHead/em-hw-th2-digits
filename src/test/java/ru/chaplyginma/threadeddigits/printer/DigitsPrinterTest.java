package ru.chaplyginma.threadeddigits.printer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.chaplyginma.threadeddigits.counter.Counter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

class DigitsPrinterTest {

    @Test
    @DisplayName("Test even and odd printers print digits one after another")
    void givenEvenAndOddPrinters_whenPrint_thenPrintDigitsOneAfterAnother() throws InterruptedException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        Counter counter = new Counter(10);

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

        System.setOut(originalOut);
        String output = outputStream.toString();
        String[] lines = output.split(System.lineSeparator());

        assertThat(lines).hasSize(10);

        for (int i = 0; i < lines.length; i++) {
            int number = Integer.parseInt(lines[i].split(":")[1].trim());
            if (i % 2 == 0) {
                assertThat(number).isEven();
            } else {
                assertThat(number).isOdd();
            }
        }
    }

}