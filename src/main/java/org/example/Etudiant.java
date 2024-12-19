package org.example;

public class Etudiant {
    private String nom;
    private Adresse adresse;

    public Etudiant(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
        if (adresse != null && adresse.getEtudiant() != this) {
            adresse.setEtudiant(this);
        }
    }
}
