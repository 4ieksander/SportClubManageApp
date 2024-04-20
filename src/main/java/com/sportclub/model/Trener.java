package com.sportclub.model;
import java.time.LocalDate;


public class Trener extends Pracownik {
    private String specjalizacja;

    public Trener(String imie, String nazwisko, LocalDate dataUrodzenia, DzialPracownikow dzial, String specjalizacja) {
        super(imie, nazwisko, dataUrodzenia, dzial);
        this.specjalizacja = specjalizacja;
    }

    @Override
    public String toString() {
        return "Trener{" +
                this.getBasicInfo() +
                ", specjalizacja='" + specjalizacja + '\'' +
                '}';
    }


    @Override
    public void pracuj() {
        System.out.println("Prowadzenie treningu w specjalizacji " + specjalizacja);
    }

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

    // Gettery i settery
    public String getSpecjalizacja() { return specjalizacja; }
    public void setSpecjalizacja(String specjalizacja) { this.specjalizacja = specjalizacja; }
}