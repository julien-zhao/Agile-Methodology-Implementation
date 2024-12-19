import org.example.Adresse;
import org.example.Etudiant;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EtudiantTest {
    @Test
    void testAssociationBidirectionnelle() {
        Adresse adresse = new Adresse("123 Rue Principale");
        Etudiant etudiant = new Etudiant("Alice");

        assertNull(etudiant.getAdresse());
        assertNull(adresse.getEtudiant());

        etudiant.setAdresse(adresse); // rename

        assertEquals(adresse, etudiant.getAdresse()); //extractMethod
        assertEquals(etudiant, adresse.getEtudiant()); // extractMethod

        etudiant.setAdresse(null);
        adresse.setEtudiant(null);

        assertNull(etudiant.getAdresse());
        assertNull(adresse.getEtudiant()); // erreur
    }
}

