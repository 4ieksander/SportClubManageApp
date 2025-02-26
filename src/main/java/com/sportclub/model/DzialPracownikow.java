package com.sportclub.model;
import com.sportclub.exception.NotUniqueNameException;

import java.util.HashSet;
import java.util.Set;

public class DzialPracownikow {
    private static final Set<String> nazwyDzialow = new HashSet<>();    // zmienna statyczna, czyli własnośc klasy a nie obiektu
    private static int nextId = 1;      // jako klasa trzyma następne id

    private final int id;       // final czyli po stworzeniu już nie można jej zmienić
    private String nazwa;
    private Set<Pracownik> pracownicy;


    // konstruktor prywatny, ponieważ tworzyć dzial można jedynie poprzez metodę statyczną createDzial, tak to zrozumialem i zaimplementowalem
    private DzialPracownikow(String nazwa) {
        this.id = nextId++;         // przypisuje jej wartosc zmiennej statycznej do siebie i ją inkrementuje
        this.nazwa = nazwa;
        this.pracownicy = new HashSet<>();
    }


    public static DzialPracownikow createDzial(String nazwa) throws NotUniqueNameException {
        if (nazwyDzialow.contains(nazwa)) {
            throw new NotUniqueNameException(nazwa);
        }
        nazwyDzialow.add(nazwa);
        return new DzialPracownikow(nazwa);
    }

    public void dodajPracownika(Pracownik pracownik) {
        pracownicy.add(pracownik);
    }


    @Override
    public String toString() {
        return "DzialPracownikow{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                ", pracownicy=" + pracownicy.stream().map(Pracownik::toString).toList() +      // robi listę pracowników
                '}';
    }


    // gettery i settery
    public Set<Pracownik> getPracownicy() {
        return new HashSet<>(pracownicy);
    }
    public String getNazwa() {
        return nazwa;
    }
    public void setNazwa(String nazwa) {this.nazwa = nazwa;}
    public int getId() {
        return id;
    }



}