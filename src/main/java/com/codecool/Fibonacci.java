package com.codecool;

import java.math.BigInteger;
import java.util.function.Supplier;

public class Fibonacci implements Supplier<BigInteger> {
    private BigInteger current = new BigInteger("0");
    private BigInteger next = new BigInteger("1");

    @Override
    public BigInteger get() {
        BigInteger value = current;
        sleep();
        current = next;
        sleep();
        next = next.add(value);
        sleep();
        return value;
    }

    public BigInteger atomicGet() {
        synchronized (this) {
            return get();
        }
    }


    private void sleep() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
