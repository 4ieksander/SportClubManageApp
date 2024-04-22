package com.sportclub.model;

import com.sportclub.util.IDobryPracownik;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public abstract class Pracownik implements Comparable<Pracownik>, IDobryPracownik {
    // zmienne statyczne niezmienne (final)
    private static final List<Pracownik> wszyscyPracownicy = new ArrayList<>();
    // zmienne statyczne zmienne
    private static int nextId = 1;          // ID są współdzielone przez te 3 pochodne klasy

    //zmienne instancji finalne (niezmienne)
    private final int id;
    //zmienne instancji prywatne
    private String imie;
    private String nazwisko;
    private LocalDate dataUrodzenia;
    private boolean czyZdrowy;
    private DzialPracownikow dzial;
    //zmienne instancji chronione
    protected List<Zespol> zespoly = new ArrayList<>();         // Aby pracownik mógł zobaczyc jakie ma zadania (getZespoly jest tylko w managerze, tak zrozumialem z zadania)
    protected List<Zadanie> zadania = new ArrayList<>();        // Do powyzszej listy dodawany jest zespol podczas dodawania, analogicznie zadania, znajdziesz w kodzie

    // konstruktor
    public Pracownik(String imie, String nazwisko, LocalDate dataUrodzenia, DzialPracownikow dzial) {
        this.id = nextId++;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUrodzenia = dataUrodzenia;
        this.dzial = dzial;
        dzial.dodajPracownika(this);    // dzial przechowuje pracownikow w nim
        this.czyZdrowy = true; // Domyślna wartość to true
        wszyscyPracownicy.add(this);        // baza wszystkich pracownikow
    }

    // metoda abstrakcyjna (trzeba ją zaimplementować po odziedziczeniu)
    public abstract void pracuj();  // wymaganie zadania


    // metody statyczne (klasy, nie instancji)
    public static void wyswietlZadaniaPracownikow(){        // wyświetla wszystkie zadania pracownikow, (dla każdego osobną listę) robi spory smietnik na ekranie
        for (Pracownik pracownik : wszyscyPracownicy){
            System.out.println(pracownik.getId() + " " + pracownik.getImieNazwisko() + ": ");
            if (pracownik.zadania.isEmpty()) {
                System.out.println("\tBrak zadań.");
            }
            for (Zadanie zadanie : pracownik.zadania){
                System.out.println("\t" + zadanie);
            }
        }
    }

    public static List<Pracownik> getWszyscyPracownicy() {
        return new ArrayList<>(wszyscyPracownicy);
    }


    // metody publiczne instancji
    public void dodajZadanie(Zadanie zadanie) {     // nie ma getZadania(), jest oon tylko w klasie Manager (tak zrozumiałem z instrukcji)
        this.zadania.add(zadanie);
    }

    public void dodajZespol(Zespol zespol) {        // wywolywane w dodawaniu do zespolu
        zespoly.add(zespol);
    }

    public List<Zespol> getZespoly() {
        return new ArrayList<>(zespoly);
    }

    // implementacja interfejsu comparable
    @Override
    public int compareTo(Pracownik pracownik) {         // ten interfejs nie wypluje innych wartości niż int (compareTo -> interfejs comparable, implementowany w definiowaniu klasy
        int ageComparison = this.dataUrodzenia.compareTo(pracownik.dataUrodzenia);
        if (ageComparison != 0) {
            return ageComparison;
        }

        int lastNameComparison = this.nazwisko.compareTo(pracownik.nazwisko);
        if (lastNameComparison != 0) {
            return lastNameComparison;          // nie wiem do konca jak to liczy, liczy musialbys poczytac o tej funkcji
        }

        return 0;   // teoretycznie nie potrzebne, mozna usunac, bo jesli lastNameComparison bedzie 0 to i tak przejdzie do wysłania 0
    }

    // nadpisanie metody toString()
    @Override
    public String toString() {
        return "Pracownik{" + this.getBasicInfo() + "}";
    }


    // Gettery i settery
    public int getId() { return id; }               // brak get bo id jest FINAL
    public String getImie() { return imie; }
    public void setImie(String imie) { this.imie = imie; }
    public String getNazwisko() { return nazwisko; }
    public void setNazwisko(String nazwisko) { this.nazwisko = nazwisko; }
    public LocalDate getDataUrodzenia() { return dataUrodzenia; }
    public void setDataUrodzenia(LocalDate dataUrodzenia) {}
    public boolean getCzyZdrowy() { return czyZdrowy; }
    public void setCzyZdrowy(boolean czyZdrowy) { this.czyZdrowy = czyZdrowy; }
    public DzialPracownikow getDzial() { return dzial; }
    public void setDzial(DzialPracownikow dzial) { this.dzial = dzial; }


    // metoda pomocnicza
    public String getImieNazwisko()
    {
        return this.imie + " " + this.nazwisko;
    }

    // miejsce na metody chronione i prywatne
    protected String getBasicInfo(){        // większość zmiennych są prywatnymi, dlatego aby getterow nie naduzywac w metodach toString
        return "id=" + id +                 // w klasach pochodnych, zrobiłem tą metodę, która zwraca tego stringa.
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", dataUrodzenia=" + dataUrodzenia +    // protected aby mozna bylo tego uzyc jedynie w klasach które dziedziczą Pracownik
                ", dzial=" + dzial.getNazwa() +
                ", czyZdrowy=" + czyZdrowy;
    }

}