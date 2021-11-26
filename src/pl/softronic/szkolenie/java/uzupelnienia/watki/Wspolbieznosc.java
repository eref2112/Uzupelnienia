package pl.softronic.szkolenie.java.uzupelnienia.watki;

import pl.softronic.szkolenie.java.uzupelnienia.ElementKolekcji;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Wspolbieznosc  {

    public void testuj() {
        Powitania powitania = new Powitania();
        Pozegnania pozegnania = new Pozegnania();

        Powitania1 powitania1 = new Powitania1();
        Pozegnania1 pozegnania1 = new Pozegnania1();

        //Ta klasa zapewnia obsługę wielowątkowości
        ExecutorService executor = Executors.newCachedThreadPool();

        //metoda powitania.run() blokuje wykonanie dalszej częście programu
        //podobnie jak metoda1() z z mettody testuj1()
        //dlatego, że została uruchomiona zwyczjnie aa nie w egzekutorze.

        //ExecutorService zapewnia współbieżność wykonywania metod!!!!
        //powitania.run();

        executor.execute(powitania);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Można uruchomić w egzekutorze, w osobnym wątku metodę jakiejś klasy
        //tylko wtedy, gdy klasa albo dziedziczy po klasie Thread, albo implementuje interfejs Runnable
        //np:
        //class Powitania implements Runnable{
        //class Powitania1 extends Thread{

        //Można albo tak, albo tak z powodu braku wielokrotnego dziedziczenia
        //Natomiast poniższa instrukcja generuje błąd
        //executor.execute(this);

        //wywołujemy metodę kończącą wątek
        powitania.zakonczPrace();

        //executor.execute(pozegnania);
       // executor.execute(powitania1);
       // executor.execute(pozegnania1);


        //UWAGA!!! Koniecznie wywołać metodę shutdown()
        //bo inaczej egzekutor działa i blokuje zakończenie programu
        executor.shutdown();

    }

    //Matoda pokazująca różnicę między wykonaniem wielowątkowym metod execute "executor.execute(powitania)";
    //w metodzie testuj(), a metod metoda1() i metoda2() w metodzie testuj1()

    //Wwaga - metoda metoda1() blokuje wykonanie metody metoda2() z powodu wykonywania pętli nieskończonej
    public void testuj1(){
        this.metoda1();

        //Tu nie wchodzi
        this.metoda2();
    }

    private void metoda1() {
        while(true){
            System.out.println("pętla z metody 1");
        }
    }

    private void metoda2() {
        while(true){
            System.out.println("pętla z metody 2");
        }
    }


}

class Powitania implements Runnable{
    //pole sterujące pracą wątku
    boolean pracuj = true;

    @Override
    public void run() {
        while(pracuj) {
            try {
                Thread.sleep(300);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Witaj , wartosc pracuj = " + pracuj);
        }
    }

    //metoda kończąca pętlę
    public void zakonczPrace(){
        pracuj = false;
    }
}

class Pozegnania implements Runnable{

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(500);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Żegnaj " );
        }

    }
}


class Powitania1 extends Thread{

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(800);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Witaj thread");
        }
    }
}

class Pozegnania1  extends Thread{

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Żegnaj thread ");
        }

    }
}

class NowyElment extends ElementKolekcji implements Runnable {

    public NowyElment(Integer id, String autor, String tytul) {
        super(id, autor, tytul);
    }


    @Override
    public void run() {

    }
}


/*
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
    */

