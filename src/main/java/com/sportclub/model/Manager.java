package com.sportclub.model;
import java.time.LocalDate;

public class Manager extends Recepcjonista {
    public Manager(String imie, String nazwisko, LocalDate dataUrodzenia, DzialPracownikow dzial, String login, String haslo) {
        super(imie, nazwisko, dataUrodzenia, dzial, login, haslo);
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