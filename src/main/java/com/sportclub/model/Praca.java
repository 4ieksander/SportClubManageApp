package com.sportclub.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



public class Praca implements Runnable {        // poczytaj troche runnable
    private static int nextId = 1;
    private static Map<Integer, Zadanie> mapaZadan = new HashMap<>();       // map -> coś jak dziennik w pythonie

    private final int id;
    private List<Zadanie> zadania;
    private String opis;
    private Zespol zespol;


    public Praca(String opis, Zespol zespol) {
        this.id = nextId++;
        this.zadania = new ArrayList<>();
        this.opis = opis;
        zespol.ustawPrace(this);        //u stawia tą pracę danemu zespołowi jako zmienną
        this.zespol = zespol;

    }


    public static Zadanie getZadaniePoId(int id) {
        return mapaZadan.get(id);
    }


    public void dodajZadanie(Zadanie zadanie) {
        Manager manager = zespol.getManager();      // bierze managera tego zespolu
        manager.dodajZadanie(zadanie);              // i dodaje mu do zmiennej zadania (ta sama co mają pracownicy) to zadanie
        zespol.dodajZadaniePracownikom(zadanie);    // --||--
        zadania.add(zadanie);                       // lista zadań w TEJ pracy
        mapaZadan.put(zadanie.getZadanieId(), zadanie); // metoda "getId()" jest zajęta przez threading
    }


    // implementacja interfejsu runnable
    @Override
    public void run() {
        if (zadania.isEmpty()) {
            System.out.println("Brak zadań do wykonania.");
            return;
        }
        if (czyKtosJestChory() || !zespol.getManager().getCzyZdrowy()){    // " ! " -> inwersja  " || "" -> OR ( && -> AND )
            System.out.println("Ktoś jest chory w zespole " + zespol.getNazwa() + ", nie można zacząć pracy.");
            return;
        }
        /// Zatwierdzone zadania
        System.out.println("Rozpoczęcie pracy: " + opis);

        for (Zadanie zadanie : zadania) {
            zadanie.start();
            try {
                zadanie.join(); // Czeka na zakończenie wątku thread
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public String toString() {
        String listaZadan = zadania.stream()
                .map(Zadanie::toString)
                .collect(Collectors.joining(", "));

        return "Praca{" +
                "id=" + id +
                ", zadania=[" + listaZadan + "]" +
                ", opis='" + opis + '\'' +
                ", zespol=" + zespol +
                '}';
    }


    public List<Zadanie> getZadania() {
        return zadania;
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


    private boolean czyKtosJestChory() {        // funkcja pomocnicza
        return zespol.getPracownicy().stream()  // Pobiera strumień pracowników zespołu
                .anyMatch(pracownik -> !pracownik.getCzyZdrowy());  // Sprawdza, czy którykolwiek pracownik jest chory
    }

}
