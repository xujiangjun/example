package com.xujiangjun.example.web.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author xujiangjun
 * @date 2017-09-12 15:26
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);

        Waiter waiter      = new Waiter(latch);
        Decrementer decrementer = new Decrementer(latch);
        new Thread(waiter).start();
        new Thread(decrementer).start();

        Thread.sleep(2000);
    }

    public static class Waiter implements Runnable{

        CountDownLatch latch = null;

        public Waiter(CountDownLatch latch) {
            this.latch = latch;
        }

        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Waiter Released");
        }
    }

    private static class Decrementer implements Runnable {

        CountDownLatch latch = null;

        public Decrementer(CountDownLatch latch) {
            this.latch = latch;
        }

        public void run() {

            try {
                Thread.sleep(1000);
                this.latch.countDown();
                System.out.println("countDown 1...");

                Thread.sleep(1000);
                this.latch.countDown();
                System.out.println("countDown 2...");

                Thread.sleep(1000);
                this.latch.countDown();
                System.out.println("countDown 3...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("countDown finish...");
        }
    }
}
