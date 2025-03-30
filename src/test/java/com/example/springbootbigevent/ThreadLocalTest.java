package com.example.springbootbigevent;

import org.junit.jupiter.api.Test;

public class ThreadLocalTest {
    @Test
    public void testThreadLocal() {
        ThreadLocal tl = new ThreadLocal<>();

        new Thread(() -> {
            tl.set("1");
            System.out.println(Thread.currentThread().getName() + ": " + tl.get());
        }, "1").start();

        new Thread(() -> {
            tl.set("2");
            System.out.println(Thread.currentThread().getName() + ": " + tl.get());
        }, "2").start();
    }
}
