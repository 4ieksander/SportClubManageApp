package com.sportclub.model;

import com.sportclub.util.IDobryPracownik;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


public abstract class Pracownik implements Comparable<Pracownik>, IDobryPracownik {
    private static int nextId = 1;
    private final int id;
    private String imie;
    private String nazwisko;
    private LocalDate dataUrodzenia;
    private boolean czyZdrowy;
    private DzialPracownikow dzial;
    protected List<Zespol> zespoly = new ArrayList<>();
    protected List<Zadanie> zadania = new ArrayList<>();
    private static final List<Pracownik> wszyscyPracownicy = new ArrayList<>();

    public Pracownik(String imie, String nazwisko, LocalDate dataUrodzenia, DzialPracownikow dzial) {
        this.id = nextId++;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUrodzenia = dataUrodzenia;
        this.dzial = dzial;
        dzial.dodajPracownika(this);
        this.czyZdrowy = true; // Domyślna wartość to true
        wszyscyPracownicy.add(this);
    }

    public abstract void pracuj();

    public List<Zadanie> getZadania() {
        return new ArrayList<>(zadania);
    }

    public void dodajZadanie(Zadanie zadanie) {
         this.zadania.add(zadanie);
    }

    public void dodajZespol(Zespol zespol) {
        zespoly.add(zespol);
    }

    public List<Zespol> getZespoly() {
        return new ArrayList<>(zespoly);
    }


    @Override
    public int compareTo(Pracownik o) {
        // Przykład porównania najpierw po nazwisku, potem po imieniu
        int lastCmp = this.nazwisko.compareTo(o.nazwisko);
        if (lastCmp != 0) {
            return lastCmp;
        }
        return this.imie.compareTo(o.imie);
    }



    // Gettery i settery
    public int getId() { return id; }
    public String getImie() { return imie; }
    public void setImie(String imie) { this.imie = imie; }
    public String getNazwisko() { return nazwisko; }
    public void setNazwisko(String nazwisko) { this.nazwisko = nazwisko; }
    public LocalDate getDataUrodzenia() { return dataUrodzenia; }
    public boolean isCzyZdrowy() { return czyZdrowy; }
    public void setCzyZdrowy(boolean czyZdrowy) { this.czyZdrowy = czyZdrowy; }
    public DzialPracownikow getDzial() { return dzial; }
    public void setDzial(DzialPracownikow dzial) { this.dzial = dzial; }

    public static List<Pracownik> getWszyscyPracownicy() {
        return new ArrayList<>(wszyscyPracownicy);
    }
}