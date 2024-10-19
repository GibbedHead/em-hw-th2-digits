package ru.chaplyginma;

import ru.chaplyginma.threadeddigits.runner.PrintersRunner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        PrintersRunner runner = new PrintersRunner(100);

        runner.print();
    }
}