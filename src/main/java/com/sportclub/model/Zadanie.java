package com.sportclub.model;

import java.time.LocalDateTime;
import java.util.Random;

public class Zadanie extends Thread {
    private String nazwa;
    private String opis;
    private enum Stan { Utworzone, Rozpoczete, Zakonczone }
    private Stan stan;
    private LocalDateTime dataUtworzenia;
    private LocalDateTime dataZakonczenia;
    private int czasWykonania;

    public Zadanie(String nazwa, String opis) {
        this.nazwa = nazwa;
        this.opis = opis;
        this.stan = Stan.Utworzone;
        this.dataUtworzenia = LocalDateTime.now();
        this.czasWykonania = new Random().nextInt(6) + 3;  // losowa wartość między 3 a 8 sekundami
    }

    public Zadanie(String nazwa) {
        this(nazwa, "Brak opisu");
    }

    @Override
    public void run() {
        if (stan == Stan.Utworzone) {
            stan = Stan.Rozpoczete;
            System.out.println("Rozpoczęcie zadania: " + nazwa);
            try {
                for(int i = 0; i < czasWykonania; i++) {
                    Thread.sleep(1000);
                    System.out.println("Zadanie '" + nazwa + "' - pozostały czas: " + i + "s...");
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

    public String getStan() {
        return stan.name();
    }

    public LocalDateTime getDataUtworzenia() {
        return dataUtworzenia;
    }

    public LocalDateTime getDataZakonczenia() {
        return dataZakonczenia;
    }
}