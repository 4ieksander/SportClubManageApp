package com.sportclub.app;

import com.sportclub.model.*;
import com.sportclub.exception.NotUniqueNameException;

import java.time.LocalDate;

public class MainApplication {

    public static void main(String[] args) {
        DzialPracownikow dzialHR = null;
        DzialPracownikow dzialIT = null;

        try {
            // Tworzenie działów
            dzialHR = DzialPracownikow.createDzial("HR");
            dzialIT = DzialPracownikow.createDzial("IT");

            // Dodawanie pracowników do działu HR
            dzialHR.dodajPracownika("Jan Kowalski");
            dzialHR.dodajPracownika("Anna Nowak");

            // Dodawanie pracowników do działu IT
            dzialIT.dodajPracownika("Piotr Zalewski");
            dzialIT.dodajPracownika("Ewa Malinowska");

        } catch (NotUniqueNameException e) {
            System.out.println(e.getMessage());
            // Obsługa wyjątku, możliwe zakończenie lub zapis do logu
        }

        if (dzialHR != null && dzialIT != null) {
            // Tworzenie menedżerów i pracowników
            Manager manager = new Manager("Anna", "Kowalska", LocalDate.of(1985, 5, 5), dzialHR, "anna.k", "password123");
            Recepcjonista recepcjonista = new Recepcjonista("Marcin", "Nowak", LocalDate.of(1988, 4, 4), dzialIT, "marcin.n", "pass987");
            Trener trener = new Trener("Lukasz", "Budnik", LocalDate.of(1990, 2, 15), dzialHR, "Fitness");

            // Tworzenie zespołu
            Zespol itZespol = new Zespol("IT Team", manager);
            itZespol.dodajPracownika(recepcjonista);

            // Tworzenie zadań
            Zadanie zadanie1 = new Zadanie("Aktualizacja systemu", "Aktualizacja i deploy nowej wersji systemu.", true);
            Zadanie zadanie2 = new Zadanie("Szkolenie BHP", "Prowadzenie szkolenia BHP dla nowych pracowników.", false);
            zadanie2.setZatwierdzone(true); // Zatwierdzenie zadania do realizacji

            // Tworzenie pracy
            Praca praca = new Praca("Planowanie IT", itZespol);
            praca.dodajZadanie(zadanie1);
            praca.dodajZadanie(zadanie2);

            // Rozpoczęcie pracy w wątku
            Thread pracaThread = new Thread(praca);
            pracaThread.start();

            try {
                pracaThread.join(); // Czekanie na zakończenie pracy
            } catch (InterruptedException e) {
                System.out.println("Wykonanie pracy zostało przerwane.");
                Thread.currentThread().interrupt();
            }

            // Wyświetlanie informacji o zakończonych zadaniach
            System.out.println("Zakończone zadania:");
            if (zadanie1.getStan().equals("Zakonczone")) {
                System.out.println(zadanie1.getNazwa() + " - " + zadanie1.getOpis());
            }
            if (zadanie2.getStan().equals("Zakonczone")) {
                System.out.println(zadanie2.getNazwa() + " - " + zadanie2.getOpis());
            }
        } else {
            System.out.println("Błąd podczas tworzenia działów, nie można kontynuować.");
        }
    }
}
