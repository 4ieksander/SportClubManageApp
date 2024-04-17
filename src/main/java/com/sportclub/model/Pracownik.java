package com.sportclub.model;
import java.time.LocalDate;

import com.sportclub.util.IDobryPracownik;


public abstract class Pracownik implements Comparable<Pracownik>, IDobryPracownik {
    private static int nextId = 1;
    private final int id;
    private String imie;
    private String nazwisko;
    private LocalDate dataUrodzenia;
    private boolean czyZdrowy;
    private DzialPracownikow dzial;

    public Pracownik(String imie, String nazwisko, LocalDate dataUrodzenia, DzialPracownikow dzial) {
        this.id = nextId++;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUrodzenia = dataUrodzenia;
        this.dzial = dzial;
        this.czyZdrowy = true;  // Domyślna wartość to true
    }

    public abstract void pracuj();

    @Override
    public int compareTo(Pracownik o) {
        // Można dostosować porównanie, np. wg nazwiska, imienia
        int last = this.nazwisko.compareTo(o.nazwisko);
        return last == 0 ? this.imie.compareTo(o.imie) : last;
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

    public abstract void pracujEfektywnie();

    public abstract void współpracujZInnymi();

    public abstract void rozwijajUmiejętności();
}