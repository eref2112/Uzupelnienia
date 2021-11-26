package pl.softronic.szkolenie.java.uzupelnienia.watki;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Wspolbieznosc {

    public void testuj() {
        Powitania powitania = new Powitania();
        Pozegnania pozegnania = new Pozegnania();

        Powitania1 powitania1 = new Powitania1();
        Pozegnania1 pozegnania1 = new Pozegnania1();

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(powitania);
        executor.execute(pozegnania);

        executor.execute(powitania1);
        executor.execute(pozegnania1);

    }
}

class Powitania implements Runnable{

    @Override
    public void run() {
        for (int i = 1; i <= 1000; i++) {
            try {
                Thread.sleep(1300);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Witaj " + i);
        }
    }
}

class Pozegnania implements Runnable{

    @Override
    public void run() {
        for (int i = 1; i <= 1000; i++) {
            try {
                Thread.sleep(500);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Żegnaj " + i);
        }

    }
}


class Powitania1 extends Thread{

    @Override
    public void run() {
        for (int i = 1; i <= 1000; i++) {
            try {
                Thread.sleep(800);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Witaj thread" + i);
        }
    }
}

class Pozegnania1  extends Thread{

    @Override
    public void run() {
        for (int i = 1; i <= 1000; i++) {
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Żegnaj thread " + i);
        }

    }
}