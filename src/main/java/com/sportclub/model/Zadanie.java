package com.sportclub.model;

import java.time.LocalDateTime;
import java.util.Random;

public class Zadanie extends Thread {
    private static int nextId = 1;
    private final int id;
    private String nazwa;
    private String opis;
    private enum Stan { Utworzone, Rozpoczete, Zakonczone }
    private Stan stan;
    private boolean zatwierdzone;
    private LocalDateTime dataUtworzenia;
    private LocalDateTime dataZakonczenia;
    private int czasWykonania;


    // Konstruktory
    public Zadanie(String nazwa, String opis, boolean zatwierdzone) {
        this.id = nextId++;
        this.nazwa = nazwa;
        this.opis = opis;
        this.zatwierdzone = zatwierdzone;
        this.stan = Stan.Utworzone;
        this.dataUtworzenia = LocalDateTime.now();
        this.czasWykonania = new Random().nextInt(6) + 3;  // losowa wartość między 3 a 8 sekundami
    }

    public Zadanie(String nazwa) {
        this(nazwa, "Brak opisu", false);
    }


    // Główna metoda
    @Override
    public void run() {
        if (!zatwierdzone) {
            System.out.println("Zadanie " + nazwa + " nie jest zatwierdzone i nie może być rozpoczęte.");
            return;
        }
        if (stan == Stan.Utworzone) {
            stan = Stan.Rozpoczete;
            System.out.println("Rozpoczęcie zadania: " + nazwa);
            try {
                for(int i = 0; i < czasWykonania; i++) {
                    Thread.sleep(1000);
                    System.out.println("Zadanie '" + nazwa + "' - pozostały czas: " + (czasWykonania - i) + "s...");
                }
            } catch (InterruptedException e) {
                System.out.println("Zadanie zostało przerwane");
                Thread.currentThread().interrupt();
            }
            stan = Stan.Zakonczone;
            dataZakonczenia = LocalDateTime.now();
            System.out.println("Zakończenie zadania: " + nazwa);
        } else {
            System.out.println("Zadanie nie może być rozpoczęte ponownie.");
        }
    }


    // gettery i settery

    public int getZadanieId() { // getId() jest zajęte przez wbudowaną metodę zwracającą rzeczywiste id
        return this.id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getStan() {
        return stan.name();
    }

    public boolean isZatwierdzone() {
        return zatwierdzone;
    }

    public void setZatwierdzone(boolean zatwierdzone) {
        this.zatwierdzone = zatwierdzone;
    }

    public LocalDateTime getDataUtworzenia() {
        return dataUtworzenia;
    }

    public LocalDateTime getDataZakonczenia() {
        return dataZakonczenia;
    }

}