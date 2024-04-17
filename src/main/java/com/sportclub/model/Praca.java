package com.sportclub.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



public class Praca implements Runnable {
    private List<Zadanie> zadania;
    private String opis;
    private Zespol zespol;
    private static Map<Integer, Zadanie> mapaZadan = new HashMap<>();

    // konstruktor
    public Praca(String opis, Zespol zespol) {
        this.zadania = new ArrayList<>();
        this.opis = opis;
        this.zespol = zespol;
    }

    /**
     * Dodaje zadanie do pracy.
     * @param zadanie Zadanie do dodania.
     */
    public void dodajZadanie(Zadanie zadanie) {
        zadania.add(zadanie);
        mapaZadan.put(zadanie.getZadanieId(), zadanie); // metoda getId() jest zajęta (wbudowana)
    }

    /**
     * Staticzna metoda do pozyskiwania zadania na podstawie jego ID.
     * @param id Identyfikator zadania.
     * @return Zadanie o danym ID lub null, jeśli nie znaleziono.
     */
    public static Zadanie getZadaniePoId(int id) {
        return mapaZadan.get(id);
    }

    /**
     * Metoda wykonująca wszystkie zadania związane z pracą.
     */
    @Override
    public void run() {
        if (zadania.isEmpty()) {
            System.out.println("Brak zadań do wykonania.");
            return;
        }

        System.out.println("Rozpoczęcie pracy: " + opis);
        List<Zadanie> doWykonania = zadania.stream()
                .filter(Zadanie::isZatwierdzone)
                .collect(Collectors.toList());

        for (Zadanie zadanie : doWykonania) {
            zadanie.start();  // Bezpośrednie uruchomienie wątku, ponieważ Zadanie jest Thread
        }
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Zespol getZespol() {
        return zespol;
    }

    public void setZespol(Zespol zespol) {
        this.zespol = zespol;
    }
}
