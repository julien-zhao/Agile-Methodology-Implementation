package Test;

import io.cucumber.java.en.*;
import org.example.Address;
import org.example.Student;
import static org.junit.jupiter.api.Assertions.*;

public class feature {
    private Student student;
    private Address address;

    @Given("a student named {string}")
    public void a_student_named(String name) {
        student = new Student(name);
    }

    @Given("an address {string}")
    public void an_address(String street) {
        address = new Address(street);
    }

    @When("I associate the student with the address")
    public void i_associate_student_with_address() {
        student.setAddress(address);
    }

    @Then("the student should have the address {string}")
    public void the_student_should_have_the_address(String street) {
        assertEquals(street, student.getAddress().getStreet());
    }


}
