package com.codecool;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {

        Fibonacci fib = new Fibonacci();

        ExecutorService executor = Executors.newFixedThreadPool(5);

        List<Future<BigInteger>> futures = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            futures.add(executor.submit(fib::get));
        }

        System.out.println("Fibonacci:");
        printFutures(futures);

        Fibonacci fib2 = new Fibonacci();

        List<Future<BigInteger>> futures2 = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            futures2.add(executor.submit(fib2::atomicGet));
        }

        System.out.println("Atomic fibonacci:");
        printFutures(futures2);

        executor.shutdown();
    }

    private static void printFutures(List<Future<BigInteger>> futures) {
        futures.stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                    return new BigInteger("0");
                }).sorted().forEach(System.out::println);
    }
}
