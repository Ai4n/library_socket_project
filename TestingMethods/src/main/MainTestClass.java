package main;

import java.util.ArrayList;
import com.google.gson.*;

public class MainTestClass {

    public static void main(String[] args) throws Exception {
        final String resource1 = "Danil Vassyakin";
        final String resource2 = "Akhan Beisenbayev";

        Thread tt1 = new Thread() {
            @Override
            public void run() {
                synchronized (resource1) {
                    System.out.println("Thread 1: locked resource 1");

                    try {
                        Thread.sleep(50);
                    } catch (Exception e) {
                    }

                    synchronized (resource2) {
                        System.out.println("Thread 1: locked resource 2");
                    }
                }
            }
        };
        Thread tt2 = new Thread() {
            @Override
            public void run() {
                synchronized (resource2) {
                    System.out.println("Thread 2: locked resource 2");

                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                    }

                    synchronized (resource1) {
                        System.out.println("Thread 2: locked resource 1");
                    }
                }
            }
        };

        ThreadTest tt3 = new ThreadTest(1, 3, 567777777);
        tt1.start();
        tt2.start();
        tt3.start();
    }
}
/**
 * Race conditions Test example
 */
//
//        public class MainTestClass{
//            public static void main(String[] args) {
//                Counter counter = new Counter();
//                Thread t1 = new Thread(counter, "Thread-1");
//                Thread t2 = new Thread(counter, "Thread-2");
//                Thread t3 = new Thread(counter, "Thread-3");
//                t1.start();
//                t2.start();
//                t3.start();
//            }
//        }
/**
 *  Starvation Test visible example
 */
//public class MainTestClass {
//
//    public static void main (String[] args) {
//        JFrame frame = createFrame();
//        frame.setLayout(new FlowLayout(FlowLayout.LEFT));
//
//        for (int i = 0; i < 5; i++) {
//            ProgressThread progressThread = new ProgressThread();
//            frame.add(progressThread.getProgressComponent());
//            progressThread.start();
//        }
//
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//    }
//
//    private static JFrame createFrame () {
//        JFrame frame = new JFrame("Starvation Demo");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(new Dimension(300, 200));
//        return frame;
//    }
//}
/**
 * Semaphore test example
 */
//max 4 people
//
//            static int shared = 0;
//            static Semaphore semaphore = new Semaphore(1);
//
//            static class MyATMThread extends Thread {
//
//                String name = "";
//
//                MyATMThread(String name) {
//                    this.name = name;
//                }
//
//                public void run() {
//                    try {
//                        System.out.println(name + " : acquiring lock...");
//                        System.out.println(name + " : available Semaphore permits now: "
//                                + semaphore.availablePermits());
//                        semaphore.acquire();
//                        System.out.println(name + " : got the permit!");
//                        try {
//                            for (int i = 1; i <= 5; i++) {
//
//                                System.out.println(name + " : SHARED " + ++shared
//                                        + ", available Semaphore permits : "
//                                        + semaphore.availablePermits());
//
//                                // sleep 1 second
//                                Thread.sleep(1000);
//
//                            }
//                        } finally {
//                            // calling release() after a successful acquire()
//                            System.out.println(name + " : releasing lock...");
//                            semaphore.release();
//                            System.out.println(name + " : available Semaphore permits now: "
//                                    + semaphore.availablePermits());
//                        }
//
//                    } catch (InterruptedException e) {
//
//                        e.printStackTrace();
//
//                    }
//
//                }
//
//            }
//
//            public static void main(String[] args) throws InterruptedException {
//
//                System.out.println("Total available Semaphore permits : "
//                        + semaphore.availablePermits());
//
//                MyATMThread t1 = new MyATMThread("A");
//                t1.start();
//
//                MyATMThread t2 = new MyATMThread("B");
//                t2.start();
//
//                MyATMThread t3 = new MyATMThread("C");
//                t3.start();
//
//                MyATMThread t4 = new MyATMThread("D");
//                t4.start();
//
//                MyATMThread t5 = new MyATMThread("E");
//                t5.start();
//
//                MyATMThread t6 = new MyATMThread("F");
//                t6.start();
//
//                Thread.sleep(20000);
//                System.out.println("FINALLY " + shared);
//            }
//        }

/**
 *   Mutex conditions Test Example
 *
 */
// max 1 people
//            static Semaphore semaphore = new Semaphore(1);
//
//            static class MyLockerThread extends Thread {
//
//                String name = "";
//
//                MyLockerThread(String name) {
//                    this.name = name;
//                }
//
//                public void run() {
//
//                    try {
//
//                        System.out.println(name + " : acquiring lock...");
//                        System.out.println(name + " : available Semaphore permits now: "
//                                + semaphore.availablePermits());
//
//                        semaphore.acquire();
//                        System.out.println(name + " : got the permit!" + semaphore.availablePermits());
//
//                        try {
//
//                            for (int i = 1; i <= 5; i++) {
//
//                                System.out.println(name + " : is performing operation " + i
//                                        + ", available Semaphore permits : "
//                                        + semaphore.availablePermits());
//
//                                // sleep 1 second
//                                Thread.sleep(1000);
//
//                            }
//
//                        } finally {
//
//                            // calling release() after a successful acquire()
//                            System.out.println(name + " : releasing lock...");
//                            semaphore.release();
//                            System.out.println(name + " : available Semaphore permits now: "
//                                    + semaphore.availablePermits());
//
//                        }
//
//                    } catch (InterruptedException e) {
//
//                        e.printStackTrace();
//
//                    }
//
//                }
//
//            }
//
//            public static void main(String[] args) {
//
//                System.out.println("Total available Semaphore permits : "
//                        + semaphore.availablePermits());
//
//                MyLockerThread t1 = new MyLockerThread("A");
//                t1.start();
//
//                MyLockerThread t2 = new MyLockerThread("B");
//                t2.start();
//
//                MyLockerThread t3 = new MyLockerThread("C");
//                t3.start();
//
//                MyLockerThread t4 = new MyLockerThread("D");
//                t4.start();
//
//                MyLockerThread t5 = new MyLockerThread("E");
//                t5.start();
//
//                MyLockerThread t6 = new MyLockerThread("F");
//                t6.start();
//
//            }
//        }
/**
 * Fairness Solve Problems Test
 */
//
//public class MainTestClass {
//
//    public static void main (String[] args) {
//
//        JFrame frame = createFrame();
//        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
//
//        for (int i = 0; i < 5; i++) {
//            ProgressThread progressThread = new ProgressThread();
//            frame.add(progressThread.getProgressComponent());
//            progressThread.start();
//            System.out.println( UUID.randomUUID().toString());
//        }
//
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//    }
//
//    private static JFrame createFrame () {
//        JFrame frame = new JFrame("Starvation Demo");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(new Dimension(300, 200));
//        return frame;
//    }
//}