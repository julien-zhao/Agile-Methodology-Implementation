package org.example;

public class Adresse {
    private String rue;
    private Etudiant etudiant;

    public Adresse(String rue) {
        this.rue = rue;
    }

    public String getRue() {
        return rue;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
        if (etudiant != null && etudiant.getAdresse() != this) {
            etudiant.setAdresse(this);
        }
    }
}
