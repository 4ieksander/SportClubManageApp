package com.sportclub.model;

import java.time.LocalDate;


public class Recepcjonista extends Pracownik {
    private String login;
    private String haslo;
    private String initial;

    public Recepcjonista(String imie, String nazwisko, LocalDate dataUrodzenia, DzialPracownikow dzial, String login, String haslo) {
        super(imie, nazwisko, dataUrodzenia, dzial);
        this.login = login;
        this.haslo = haslo;
        this.initial = generateInitial(imie, nazwisko);
    }

    // Metoda specyficzna dla recepcjonisty
    public void sprawdzKlienta(String guestName) {
        System.out.println("Sprawdzanie klienta " + guestName);
    }

    // ....abstrakcyjnej
    @Override
    public void pracuj() {
        System.out.println("Obsługa klientów na recepcji.");
    }

    // implementacja interfejsu
    @Override
    public void pracujEfektywnie() {
        System.out.println("Efektywna obsługa zapytań i rezerwacji.");
    }

    @Override
    public void współpracujZInnymi() {
        System.out.println("Współpraca z innymi działami firmy.");
    }

    @Override
    public void rozwijajUmiejętności() {
        System.out.println("Uczestnictwo w szkoleniach z obsługi klienta.");
    }

    // Obsługa zmiany imienia lub nazwiska z aktualizacją inicjałów, dlatego nadpisane
    @Override
    public void setImie(String imie) {
        super.setImie(imie);
        this.initial = generateInitial(imie, getNazwisko());
    }

    @Override
    public void setNazwisko(String nazwisko) {
        super.setNazwisko(nazwisko);
        this.initial = generateInitial(getImie(), nazwisko);
    }

    @Override
    public String toString() {
        return "Recepcjonista{" +
                this.getBasicInfo() +
                ", login='" + login + '\'' +
                ", haslo='" + haslo + '\'' +
                ", initial='" + initial + '\'' +
                '}';
    }


    // Gettery i settery
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getHaslo() { return haslo; }
    public void setHaslo(String haslo) { this.haslo = haslo; }
    public String getInitial() { return initial; }


    // metoda prywatna
    private String generateInitial(String imie, String nazwisko) {
        return "" + imie.charAt(0) + nazwisko.charAt(0);
    }

}

