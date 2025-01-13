import org.example.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTests {
    @Test
    void testUnidirectionalAssociation() {
        Address address = new Address("123 Main Street");
        Student student = new Student("Alice");

        assertNull(student.getAddress());

        setStudentAddress(student, address);

        assertEquals(address, student.getAddress());
        assertEquals("123 Main Street", address.getStreet());

        student.setAddress(null);

        assertNull(student.getAddress());
    }

    private void setStudentAddress(Student student, Address address) {
        student.setAddress(address);
    }


//*****************************************************************************//
//**********************************Tuto final*********************************//



//********************************Student Name*********************************************//

    @Test
    void testSetStudentName() {
        Student student = new Student("Original");
        student.setName("Updated");

        assertEquals("Updated", student.getName());
    }

    @Test
    void testSetInvalidStudentName() {
        Student student = new Student("Valid Name");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> student.setName(""));
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }
    @Test
    void testSetNameNull() {
        Student student = new Student("Alice");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            student.setName(null);
        });

        assertEquals("Name cannot be null or empty", exception.getMessage());
    }



    //******************************Animal Name***********************************************//

    @Test
    void testSetAnimalNameValid() {
        Animal animal = new Animal("Dog","Woof!");

        animal.setAnimalName("Cat");

        assertEquals("Cat", animal.getName());
    }

    @Test
    void testSetAnimalNameEmpty() {
        Animal animal = new Animal("Dog","Woof!");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            animal.setAnimalName("");  // Essayer de définir un nom vide
        });

        assertEquals("Name cannot be null or empty", exception.getMessage());
    }

    @Test
    void testSetAnimalNameNull() {
        Animal animal = new Animal("Dog","Woof!");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            animal.setAnimalName(null);
        });

        assertEquals("Name cannot be null or empty", exception.getMessage());
    }

    //********************************Adopt Animal*********************************************//

    @Test
    void testAdoptAnimalSuccessfully() {
        // Arrange
        Student student = new Student("Bob");
        Animal animal = new Animal("Dog", "Woof!");

        // Act
        student.adoptAnimal(animal);

        // Assert
        assertTrue(animal.isAdopted());
        assertTrue(student.getAdoptedAnimals().contains(animal));
    }

    @Test
    void testAdoptAnimalAlreadyAdopted() {
        // Arrange
        Student student = new Student("Bob");
        Animal animal = new Animal("Dog", "Woof!");
        student.adoptAnimal(animal);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            student.adoptAnimal(animal);
        });

        assertEquals("This animal is already adopted or is not valid.", exception.getMessage());
    }

    @Test
    void testAdoptNullAnimal() {
        // Arrange
        Student student = new Student("Alice");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            student.adoptAnimal(null);
        });

        assertEquals("This animal is already adopted or is not valid.", exception.getMessage());
    }




    //********************************Release Animal*********************************************//
    @Test
    void testReleaseAnimalSuccessfully() {
        // Arrange
        Student student = new Student("Bob");
        Animal animal = new Animal("Dog", "Woof!");
        student.adoptAnimal(animal);

        // Act
        student.releaseAnimal(animal);

        // Assert
        assertFalse(animal.isAdopted());
        assertFalse(student.getAdoptedAnimals().contains(animal));
    }

    @Test
    void testReleaseAnimalNotAdopted() {
        // Arrange
        Student student = new Student("Bob");
        Animal animal = new Animal("Dog", "Woof!");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            student.releaseAnimal(animal);
        });

        assertEquals("This animal is not adopted by this student.", exception.getMessage());
    }

    @Test
    void testReleaseNullAnimal() {
        // Arrange
        Student student = new Student("Alice");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            student.releaseAnimal(null);
        });

        assertEquals("This animal is not adopted by this student.", exception.getMessage());
    }


    //********************************Pattern Observer : Notification*********************************************//
    @Test
    void testAnimalAdoptionNotification() {
        Student student = new Student("Diana");
        Animal dog = new Animal("Dog", "Woof!");
        AdoptionObserver observer = new AdoptionObserver();

        student.addObserver(observer);

        student.adoptAnimal(dog);

        List<String> notifications = observer.getNotifications();
        assertEquals(1, notifications.size());
        assertEquals("Diana has adopted Dog", notifications.get(0));
    }

    @Test
    void testAnimalReleaseNotification() {
        Student student = new Student("Eve");
        Animal cat = new Animal("Cat", "Meow!");
        AdoptionObserver observer = new AdoptionObserver();

        student.addObserver(observer);
        student.adoptAnimal(cat);

        assertEquals(1, observer.getNotifications().size());

        student.releaseAnimal(cat);

        List<String> notifications = observer.getNotifications();
        assertEquals(2, notifications.size());
        assertEquals("Eve has released Cat", notifications.get(1));
    }


    //********************************Factory Pattern*********************************************//
    @Test
    void testFactoryPattern() {
        Animal dog = AnimalFactory.createAnimal("dog");
        Animal cat = AnimalFactory.createAnimal("cat");

        assertEquals("Dog", dog.getName());
        assertEquals("Woof!", dog.makeSound());

        assertEquals("Cat", cat.getName());
        assertEquals("Meow!", cat.makeSound());

    }

    @Test
    void testFactoryPatternInvalidType() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            AnimalFactory.createAnimal("dragon");
        });

        assertEquals("Unknown animal type: dragon", exception.getMessage());
    }
}
