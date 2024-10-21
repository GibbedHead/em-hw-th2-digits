package ru.chaplyginma.threadeddigits.counter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CounterTest {

    @Test
    @DisplayName("Test initial count")
    void givenNewlyCreatedCounter_whenGetCount_thenReturnZero() {
        Counter counter = new Counter(5);

        assertThat(counter.getCount()).isZero();
    }

    @Test
    @DisplayName("Test increment")
    void givenNewlyCreatedCounter_whenIncrement_thenReturnOne() {
        Counter counter = new Counter(5);

        counter.increment();

        assertThat(counter.getCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("Test new counter with max = 5 is lesser than max is true")
    void givenCounterWithPositiveMax_whenIsLesserThanMax_thenReturnTrue() {
        Counter counter = new Counter(5);

        assertThat(counter.isLesserThanMax()).isTrue();
    }

    @Test
    @DisplayName("Test counter with max 5 incremented 5 times is lesser than max false")
    void givenCounterWith5MaxIncremented5Times_whenIsLesserThanMax_thenReturnFalse() {
        Counter counter = new Counter(5);
        counter.increment();
        counter.increment();
        counter.increment();
        counter.increment();
        counter.increment();
        assertThat(counter.isLesserThanMax()).isFalse();
    }
}