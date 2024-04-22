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
        DzialPracownikow dzialObslugi = null;
        DzialPracownikow dzialManagerski = null;
        DzialPracownikow dzialSportu = null;
        DzialPracownikow dzialWyjatku = null;

        try {
            dzialManagerski = DzialPracownikow.createDzial("Managerowie");
            dzialObslugi = DzialPracownikow.createDzial("Obsluga");
            dzialSportu = DzialPracownikow.createDzial("Sport");
            dzialWyjatku = DzialPracownikow.createDzial("Sport");
        } catch (NotUniqueNameException e1) {
            System.out.println("Wystapil wyjatek: " + e1.getMessage());}



        Manager manager_silowni = new Manager("Jakub", "Sikora", LocalDate.of(1988, 4, 4), dzialManagerski, "jakub.s", "12345");
        Manager manager_boiska = new Manager("Jacek", "Biały", LocalDate.of(1975, 3, 2), dzialManagerski, "jacek.b", "123123");
        Recepcjonista recepcjonista1 = new Recepcjonista("Anna", "Kowalska", LocalDate.of(1985, 5, 5), dzialObslugi, "anna.k", "password123");
        Recepcjonista recepcjonista2 = new Recepcjonista("Barbara", "Popularna", LocalDate.of(1983, 1, 2), dzialObslugi, "barbara.p", "password123");
        Trener trener1 = new Trener("Lukasz", "Budnik", LocalDate.of(2000, 5, 30), dzialSportu, "CrossFit");
        Trener trener2 = new Trener("Darek", "Kowalski", LocalDate.of(1990, 2, 15), dzialSportu, "Trener personalny");
        Trener trener3 = new Trener("Mariusz", "Kowalski", LocalDate.of(1990, 2, 15), dzialSportu, "Boks");
        Trener trener4 = new Trener("Patryk", "Kolanko", LocalDate.of(1990, 2, 15), dzialSportu, "Trener personalny");
        Trener trener5 = new Trener("Waldemar", "Polak", LocalDate.of(1998, 4,6), dzialSportu, "Trener piłki nożnej");
        Trener trener6 = new Trener("Waldemar", "August", LocalDate.of(1991, 2,8), dzialSportu, "Trener piłki ręcznej");


        // Tworzenie zespołów
        out.println("\nZespol trenerow piłki");
        Zespol trenerzyDruzynowi = new Zespol("Trenerzy sportów drużynowych ", manager_boiska);
        trenerzyDruzynowi.dodajPracownika(trener5);    // dodanie 1 pracownika
        trenerzyDruzynowi.dodajPracownika(trener6);


        out.println("\nDodawanie listy pracownikow silowni");
        List<Pracownik> personelSilowniList = new ArrayList<>();
        personelSilowniList.add(trener1);
        personelSilowniList.add(trener2);
        personelSilowniList.add(trener3);
        personelSilowniList.add(trener4);
        Zespol personelSilowni = new Zespol("Personel silowni", manager_silowni);
        personelSilowni.dodajPracownika(personelSilowniList);         // dodanie pracownikow do zespolu poprzez liste


        out.println("\nZespol reecepcjonistek");
        Zespol recepcja = new Zespol("Recepcja", manager_silowni);
        Set<Pracownik> personelObslugiSet = dzialObslugi.getPracownicy(); // metoda aby uzyskać informację jacy pracownicy są w dziale
        List<Pracownik> personelObslugiList = new ArrayList<>();
        personelObslugiList.addAll(personelObslugiSet);    // dodanie listy pracowników z seta do listy
        recepcja.dodajPracownika(personelObslugiList);   // dodanie pracownikow do zespolu poprzez liste


        Praca obsluga = new Praca("Obsluga", recepcja);
        Praca silownia = new Praca("Silownia", personelSilowni);
        Praca boisko = new Praca("Boisko", trenerzyDruzynowi);

        out.println("\n\nTworzenie zadań");
        Zadanie zadanie1 = new Zadanie("Logowanie", "Zaloguj sie do komputera", true);
        Zadanie zadanie2 = new Zadanie("Przyjmuj klientów", "Na silowni.", false);
        Zadanie zadanie3 = new Zadanie("Sprawdz skrzynke email", ".", false);
        Zadanie zadanie4 = new Zadanie("Posprzątanie silowni", "Jak w tytule", true);
        Zadanie zadanie5 = new Zadanie("Trening personalny 1");
        Zadanie zadanie6= new Zadanie("Trening personalny 2");
        Zadanie zadanie7 = new Zadanie("Sparring");
        Zadanie zadanie8 = new Zadanie("Mecz", "Jak w tytule", false);
        zadanie2.setZatwierdzone(true);
        out.println("\n"+zadanie7.getOpis());

        obsluga.dodajZadanie(zadanie1);
        obsluga.dodajZadanie(zadanie2);
        obsluga.dodajZadanie(zadanie3);
        silownia.dodajZadanie(zadanie4);
        silownia.dodajZadanie(zadanie5);
        silownia.dodajZadanie(zadanie6);
        boisko.dodajZadanie(zadanie7);
        boisko.dodajZadanie(zadanie8);


        Thread obslugaThread = new Thread(obsluga);
        Thread silowniaThread = new Thread(silownia);
        Thread boiskoThread = new Thread(boisko);

        trener2.setCzyZdrowy(false);
        manager_boiska.setCzyZdrowy(false);

//        Pracownik.wyswietlZadaniaPracownikow();


        out.println("\n\nStartuje wątki pracy...");
        silowniaThread.start();

        try{
            Thread.sleep(300);
            obslugaThread.start();
            obslugaThread.join();
            out.println("Status zadania logowanie:");
            out.println(Praca.getZadaniePoId(2));
            boiskoThread.start();
            boiskoThread.join();

            out.println("\nUstawianie czyZdrowy = True");
            manager_boiska.setCzyZdrowy(true);
            boiskoThread = new Thread(boisko);
            boiskoThread.start();
            Thread.sleep(1000);
            out.println("\nManager boiska - zadania i zespoły (jedno zadanie rozpoczęte):");
            manager_boiska.pokazZespolyIZadaniaDoNich();
            boiskoThread.join();

            out.println("\n\n\nMetody toString()");
            out.println(obsluga.toString());
            out.println(dzialSportu.toString());
            out.println(zadanie1.toString());
            out.println(manager_silowni.toString());

            out.println("\ncompareTo");
            out.println(trener1.compareTo(trener3));
            out.println(trener2.compareTo(trener3));

            out.println("\nZmiana ID");
            out.println(recepcjonista1.getImieNazwisko());
            out.println(recepcjonista1.getInitial());
            recepcjonista1.setImie("Monika");
            out.println(recepcjonista1.getInitial());


            out.println("\nZadania w zespołach managera:");
            manager_silowni.pokazZespolyIZadaniaDoNich();

            out.println("\nPracownicy dzialu menadżerów:");
            for (Pracownik pracownik : dzialManagerski.getPracownicy()){
                out.println(pracownik.getImieNazwisko());
            };

            out.println("\nZadanie po ID");
            out.println(Praca.getZadaniePoId(2));

            out.println("\nDodatkowa metoda recepcjonisty:");
            recepcjonista1.sprawdzKlienta("Bartłomiej");

            out.println("\nManager boiska - zadania i zespoły:");
            manager_boiska.pokazZespolyIZadaniaDoNich();
        }
        catch(InterruptedException e){
            System.out.println("Zadanie zostało przerwane");
            Thread.currentThread().interrupt();}





        }
    }
