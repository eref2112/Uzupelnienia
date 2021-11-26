package pl.softronic.szkolenie.java.uzupelnienia;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;

public class OperacjeInOut {

    void testujOperacjeInOut(){
        //1. Testujemy wczytywanie do String
        String tresc = wczytajPlikTekstowy();
        System.out.println(tresc);

        //2. Testujemy wcztywanie do Vector<String>
        Vector<String> lista = wczytajPlikTekstowyDoVectora();
        //Instrukcaja for-each dla kolekcji
        for (String el: lista){
            System.out.println(el);
        }

        //3. Testujemy zapis zmiennej String do pliku
        try {
            zapiszTekstDoPliku("c:\\szkolenie\\plikTestowy.txt",tresc);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //4. Testujemy wczytywanie zawartości witryny
        tresc = getKodHtml("http://www.google.com");
        System.out.println(tresc);
        try {
            zapiszTekstDoPliku("c:\\szkolenie\\plikTestowy.html",tresc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String wczytajPlikTekstowy() {
        String zawartoscPliku = "";
        FileReader inputStream = null;

        String wiersz = "";
        String nL = "";

        String nazwaPliku = Utils.pobierzNazwePliku();
        System.out.println("Wybrany plik to: " + nazwaPliku);

        if (nazwaPliku.equals("")){
            System.out.println("Nie wybrałeś pliku");
            return "";
        }

        InputStream is;
        InputStreamReader isr;
        BufferedReader br;
        int rozmiarBufora = 8192;
        String charSet = "UTF-8";

        StringBuilder sb = new StringBuilder();

        //-----------------------------------

        try {
            is = new FileInputStream(nazwaPliku);
            isr = new InputStreamReader(is, charSet);
            br = new BufferedReader(isr, rozmiarBufora);

            wiersz = br.readLine();

            while (wiersz != null){
                sb.append(nL);
                sb.append(wiersz);

                if (nL.equals("")) nL = "\n";
                wiersz = br.readLine();
            }

            br.close();

            zawartoscPliku = sb.toString();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return zawartoscPliku;
    }

    private Vector<String> wczytajPlikTekstowyDoVectora() {
        Vector<String> zawartoscPliku = new Vector<String>();
        FileReader inputStream = null;

        String wiersz = "";
        String nL = "";

        String nazwaPliku = Utils.pobierzNazwePliku();
        System.out.println("Wybrany plik to: " + nazwaPliku);

        if (nazwaPliku.equals("")){
            System.out.println("Nie wybrałeś pliku");
            return zawartoscPliku;
        }

        InputStream is;
        InputStreamReader isr;
        BufferedReader br;
        int rozmiarBufora = 8192;
        String charSet = "UTF-8";

        //-----------------------------------

        try {
            is = new FileInputStream(nazwaPliku);
            isr = new InputStreamReader(is, charSet);
            br = new BufferedReader(isr, rozmiarBufora);

            wiersz = br.readLine();

            while (wiersz != null){
                zawartoscPliku.add(wiersz);
                wiersz = br.readLine();
            }

            br.close();

        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return zawartoscPliku;
    }

    public static void zapiszTekstDoPliku(String nazwaPliku, String tekst) throws IOException {
        File nowyPlik = new File(nazwaPliku);
        PrintWriter pw = new PrintWriter(nowyPlik, "UTF-8");
        pw.write(tekst);
        pw.close();
    }

    public String getKodHtml(String adres) {
        String zawartoscStrony = "";
        URL urlStrony = null;

        try {
            urlStrony = new URL(adres);

            URLConnection uc = urlStrony.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String wiersz;

            while ((wiersz = in.readLine()) != null) {
                zawartoscStrony = zawartoscStrony + "\n" + wiersz;
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return zawartoscStrony;
    }



}
