package fr.ufrsciencestech.projet.model;

public class Banane implements Fruit {
    private double prix;
    private String origine;

    public Banane() {
        this.prix = 0.5; // prix en euros
        this.origine = "Espagne";
    }

    public Banane(double prix, String origine) {
        if (prix < 0)
            this.prix = -prix; // une solution possible pour interdire les prix negatifs
        else
            this.prix = prix;

        if (origine.equals(""))
            this.origine = "Espagne"; // Espagne par défaut
        else
            this.origine = origine;
    }

    @Override
    public double getPrix() {
        return prix;
    }

    @Override
    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String getOrigine() {
        return origine;
    }

    @Override
    public void setOrigine(String origine) {
        this.origine = origine;
    }

    @Override
    public String toString() {
        return "Banane de " + origine + " a " + prix + " €";
    }

    @Override
    public boolean equals(Object o) { // predicat pour tester si 2 Bananes sont equivalentes
        if (o != null && getClass() == o.getClass()) {
            Banane ba = (Banane) o;
            return (prix == ba.prix && origine.equals(ba.origine));
        }
        return false;
    }

    @Override
    public boolean isSeedless() { // predicat indiquant qu'un fruit a des pepins
        return false;
    }

}

 