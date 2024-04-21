package com.sportclub.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Zespol {
    private static int nextId = 1;
    private final int id;
    private String nazwa;
    private Manager manager;
    private Praca praca;
    private List<Pracownik> pracownicy;


    public Zespol(String nazwa, Manager manager) {
        this.id = nextId++;
        this.nazwa = nazwa;
        this.manager = manager;
        this.pracownicy = new ArrayList<>();
        manager.dodajZespol(this);
    }

    @Override
    public String toString() {
        return "Zespol{" +
                "id=" + id +
                "nazwa='" + nazwa + '\'' +
                ", manager=" + manager.getNazwisko() +
                ", pracownicy=" + pracownicy.stream().map(Pracownik::getNazwisko).toList() +
                '}';
    }


    public void dodajPracownika(Pracownik pracownik) {
        if (!(pracownik instanceof Manager)) {
            pracownicy.add(pracownik);
            pracownik.dodajZespol(this);
            System.out.println("Pracownik " + pracownik.getImieNazwisko() + " dodany do zespołu " + nazwa);
        } else {
            System.out.println("Managerowie nie mogą być dodani jako zwykli pracownicy do zespołu.");
        }
    }

    public void dodajPracownika(List<Pracownik> nowiPracownicy) {
        for (Pracownik pracownik : nowiPracownicy) {
            dodajPracownika(pracownik);
        }
    }

    public void dodajZadaniePracownikom(Zadanie zadanie) {
        for (Pracownik pracownik : pracownicy) {
            pracownik.dodajZadanie(zadanie);
        }
    }

    public void ustawPrace(Praca praca){
        this.praca = praca;
    }

    public Praca getPraca() {
        return praca;
    }

    public String getNazwa() {
        return nazwa;
    }

    public Manager getManager() {
        return manager;
    }

    public List<Pracownik> getPracownicy() {
        return new ArrayList<>(pracownicy);
    }
}