package com.sportclub.model;

import com.sportclub.util.IDobryPracownik;

import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Pracownik implements Serializable, Comparable<Pracownik>, IDobryPracownik {
    private static final long serialVersionUID = 1L;
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

    @Override
    public String toString() {
        return "Pracownik{" + this.getBasicInfo() + "}";
    }

    protected String getBasicInfo(){
        return "id=" + id +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", dataUrodzenia=" + dataUrodzenia +
                ", dzial=" + dzial.getNazwa() +
                ", czyZdrowy=" + czyZdrowy;
    }

    public static void wyswietlZadaniaPracownikow(){
        for (Pracownik pracownik : wszyscyPracownicy){
            System.out.println(pracownik.getId() + " " + pracownik.getImieNazwisko() + ": ");
            if (pracownik.zadania.isEmpty()) {
                System.out.println("\tBrak zadań.");
            }
            else{
                System.out.println(pracownik.zadania + "not empt");
            }
            for (Zadanie zadanie : pracownik.zadania){
                System.out.println("\t" + zadanie);
            }
        }
    }

    @Override
    public int compareTo(Pracownik pracownik) {
        int ageComparison = this.dataUrodzenia.compareTo(pracownik.dataUrodzenia);
        if (ageComparison != 0) {
            return ageComparison;
        }

        int lastNameComparison = this.nazwisko.compareTo(pracownik.nazwisko);
        if (lastNameComparison != 0) {
            return lastNameComparison;
        }
        return 0;
    }

    public abstract void pracuj();

    public void dodajZadanie(Zadanie zadanie) {
        this.zadania.add(zadanie);
    }

    public void dodajZespol(Zespol zespol) {
        zespoly.add(zespol);
    }


    public List<Zespol> getZespoly() {
        return new ArrayList<>(zespoly);
    }


    public String getImieNazwisko() {
        return this.imie + " " + this.nazwisko;
    }

    public static List<Pracownik> getWszyscyPracownicy() {
        return new ArrayList<>(wszyscyPracownicy);
    }

    // Gettery i settery
    public int getId() { return id; }
    public String getImie() { return imie; }
    public void setImie(String imie) { this.imie = imie; }
    public String getNazwisko() { return nazwisko; }
    public void setNazwisko(String nazwisko) { this.nazwisko = nazwisko; }
    public LocalDate getDataUrodzenia() { return dataUrodzenia; }
    public boolean getCzyZdrowy() { return czyZdrowy; }
    public void setCzyZdrowy(boolean czyZdrowy) { this.czyZdrowy = czyZdrowy; }
    public DzialPracownikow getDzial() { return dzial; }
    public void setDzial(DzialPracownikow dzial) { this.dzial = dzial; }
}
