package org.example;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private Address address;
    private List<Animal> adoptedAnimals;
    private List<Observer> observers = new ArrayList<>();


    public void addObserver(Observer observer) {
        observers.add(observer);
    }


    private void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public Student(String name) {
        this.name = name;
        this.adoptedAnimals = new ArrayList<>();
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Animal> getAdoptedAnimals() {
        return adoptedAnimals;
    }

    public void adoptAnimal(Animal animal) {
        if (animal == null || animal.isAdopted()) {
            throw new IllegalArgumentException("This animal is already adopted or is not valid.");
        }

        adoptedAnimals.add(animal);
        animal.setAdopted(true);
        notifyObservers(name + " has adopted " + animal.getName());
    }


    public void releaseAnimal(Animal animal) {
        if (animal == null || !adoptedAnimals.contains(animal)) {
            throw new IllegalArgumentException("This animal is not adopted by this student.");
        }

        adoptedAnimals.remove(animal);
        animal.setAdopted(false);
        notifyObservers(name + " has released " + animal.getName());
    }

}