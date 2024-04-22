package com.sportclub.model;
import java.time.LocalDate;


public class Trener extends Pracownik {
    private String specjalizacja;


    // konstruktor
    public Trener(String imie, String nazwisko, LocalDate dataUrodzenia, DzialPracownikow dzial, String specjalizacja) {
        super(imie, nazwisko, dataUrodzenia, dzial);    //super() -> odwołanie do klasy "rodzica"
        this.specjalizacja = specjalizacja;
    }


    // nadpisanie metody abstrakcyjnej
    @Override
    public void pracuj() {
        System.out.println("Prowadzenie treningu w specjalizacji " + specjalizacja);
    }

    // implementacje interfejsu IDobryPracownik
    @Override
    public void pracujEfektywnie() {
        System.out.println("Efektywne prowadzenie treningów.");
    }

    @Override
    public void współpracujZInnymi() {
        System.out.println("Współpraca z innymi trenerami.");
    }

    @Override
    public void rozwijajUmiejętności() {
        System.out.println("Uczestnictwo w kursach i szkoleniach.");
    }

    @Override
    public String toString() {
        return "Trener{" +
                this.getBasicInfo() +
                ", specjalizacja='" + specjalizacja + '\'' +
                '}';
    }


    // Gettery i settery
    public String getSpecjalizacja() {
        return specjalizacja;
    }
    public void setSpecjalizacja(String specjalizacja) {
        this.specjalizacja = specjalizacja;
    }
    // reszta getterow/setterow jest w klasie Rodzicu
}