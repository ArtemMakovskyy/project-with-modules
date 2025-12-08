package com.store.threads;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import lombok.SneakyThrows;

public class StartVirtualThreadExample {
    @SneakyThrows
    public static void main(String[] args) {
//        a();
//        b();
//        c();
        runVirtualTasks();
    }

    @SneakyThrows
    private static void a() {
        Thread start = Thread.ofVirtual().start(() -> a1());

        Thread start1 = Thread.ofVirtual().start(
                () -> System.out.println("Привет и з виртуального аотока"));


        start.join();
        start1.join();

        b();
    }

    private static void a1() {
        System.out.println("Starting void a thread");
    }

    @SneakyThrows
    private static void b() {
        Thread startingVoidBThread =
                Thread.startVirtualThread(
                        () -> System.out.println("Starting void b thread")
                );
        startingVoidBThread.join();
    }

    @SneakyThrows
    private static void c() {
//              Преимущества виртуальных потоков:
//        - Подходит для большого количества задач
//        - Совместим с ExecutorService, Callable, Future
//        - Автоматически завершает все потоки при закрытии
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            executor.submit(() -> System.out.println("Executors.newVirtualThreadPerTaskExecutor()"));
        }

    }

    public static void runVirtualTasks() throws InterruptedException, ExecutionException {
        List<Callable<String>> tasks = List.of(
                () -> "task 1",
                () -> "task 2",
                () -> "task 3"
        );

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            var results = executor.invokeAll(tasks);
            for (var result : results) {
                System.out.println(result.get());
            }
        }
    }

    //Java in the future will use STRUCTURED concurrency
    //ScopedValues вместо ThreadLocal


}
