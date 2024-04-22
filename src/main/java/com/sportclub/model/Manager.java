package com.sportclub.model;

import java.time.LocalDate;
import java.util.List;

public class Manager extends Recepcjonista {
    public Manager(String imie, String nazwisko, LocalDate dataUrodzenia, DzialPracownikow dzial, String login, String haslo) {
        super(imie, nazwisko, dataUrodzenia, dzial, login, haslo);      // super -> klasa po której się dziedziczy (recepcjonista)
    }   // brak dodatkowego konstruktora i getterow / setterow bo wszystko co ma, to dziedziczy.


    // metody publiczne
    public void pokazZespolyIZadaniaDoNich(){
        List<Zespol> zespoly = this.getZespoly();
        for (Zespol zespol : zespoly){
            getZadaniaWZespole(zespol);
        }
    }

    public List<Zadanie> getZadaniaWZespole(Zespol zespol) {
        Praca praca = zespol.getPraca();            // zespol nie ma w swoich zmiennych zadan, dlatego dostaje sie najpierw do pracy...
        List<Zadanie> zadaniaWZespole = praca.getZadania();     // a pozniej do zadania
        System.out.println("Zadania w zespole "+ zespol.getNazwa() + ":");
        for (Zadanie zadanie : zadaniaWZespole){
            System.out.println("\t" + zadanie);
        }
        return zadaniaWZespole;
    }

    public List<Zespol> getZespoly() {
        for (Zespol zespol : zespoly) {
            System.out.println("\t" + zespol.getNazwa());
    }
        return zespoly;
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

    @Override
    public String toString() {
        return "Manager{" +
                this.getBasicInfo() +
                ", login='" + this.getLogin() + '\'' +
                ", haslo='" + this.getHaslo() + '\'' +
                ", zespoly=" + zespoly.stream().map(Zespol::getNazwa).toList() +
                '}';
    }
}
