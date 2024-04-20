package com.sportclub.model;
import com.sportclub.exception.NotUniqueNameException;
import com.sportclub.model.DzialPracownikow;

import java.util.HashSet;
import java.util.Set;

public class DzialPracownikow {
    private static int nextId = 1;
    private final int id;
    private static final Set<String> nazwyDzialow = new HashSet<>();
    private String nazwa;
    private Set<Pracownik> pracownicy;

    private DzialPracownikow(String nazwa) {
        this.id = nextId++;
        this.nazwa = nazwa;
        this.pracownicy = new HashSet<>();
    }

    @Override
    public String toString() {
        return "DzialPracownikow{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                ", pracownicy=" + pracownicy.stream().map(Pracownik::toString).toList() +
                '}';
    }


    public static DzialPracownikow createDzial(String nazwa) throws NotUniqueNameException {
        if (nazwyDzialow.contains(nazwa)) {
            throw new NotUniqueNameException("Nazwa działu '" + nazwa + "' jest już używana.");
        }
        nazwyDzialow.add(nazwa);
        return new DzialPracownikow(nazwa);
    }

    public String getNazwa() {
        return nazwa;
    }

    public void dodajPracownika(Pracownik pracownik) {
        pracownicy.add(pracownik);
    }

    public Set<Pracownik> getPracownicy() {
        return new HashSet<>(pracownicy);
    }
}