package com.sportclub.app;

import com.sportclub.model.*;
import com.sportclub.exception.NotUniqueNameException;

import java.time.LocalDate;

public class MainApplication {

    public static void main(String[] args) {
        DzialPracownikow dzialHR = null;
        DzialPracownikow dzialIT = null;

        try {
            // Tworzenie działów
            dzialHR = DzialPracownikow.createDzial("HR");
            dzialIT = DzialPracownikow.createDzial("IT");

        } catch (NotUniqueNameException e1) {
            System.out.println(e1.getMessage());}

            Manager manager1 = new Manager("Anna", "Kowalska", LocalDate.of(1985, 5, 5), dzialHR, "anna.k", "password123");
            Recepcjonista recepcjonista1 = new Recepcjonista("Marcin", "Nowak", LocalDate.of(1988, 4, 4), dzialHR, "jam.n", "123");
            Recepcjonista recepcjonista2 = new Recepcjonista("Jan", "Nowak", LocalDate.of(1982, 6, 1), dzialIT, "marcin.n", "pass987");
            Trener trener1 = new Trener("Lukasz", "Budnik", LocalDate.of(1990, 2, 15), dzialHR, "Fitness");

            // Tworzenie zespołu
            Zespol itZespol = new Zespol("IT Team", manager1);
            itZespol.dodajPracownika(recepcjonista1);
            itZespol.dodajPracownika(trener1);


            // Tworzenie zadań
            Zadanie zadanie1 = new Zadanie("Aktualizacja systemu", "Aktualizacja i deploy nowej wersji systemu.", true);
            Zadanie zadanie2 = new Zadanie("Szkolenie BHP", "Prowadzenie szkolenia BHP dla nowych pracowników.", false);
            Zadanie zadanie3 = new Zadanie("Szkolenie stanowiskowe", "Prowadzenie szkolenia stanowiskowego dla nowych pracowników.", false);

            zadanie2.setZatwierdzone(true);

            // Tworzenie pracy
            Praca praca = new Praca("Planowanie IT", itZespol);
            praca.dodajZadanie(zadanie1);
            praca.dodajZadanie(zadanie2);
            praca.dodajZadanie(zadanie3);

            // Rozpoczęcie pracy w wątku
            Thread pracaThread = new Thread(praca);
            pracaThread.start();

            try {
                pracaThread.join(); // Czekanie na zakończenie pracy
            } catch (InterruptedException e2) {
                System.out.println("Wykonanie pracy zostało przerwane.");
                Thread.currentThread().interrupt();
            }


        }
    }
