package com.codecool;

import java.util.function.LongSupplier;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicFibonacci implements LongSupplier {
    private static AtomicLong current = new AtomicLong(0);
    private static AtomicLong next = new AtomicLong(1);

    @Override
    public long getAsLong() {
        return current.getAndSet(next.getAndUpdate(value -> value + current.get()));
    }
}


