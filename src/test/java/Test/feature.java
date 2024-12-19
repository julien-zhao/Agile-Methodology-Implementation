package Test;

import io.cucumber.java.en.*;
import org.example.Adresse;
import org.example.Etudiant;

import static org.junit.jupiter.api.Assertions.*;

public class feature {
    private Etudiant etudiant;
    private Adresse adresse;

    @Given("un étudiant nommé {string}")
    public void un_etudiant_nomme(String nom) {
        etudiant = new Etudiant(nom);
    }

    @Given("une adresse {string}")
    public void une_adresse(String rue) {
        adresse = new Adresse(rue);
    }

    @When("j'associe l'étudiant à l'adresse")
    public void j_associe_etudiant_a_adresse() {
        etudiant.setAdresse(adresse);
    }

    @Then("l'étudiant devrait avoir l'adresse {string}")
    public void letudiant_devrait_avoir_ladresse(String rue) {
        assertEquals(rue, etudiant.getAdresse().getRue());
    }

    @Then("l'adresse devrait être associée à l'étudiant {string}")
    public void ladresse_devrait_etre_associee_a_letudiant(String nom) {
        assertEquals(nom, adresse.getEtudiant().getNom());
    }
}