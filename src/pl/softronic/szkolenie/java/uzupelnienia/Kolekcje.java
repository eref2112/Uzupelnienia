package pl.softronic.szkolenie.java.uzupelnienia;

import java.util.*;

public class Kolekcje {

    void testujVector(){
        ElementKolekcji elKol;

        Vector<ElementKolekcji> mojaLista = new Vector<>();
        for(int i = 1; i <= 11; i++){
            elKol = new ElementKolekcji(i, "Autor" + i, "Tytul" + i);
            mojaLista.add(elKol);
        }
        System.out.println("Liczba książek: " + mojaLista.size());
        mojaLista.remove(3);
        System.out.println("Liczba książek: " + mojaLista.size());;

        for(ElementKolekcji el: mojaLista){
            System.out.println(el);
        }

        mojaLista.clear();
        System.out.println("Liczba książek: " + mojaLista.size());;
    }

    /*
        Elementy listy mają określoną kolejność.
        Dozwolone są duplikaty elementów.
        Elementy można umieścić na liście w określonej pozycji.
        Element znajdujący się w określonej pozycji można prosto pobrać z listy.
        Element w określonej pozycji możemy łatwo podmienić na inny.
        Listy możemy łatwo sortować.

     */
    public void testujList() {
        ElementKolekcji elKol;

        List<ElementKolekcji> mojaLista = new ArrayList<>();
        for(int i = 1; i <= 11; i++){
            elKol = new ElementKolekcji(i, "Autor" + i, "Tytul" + i);
            mojaLista.add(elKol);
            mojaLista.add(elKol);
        }
        System.out.println("Liczba książek: " + mojaLista.size());
        mojaLista.remove(3);
        System.out.println("Liczba książek: " + mojaLista.size());;

        for(ElementKolekcji el: mojaLista){
            System.out.println(el);
        }

        mojaLista.clear();
        System.out.println("Liczba książek: " + mojaLista.size());;
    }


    /*
    Kolejną kolekcją w Javie są Sety, inaczej mówiąc zbiory. Dostarczane są one za pośrednictwem interfejsu Set z pakietu java.util. Sety wyglądają z wierzchu trochę podobnie do list, jednak ich implementacja i podejście do danych jest zupełnie inne. Przyjrzyjmy się teraz najważniejszym cechom zbiorów:
    Nie są dozwolone duplikaty elementów - W secie nie mogą istnieć dokładnie dwa takie same obiekty.
    Do elementów w secie nie odwołujemy się po pozycji, gdyż nie wiadomo na jakiej pozycji znajduje się dany element.
    Mogą być sortowalne lub nie - zależy od konkretnej implementacji interfejsu.

    Najpopularniejszymi zbiorami są implementacje dostarczane przez klasy:

    HashSet z pakietu java.util
    Bardzo często stosowana impelmentacja. Elementy są nieposortowane.
    Ich kolejność nie odpowiada również kolejności wkładania do zbioru. Może przechowywać null-e.

    LinkedHashSet z pakietu java.util
    Implementacja przechowująca elementy w kolejności ich dodawania.
    Zatem może być przydatna jeśli zależy nam zarówno na unikalności jak i na tworzeniu historii unikalnych wpisów.
    Może przechowywać null-e.

    TreeSet z pakietu java.util
    Przechowuje zbiór pod postacią drzewa. Elementy są poukładane w sposób posortowany (rosnąco).
    Przydaje się gdy chcemy zapewnić unikalność elementów oraz podstawowe sortowanie. Nie może przechowywać null-i.
     */
    public void testujSet() {
    //DeklARACJE
        Set<Object> listAnything = new HashSet<Object>();
        Set<Integer> listNumbers = new HashSet<Integer>();
        Set<String> linkedWords = new LinkedHashSet<String>();
        Set<String> sortedWords = new TreeSet<String>();

        Set<Number> liczby = new HashSet<>();
        liczby.add(new Integer(123));
        liczby.add(new Float(3.1415));
        liczby.add(new Double(299.988));
        liczby.add(new Long(67000));

        for (Number number : liczby) {
            System.out.println(number);
        }
    }

    /*
    Najpierw zobaczmy jakie są cechy charakterystyczne map:
    Nie są dozwolone duplikaty kluczy.
    Wartości mogą się duplikować.
    Mogą być sortowalne lub nie - zależy od konkretnej implementacji interfejsu.
    Klucze mogą być null-ami lub nie - zależy od konkretnej implementacji interfejsu (TreeMapy nie pozwalają na nulle).

    Najpopularniejszymi mapami są implementacje dostarczane przez klasy:

    HashMap z pakietu java.util
    Bardzo często stosowana implementacja. Elementy są nieposortowane.
    Ich kolejność nie odpowiada również kolejności wkładania do zbioru. Może przechowywać jednego null-a wśród kluczy.

    LinkedHashMap z pakietu java.util
    Implementacja przechowująca elementy w kolejności ich dodawania.
    Rozszerza klasę HashMap. Zatem może być przydatna jeśli zależy nam zarówno na unikalności kluczy
    jak i na tworzeniu historii unikalnych wpisów. Może przechowywać jednego null-a wśród kluczy.

    TreeMap z pakietu java.util
    Nie pozwala na przechowywanie nulla w miejscu klucza.
    Elementy są przechowywane pod postacią drzewa. Elementy są poukładane według kluczy w sposób posortowany (rosnąco).
    Przydaje się gdy chcemy zapewnić unikalność elementów oraz podstawowe sortowanie.
     */
    public void testujMap() {
        Map<Object, Object> mapOfAnything = new HashMap<Object,Object>();
        Map<Integer, String> linkedWordsWithIds = new LinkedHashMap<Integer,String>();
        Map<Integer, String> sortedWordsWithIds = new TreeMap<Integer,String>();

        Map<Integer, String> movies = new HashMap<Integer, String>();
        // Poniższe elementy zostaną dodane do mapy - wszystkie klucze są typu Integer, a wartości typu String:
        movies.put(1, "Joker");
        movies.put(2, "Jurassic World");
        movies.put(3, "Psy 3");

        //Po wykonaniu poniższej linijki podmienimy film dla identyfikatora 2:
        movies.put(2, "Nietykalni");

        // Poniższy element nie zostanie dodany do mapy - kompilator zgłosi błąd
        // (404 jest liczbą a nie stringiem):
        //movies.put(4, 404);

        Set<Map.Entry<Integer, String>> entries = movies.entrySet();
        Iterator<Map.Entry<Integer, String>> moviesIterator = entries.iterator();

        while(moviesIterator.hasNext()) {
            Map.Entry<Integer, String> entry = moviesIterator.next();
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }
}
