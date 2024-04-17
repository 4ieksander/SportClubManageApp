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

    private String generateInitial(String imie, String nazwisko) {
        return "" + imie.charAt(0) + nazwisko.charAt(0);
    }

    @Override
    public void pracuj() {
        System.out.println("Obsługa klientów na recepcji.");
    }

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

    // Gettery i settery
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getHaslo() { return haslo; }
    public void setHaslo(String haslo) { this.haslo = haslo; }
    public String getInitial() { return initial; }
    // Uwzględnienie zmiany imienia lub nazwiska
    public void setImie(String imie) {
        super.setImie(imie);
        this.initial = generateInitial(imie, getNazwisko());
    }
    public void setNazwisko(String nazwisko) {
        super.setNazwisko(nazwisko);
        this.initial = generateInitial(getImie(), nazwisko);
    }
}