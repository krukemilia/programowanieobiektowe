package com.capgemini.programowanie.obiektowe;

import java.time.LocalDate;
import java.util.Map;

public class Client {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate creationDate;
    private boolean isPremium;
    private static final double MAX_CAPACITY = 1000.00;  // pojemność
    private double usedCapacity = 0.00;  // ile jest już zajęte
    private Map<SupportedMetalType, Double> storedMetalTypes;

    public Client(String id, String firstName, String lastName, LocalDate creationDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.creationDate = creationDate;
        this.isPremium = false;
    }

    // Gettery i Settery 
    public String getId() {
        return id;
    }

  public String getFirstName() {
      return firstName;
  }

  public void setFirstName(String firstName) {
      this.firstName = firstName;
  }

  public String getLastName() {
      return lastName;
  }

  public void setLastName(String lastName) {
      this.lastName = lastName;
  }

  public LocalDate getCreationDate() {
      return creationDate;
  }

  public boolean isPremium() {
      return isPremium;
  }

  public void setPremium(boolean isPremium) {
      this.isPremium = isPremium;
  }

  public void addMetalIngot(SupportedMetalType metalType, double mass) {
      double currentMass = storedMetalTypes.getOrDefault(metalType, 0.0);
      storedMetalTypes.put(metalType, currentMass + mass);
  }
public class FullWarehouseException extends Exception {
    public FullWarehouseException(String message) {
        super(message);
    }
}

public Map<SupportedMetalType, Double> getStoredMetalTypes() {
    return this.storedMetalTypes;
}

public void addMetal(SupportedMetalType metalType, double mass) throws FullWarehouseException {
    if (usedCapacity + mass > MAX_CAPACITY) {
        throw new FullWarehouseException("Insufficient storage space remaining.");
    }

    usedCapacity += mass;

    double currentMass = storedMetalTypes.getOrDefault(metalType, 0.0);
    storedMetalTypes.put(metalType, currentMass + mass);
}
}
