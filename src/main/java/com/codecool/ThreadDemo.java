package com.codecool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.LongSupplier;

public class ThreadDemo {
    public static void main(String[] args) {

        Fibonacci fib = new Fibonacci();

        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 50; i++) {
            Future<Integer> future = executor.submit(fib::get);

            try {
                System.out.println("Fib: " + future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        LongSupplier fib2 = new AtomicFibonacci();

        for (int i = 0; i < 50; i++) {
            Future<Long> future = executor.submit(fib2::getAsLong);

            try {
                System.out.println("Atomic fib: " + future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
    }
}
