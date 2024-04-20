package com.sportclub.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Manager extends Recepcjonista {
    public Manager(String imie, String nazwisko, LocalDate dataUrodzenia, DzialPracownikow dzial, String login, String haslo) {
        super(imie, nazwisko, dataUrodzenia, dzial, login, haslo);
    }

    @Override
    public String toString() {
        return "Manager{" +
                this.getBasicInfo() +
                ", login='" + this.getLogin() + '\'' +
                ", haslo='" + this.getHaslo() + '\'' +
                ", zespoly=" + zespoly.stream().map(Zespol::getNazwa).toList() +
                '}';
    }


    public List<Zadanie> getZadaniaWZespole(Zespol zespol) {
        Praca praca = zespol.getPraca();
        List<Zadanie> zadaniaWZespole = praca.getZadania();
        System.out.println("Zadania w zespole "+ zespol.getNazwa() + ":");
        for (Zadanie zadanie : zadaniaWZespole){
            System.out.println(zadanie);
        }
        return zadaniaWZespole;
    }

    // Metoda do zwracania listy zespołów dla danego zadania
    public List<Zespol> getZespolyZadania(Zadanie zadanie) {
        List<Zespol> zespolyZadania = new ArrayList<>();
        for (Zespol zespol : zespoly) {
            if (zespol.getPracownicy().contains(this) && zadania.contains(zadanie)) {
                zespolyZadania.add(zespol);
            }
        }
        return zespolyZadania;
    }

    @Override
    public void pracuj() {
        System.out.println("Zarządzanie zespołem i projektami.");
    }

    @Override
    public void pracujEfektywnie() {
        System.out.println("Efektywne zarządzanie zasobami ludzkimi.");
    }

    @Override
    public void współpracujZInnymi() {
        System.out.println("Współpraca z kierownictwem i innymi managerami.");
    }

    @Override
    public void rozwijajUmiejętności() {
        System.out.println("Rozwój umiejętności lidera i menedżera.");
    }
}
