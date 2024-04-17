package com.sportclub.app;

import com.sportclub.model.*;

import java.time.LocalDate;

public class MainApplication {
    public static void main(String[] args) {
        // Tworzenie działów
        DzialPracownikow itDzial = new DzialPracownikow("IT");
        DzialPracownikow hrDzial = new DzialPracownikow("HR");

        // Tworzenie pracowników
        Manager manager = new Manager("Anna", "Kowalska", LocalDate.of(1985, 5, 5), hrDzial, "anna.k", "password123");
        Recepcjonista recepcjonista = new Recepcjonista("Marcin", "Nowak", LocalDate.of(1997, 4, 4), itDzial, "marcin.n", "pass987");
        Trener trener = new Trener("Lukasz", "Budnik", LocalDate.of(2000, 2, 15), hrDzial, "Fitness");
        System.out.println(manager.getLogin());
        System.out.println(recepcjonista.getDataUrodzenia());
        System.out.println(trener.getDzial());
        System.out.println(itDzial.getNazwa());
        System.out.println(hrDzial.getNazwa());
    }
}