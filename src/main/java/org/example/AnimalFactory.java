package org.example;

public class AnimalFactory {
    public static Animal createAnimal(String type) {
        switch (type.toLowerCase()) {
            case "dog":
                return new Animal("Dog", "Woof!");
            case "cat":
                return new Animal("Cat", "Meow!");
            default:
                throw new IllegalArgumentException("Unknown animal type: " + type);
        }
    }
}
