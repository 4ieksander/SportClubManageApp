package com.sportclub.model;

import java.util.ArrayList;
import java.util.List;

public class Zespol {
    private String nazwa;
    private Manager manager;
    private List<Pracownik> pracownicy;

    public Zespol(String nazwa, Manager manager) {
        this.nazwa = nazwa;
        this.manager = manager;
        this.pracownicy = new ArrayList<>();
    }

    public void dodajPracownika(Pracownik pracownik) {
        if (!(pracownik instanceof Manager)) {
            pracownicy.add(pracownik);
            System.out.println("Pracownik " + pracownik.getImie() + " " + pracownik.getNazwisko() + " dodany do zespołu " + nazwa);
        } else {
            System.out.println("Managerowie nie mogą być dodani jako zwykli pracownicy do zespołu.");
        }
    }

    public void dodajPracownika(List<Pracownik> nowiPracownicy) {
        for (Pracownik pracownik : nowiPracownicy) {
            dodajPracownika(pracownik);
        }
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