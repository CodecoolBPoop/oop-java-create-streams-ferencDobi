package com.codecool;

import java.util.function.Supplier;

public class Fibonacci implements Supplier<Integer> {
    private static int current = 0;
    private static int next = 1;

    @Override
    public Integer get() {
        Integer value = current;
        current = next;
        next += value;
        return value;
    }
}
