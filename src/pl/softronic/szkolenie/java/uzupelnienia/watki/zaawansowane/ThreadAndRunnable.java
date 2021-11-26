package pl.softronic.szkolenie.java.uzupelnienia.watki.zaawansowane;

public class ThreadAndRunnable {
    static long i = 0;
    static long j = 0;
    static long k = 0;

    public void testuj() {
        simpleRunnableTest();
        //simpleRunnableTestWithLoop();
        //simpleThreadTestWithLoop();

        //testWithoutSleep();

    };

    private void testWithoutSleep() {
        new Thread(new PrintDataOneLoopWithoutSleep()).start();

        new Thread(() -> {
            while (i < 500000) {
                System.out.println("Mniejsze niż 500");
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void simpleThreadTestWithLoop() {
        System.out.println("Interface runnable - start");

        new PrintThreadDataOneLoop().start();
        new PrintThreadDataTwoLoop().start();

        System.out.println("Interface runnable - end");
    }

    private void simpleRunnableTestWithLoop() {
        System.out.println("Interface runnable - start");

        new Thread(new PrintDataOneLoop()).start();
        new Thread(new PrintDataTwoLoop()).start();

        System.out.println("Interface runnable - end");
    }

    void simpleRunnableTest() {
        System.out.println("Interface runnable - start");

        new Thread(new PrintDataOne()).start();
        new Thread(new PrintDataTwo()).start();
        new Thread(new PrintDataThree()).start();

        System.out.println("Interface runnable - end");
    }

    class PrintDataOne implements Runnable {
        public void run() {
            i++;
            System.out.println("Wątek 1: i = " + i);
        }
    }

    class PrintDataTwo implements Runnable {
        public void run() {
            j = j + 3;
            System.out.println("Wątek 2: j = " + j);
        }
    }

    class PrintDataThree implements Runnable {
        public void run() {
            k = k + 5;
            System.out.println("Wątek 3: k = " + k);
        }
    }

    class PrintDataOneLoopWithoutSleep implements Runnable {
        public void run() {
            while (true) {
                i++;
                System.out.println("Wątek 1 loop: i = " + i);
                if (i == 200000) i = 0;
            }
        }
    }

    class PrintDataOneLoop implements Runnable {
        public void run() {
            while (true) {
                i++;
                System.out.println("Wątek 1 loop: i = " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (i == 200000) i = 0;
            }
        }
    }

    class PrintDataTwoLoop implements Runnable {
        public void run() {
            while (true) {
                j = j +3;;
                System.out.println("Wątek 2 loop: j = " + j);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (j == 300000) j = 0;
            }
        }
    }

    class PrintThreadDataOneLoop extends Thread {
        public void run() {
            while (true) {
                i++;
                System.out.println("Wątek 1 loop: i = " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (i == 200000) i = 0;
            }
        }
    }

    class PrintThreadDataTwoLoop extends Thread {
        public void run() {
            while (true) {
                j = j +3;;
                System.out.println("Wątek 2 loop: j = " + j);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (j == 300000) j = 0;
            }
        }
    }
}
