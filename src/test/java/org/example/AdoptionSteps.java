package org.example;

import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdoptionSteps {

    private Student student;
    private Map<String, Animal> availableAnimals = new HashMap<>();
    private List<Animal> adoptedAnimals = new ArrayList<>();

    @Given("a student named for final {string}")
    public void aStudentNamed(String studentName) {
        student = new Student(studentName);
    }

    @Given("the available animals are:")
    public void theAvailableAnimalsAre(io.cucumber.datatable.DataTable dataTable) {
        dataTable.asMaps().forEach(row -> {
            String name = row.get("Name");
            String type = row.get("Type");
            boolean adopted = Boolean.parseBoolean(row.get("Adopted"));
            Animal animal = new Animal(name, type);
            animal.setAdopted(adopted);
            availableAnimals.put(name, animal);
        });
    }

    @When("the student {string} adopts the animal {string}")
    public void theStudentAdoptsTheAnimal(String studentName, String animalName) {
        Animal animal = availableAnimals.get(animalName);
        if (animal == null) {
            throw new IllegalArgumentException("Animal not found: " + animalName);
        }
        student.adoptAnimal(animal);
        adoptedAnimals.add(animal);
    }

    @Then("the list of animals adopted by {string} is:")
    public void theListOfAnimalsAdoptedByIs(String studentName, io.cucumber.datatable.DataTable expectedTable) {
        List<Map<String, String>> expectedAdoptedAnimals = expectedTable.asMaps();
        for (Map<String, String> row : expectedAdoptedAnimals) {
            String name = row.get("Name");
            String type = row.get("Type");

            boolean found = adoptedAnimals.stream()
                    .anyMatch(animal -> animal.getName().equals(name) && animal.makeSound().equals(type));

            Assert.assertTrue("Expected animal not found: " + name, found);
        }
    }

    @Then("the adoption status of {string} is {string}")
    public void theAdoptionStatusOfIs(String animalName, String expectedStatus) {
        Animal animal = availableAnimals.get(animalName);
        boolean isAdopted = Boolean.parseBoolean(expectedStatus);
        Assert.assertEquals(isAdopted, animal.isAdopted());
    }
}
