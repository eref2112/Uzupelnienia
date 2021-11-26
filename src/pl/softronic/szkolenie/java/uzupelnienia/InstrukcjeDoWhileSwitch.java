package pl.softronic.szkolenie.java.uzupelnienia;

import java.util.Objects;
import java.util.Scanner;

public class InstrukcjeDoWhileSwitch {

    static void testWhile() {
        int i = 0;
        while (i < 5) {
            i++;
            System.out.println("1. Poczatek pętli : " + i);
            //if (i == 4) return;
            //if (i == 4) break;
            if (i == 3) continue;

            System.out.println("1. Koniec pętli : " + i);

        }
        System.out.println("1. Ostatnia instrukcja w 1: " + i);

    }

    static void testDoWhile() {
        int i = 0;
        do {
            i++;
            System.out.println("2. Wykonuję coś dla i równego : " + i);
        } while (i < 10);

        System.out.println("2. Ostatnia instrukcja w 2: " + i);
    }

     static void testScanner() {
        System.out.println("Wpisuj dane, zakończ przez '/q'");

        Scanner in = new Scanner(System.in);

        String daneWejsciowe = "";
        String dana = "";

        while (in.hasNext()) {
            dana = in.next();
            daneWejsciowe = daneWejsciowe + " " + dana;

            System.out.println("Wpisałeś: " + dana);
            System.out.println("Wprowadzone dane: " + daneWejsciowe);

            if (Objects.equals(dana, "/q")) {
                in.close();
                return;
            }
        }
    }

    static void testSwitch(ZnanaOsoba znanaOsoba) {
        String title = switch (znanaOsoba) {
            case Dali, Picasso      -> "malarzem";
            case Mozart, Prokofiev  -> "kompozytorem";
            case Goethe, Dostoevsky -> "pisarzem";
            default                 -> {
                System.out.printf("Oops! Nie mam pojęcia kim był %s%n", znanaOsoba);
                yield "...";
            }
        };
        System.out.printf("%s był a %s%n", znanaOsoba, title);
    }
}


