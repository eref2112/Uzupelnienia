package pl.softronic.szkolenie.java.uzupelnienia;

import pl.softronic.szkolenie.java.uzupelnienia.watki.Wspolbieznosc;
import pl.softronic.szkolenie.java.uzupelnienia.watki.zaawansowane.ThreadAndRunnable;

import java.io.FileNotFoundException;

public class Kontroler {
    OperacjeInOut opInOut;
    Kolekcje kolekcje;
    ThreadAndRunnable tr;
    Wspolbieznosc wsp;

    public void uruchomProgram() {
        //Testujemy operacje wczytywania i zapisu
        //opInOut = new OperacjeInOut();
        //opInOut.testujOperacjeInOut();

        /*
        //Chwilowy test
        try {
            opInOut.testException();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        */


        //Testujemy instrukcje do i While

        //InstrukcjeDoWhileSwitch.testWhile();
        //InstrukcjeDoWhileSwitch.testDoWhile();
        //InstrukcjeDoWhileSwitch.testScanner();
        //InstrukcjeDoWhileSwitch.testSwitch(ZnanaOsoba.Mozart);

        //Kolekcje kolekcje = new Kolekcje();
        //kolekcje.testujVector();
        //kolekcje.testujList();
        //kolekcje.testujSet();
        //kolekcje.testujMap();

        //ThreadAndRunnable tar = new ThreadAndRunnable();
        //ExecutorServiceExamples ese = new ExecutorServiceExamples();
        //FutureSubmitRunnable fsr = new FutureSubmitRunnable();
        //AtomicClasses ac = new AtomicClasses();
        //WaitNotify wn = new WaitNotify();


        //tar.testuj();
        //ese.testuj();
        //fsr.testuj();
        //ac.testuj();
        //wn.testuj();

        wsp = new Wspolbieznosc();
        wsp.testuj();
        //wsp.testuj1();

    }


}
