package pl.softronic.szkolenie.java.uzupelnienia;

import javax.swing.*;
import java.io.File;

public class Utils {
    public static String pobierzNazwePliku() {
        String nazwaPliku = "";

        //Formatka wyboru pliku lub katalogu
        JFileChooser jfc = new JFileChooser();

        //Ustawiamy, by otworzył się w bieżącym katalogu
        jfc.setCurrentDirectory(new File( System.getProperty("user.dir")));
        int retValue = jfc.showOpenDialog(null);

        //Jeśli wybraliśmy plik i zatierdziliśmy przyciekiem OK
        if (retValue == JFileChooser.APPROVE_OPTION) {
            File plik = jfc.getSelectedFile();
            nazwaPliku = plik.getPath();
        }

        return nazwaPliku;
    }
}
