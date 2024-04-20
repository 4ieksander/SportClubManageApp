package com.sportclub.app;

import com.sportclub.model.*;
import com.sportclub.exception.NotUniqueNameException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import static java.lang.System.out;     // zeby nie trzeba było ciagle pisac System

public class MainApplication {

    public static void main(String[] args) {
        DzialPracownikow dzialHR = null;
        DzialPracownikow dzialSportu = null;
        DzialPracownikow dzialWyjatku = null;

        try {
            dzialHR = DzialPracownikow.createDzial("HR");
            dzialSportu = DzialPracownikow.createDzial("Sport");
            dzialWyjatku = DzialPracownikow.createDzial("Sport");
        } catch (NotUniqueNameException e1) {
            System.out.println("Wystapil wyjatek: " + e1.getMessage());}



        Manager manager = new Manager("Jakub", "Sikora", LocalDate.of(1988, 4, 4), dzialHR, "jam.n", "123");
        Recepcjonista recepcjonista1 = new Recepcjonista("Anna", "Kowalska", LocalDate.of(1985, 5, 5), dzialHR, "anna.k", "password123");
        Recepcjonista recepcjonista2 = new Recepcjonista("Barbara", "Popularna", LocalDate.of(1983, 1, 2), dzialHR, "anna.k", "password123");
        Trener trener1 = new Trener("Lukasz", "Budnik", LocalDate.of(2000, 5, 30), dzialSportu, "CrossFit");
        Trener trener2 = new Trener("Darek", "Kowalski", LocalDate.of(1990, 2, 15), dzialSportu, "Trener personalny");
        Trener trener3 = new Trener("Mariusz", "Kowalski", LocalDate.of(1990, 2, 15), dzialSportu, "Boks");
        Trener trener4 = new Trener("Patryk", "Kolanko", LocalDate.of(1990, 2, 15), dzialSportu, "Trener personalny");


        // Tworzenie zespołów
        out.println("\nZespol trenerow personalnych");
        Zespol trenerzyPersonalni = new Zespol("Trenerzy personalni", manager);
        trenerzyPersonalni.dodajPracownika(trener2);    // dodanie 1 pracownika
        trenerzyPersonalni.dodajPracownika(trener4);

        Set<Pracownik> pracownicyZDzialuSport = dzialSportu.getPracownicy(); // metoda aby uzyskać informację jacy pracownicy są w dziale

        out.println("\nDodawanie listy pracownikow");
        List<Pracownik> pracownicySilowni = new ArrayList<>();
        pracownicySilowni.addAll(pracownicyZDzialuSport);    // dodanie listy pracowników

        Zespol personelSilowni = new Zespol("Personel silowni", manager);
        personelSilowni.dodajPracownika(pracownicySilowni);         // dodanie pracownikow do zespolu poprzez liste

        out.println("\nZespol reecepcjonistek");
        List<Pracownik> recepcjonistki = new ArrayList<>();
        recepcjonistki.add(recepcjonista1);
        recepcjonistki.add(recepcjonista2);
        Zespol recepcja = new Zespol("Recepcja", manager);
        recepcja.dodajPracownika(recepcjonistki);   // dodanie pracownikow do zespolu poprzez liste

        out.println("\ncompareTo");
        out.println(trener1.compareTo(trener3));
        out.println(trener2.compareTo(trener3));

        out.println("\nZmiana ID");
        out.println(recepcjonista1.getImieNazwisko());
        out.println(recepcjonista1.getInitial());
        recepcjonista1.setImie("Monika");
        out.println(recepcjonista1.getInitial());


        // Tworzenie zadań
        Zadanie zadanie1 = new Zadanie("Aktualizacja systemu", "Aktualizacja i deploy nowej wersji systemu.", true);
        Zadanie zadanie2 = new Zadanie("Szkolenie BHP", "Prowadzenie szkolenia BHP dla nowych pracowników.", false);
        Zadanie zadanie3 = new Zadanie("Szkolenie stanowiskowe", "Prowadzenie szkolenia stanowiskowego dla nowych pracowników.", false);

        out.println(manager.toString());

        zadanie2.setZatwierdzone(true);
        out.println(dzialSportu.toString());
        out.println(zadanie1.toString());
        // Tworzenie pracy
        Praca praca = new Praca("Planowanie IT", trenerzyPersonalni);
        praca.dodajZadanie(zadanie1);
        praca.dodajZadanie(zadanie2);
        praca.dodajZadanie(zadanie3);
        Thread pracaThread = new Thread(praca);
//        pracaThread.start();

        out.println(praca.toString());
//        manager.getZespoly();
//        System.out.println(":)");
//        System.out.println(manager.getZadania());
////        System.out.println(itZespol.getManager().getLogin());
//        System.out.println(manager.getZespoly());
//        System.out.println(manager.toString());
        // Rozpoczęcie pracy w wątku
//            Thread pracaThread = new Thread(praca);
//            pracaThread.start();
//
//            try {
//                pracaThread.join(); // Czekanie na zakończenie pracy
//            } catch (InterruptedException e2) {
//                System.out.println("Wykonanie pracy zostało przerwane.");
//                Thread.currentThread().interrupt();
//            }


        }
    }
