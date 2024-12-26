package org.example;


import java.util.ArrayList;
import java.util.List;

public class Animal {
    private String name;
    private String sound;
    private boolean adopted;

    public Animal(String name, String sound) {
        this.name = name;
        this.sound = sound;
        this.adopted = false;
    }

    public String getName() {
        return this.name;
    }

    public void setAnimalName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public String makeSound() {
        return this.sound;
    }

    public boolean isAdopted() {
        return adopted;
    }

    public void setAdopted(boolean adopted) {
        this.adopted = adopted;
    }
}