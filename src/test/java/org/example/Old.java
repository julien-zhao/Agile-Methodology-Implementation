package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.HashMap;
import java.util.Map;

public class Old {

    private final Map<String, String> studentAddressMap = new HashMap<>();

    @Given("a student named {string}")
    public void a_student_named(String name) {
        studentAddressMap.put(name, null);
    }

    @Given("an address {string}")
    public void an_address(String address) {
    }

    @When("I associate the student {string} with the address {string}")
    public void i_associate_the_student_with_the_address(String name, String address) {
        studentAddressMap.put(name, address);
    }

    @Then("the student {string} should have the address {string}")
    public void the_student_should_have_the_address(String name, String address) {
        assert studentAddressMap.get(name).equals(address) : "Expected address to be " + address + " but got " + studentAddressMap.get(name);
    }





    // Scenario Outline: Associate a student with an address
    @Given("a new address {string}")
    public void a_new_address(String address) {
        System.out.println("New address: " + address);
    }


    @Given("a student named {string} with an existing address {string}")
    public void a_student_with_an_existing_address(String name, String oldAddress) {
        studentAddressMap.put(name, oldAddress);
    }

    @When("I update the address of the student {string} to {string}")
    public void i_update_the_address_of_the_student(String name, String newAddress) {
        studentAddressMap.put(name, newAddress);
    }

    @Then("the student {string} should have the new address {string}")
    public void the_student_should_have_the_new_address(String name, String newAddress) {
        assert studentAddressMap.get(name).equals(newAddress) : "Expected new address to be " + newAddress;
    }

    @Then("the address {string} should no longer be associated with the student {string}")
    public void the_address_should_no_longer_be_associated_with_the_student(String oldAddress, String name) {
        assert !studentAddressMap.get(name).equals(oldAddress) : "Expected the old address to no longer be associated with the student " + name;
    }







    // Scenario to check that a student cannot be associated with a null or empty address
    @Given("a student named {string} without an address")
    public void a_student_named_without_an_address(String name) {
        studentAddressMap.put(name, null);  // Create the student without an address
    }

    @When("I attempt to associate the student {string} with an address {string}")
    public void i_attempt_to_associate_the_student_with_an_address(String name, String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Anomaly: Address cannot be null or empty.");
        } else {
            studentAddressMap.put(name, address);
        }
    }

    @Then("an anomaly should be generated indicating that the address cannot be null or empty")
    public void an_anomaly_should_be_generated_indicating_that_the_address_cannot_be_null_or_empty() {
        try {
            assert false : "Expected an anomaly because the address cannot be null or empty.";
        } catch (IllegalArgumentException e) {
            assert e.getMessage().contains("Address cannot be null or empty") : "Expected anomaly message but got: " + e.getMessage();
        }
    }
}
