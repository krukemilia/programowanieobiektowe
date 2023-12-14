package com.capgemini.programowanie.obiektowe;
import java.util.*;

public class WarehouseManager implements Warehouse {
   
    // Przechowuje dane dotyczące magazynowanego metalu; Mapa klientów do Mapy rodzaju metalu do masy
    private final Map<String, Map<SupportedMetalType, Double>> storage = new HashMap<>();

    // Manager do zarządzania klientami
    private ClientManager clientManager = new ClientManager();

    // Maksymalna masa, jaką może pomieścić magazyn
    private double maxWarehouseMass = 1000.0; 

    // Metoda pomocnicza, która sprawdza, czy dany typ metalu jest obsługiwany
    private boolean isMetalTypeSupported(SupportedMetalType metalType) {
        for (SupportedMetalType supportedMetal : SupportedMetalType.values()) {
            if (supportedMetal == metalType) {
                return true;
            }
        }
        return false;
    }

    // Dodaj do magazynu szczelina danego metalu dla danego klienta
    @Override
    public void addMetalIngot(String clientId, SupportedMetalType metalType, double mass)
        throws ClientNotFoundException, ProhibitedMetalTypeException, FullWarehouseException {

        // Sprawdź, czy istnieje klient
        Client client = clientManager.getClientById(clientId);
        if (client == null) {
            throw new ClientNotFoundException("Client not found: " + clientId);
        } 

        // Sprawdź, czy typ metalu jest obsługiwany
        if (!isMetalTypeSupported(metalType)) {
            throw new ProhibitedMetalTypeException("The metal type " + metalType + " is not supported for storage.");
        }

        // Zapewnienie, że dla każdego klienta utworzona jest mapa składu
        if (!storage.containsKey(clientId)) {
            storage.put(clientId, new HashMap<>());
        }

        // Dodaj klocek metalu do magazynu klienta
        Map<SupportedMetalType, Double> clientStorage = storage.get(clientId);
        double currentMass = clientStorage.getOrDefault(metalType, 0.0);
        double newMass = currentMass + mass;

        // Sprawdzanie, czy nowy klocek metalu i obecna masa nie przekroczą maksymalnej masy magazynu
        if (mass > maxWarehouseMass || newMass > maxWarehouseMass) {
            throw new FullWarehouseException("Warehouse full. Cannot add more metal ingot.");
        }

        // Aktualizowanie masy składowanego metalu
        clientStorage.put(metalType, newMass);
    }

    // Zwraca mapę typów metalu do masy magazynowanej przez danego klienta
    @Override
    public Map<SupportedMetalType, Double> getMetalTypesToMassStoredByClient(String clientId) {
        return storage.getOrDefault(clientId, new HashMap<>());
    }

    // Zwraca całkowitą masę magazynowaną przez danego klienta
    @Override
    public double getTotalVolumeOccupiedByClient(String clientId) {
        double totalMass = 0.0;
        Map<SupportedMetalType, Double> clientStorage = storage.get(clientId);
        if (clientStorage != null) {
            for (double mass : clientStorage.values()) {
                totalMass += mass;
            }
        }
        return totalMass;
    }

    // Zwraca listę typów metalu przechowywanych przez danego klienta
    @Override
    public List<SupportedMetalType> getStoredMetalTypesByClient(String clientId) {
        Map<SupportedMetalType, Double> clientMetals = storage.get(clientId);
        if (clientMetals != null) {
            return new ArrayList<>(clientMetals.keySet());
        }
        return new ArrayList<>();
    }
}
