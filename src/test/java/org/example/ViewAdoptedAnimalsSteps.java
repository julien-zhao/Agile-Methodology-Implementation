package org.example;

import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewAdoptedAnimalsSteps {

    private Student student;
    private List<Animal> adoptedAnimals = new ArrayList<>();

    @Given("a student named for final view {string}")
    public void aStudentNamed(String studentName) {
        student = new Student(studentName);
    }

    @Given("the animals adopted by {string} are:")
    public void theAnimalsAdoptedByAre(String studentName, io.cucumber.datatable.DataTable dataTable) {
        dataTable.asMaps().forEach(row -> {
            String name = row.get("Name");
            String type = row.get("Type");
            Animal animal = new Animal(name, type);
            student.adoptAnimal(animal); // Ajoute l'animal adopté à l'étudiant
            adoptedAnimals.add(animal); // Sauvegarde dans la liste locale pour vérifier plus tard
        });
    }

    @When("{string} views their list of adopted animals")
    public void viewsTheirListOfAdoptedAnimals(String studentName) {
        // Cette étape ne fait qu'exposer la liste des animaux adoptés
        // La vérification sera effectuée dans le Then.
    }

    @Then("the displayed list is:")
    public void theDisplayedListIs(io.cucumber.datatable.DataTable expectedTable) {
        List<Map<String, String>> expectedAdoptedAnimals = expectedTable.asMaps();

        for (Map<String, String> row : expectedAdoptedAnimals) {
            String name = row.get("Name");
            String type = row.get("Type");

            boolean found = adoptedAnimals.stream()
                    .anyMatch(animal -> animal.getName().equals(name) && animal.makeSound().equals(type));

            Assert.assertTrue("Expected animal not found: " + name, found);
        }

        // Vérifiez également que la taille des listes correspond
        Assert.assertEquals("Mismatch in the number of animals", expectedAdoptedAnimals.size(), adoptedAnimals.size());
    }
}
