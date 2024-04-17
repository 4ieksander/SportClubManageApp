package com.sportclub.model;
import com.sportclub.exception.NotUniqueNameException;
import com.sportclub.model.DzialPracownikow;

import java.util.HashSet;
import java.util.Set;

public class DzialPracownikow {
    private static final Set<String> nazwyDzialow = new HashSet<>();

    private String nazwa;

    public DzialPracownikow(String nazwa) {
        this.nazwa = nazwa;
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
}