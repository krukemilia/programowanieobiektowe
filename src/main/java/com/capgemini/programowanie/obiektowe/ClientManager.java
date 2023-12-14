package com.capgemini.programowanie.obiektowe;

import java.time.LocalDate;
import java.util.*;
public class ClientManager implements Clients{
  private HashMap<String, Client> clients;

  public ClientManager() {
    this.clients = new HashMap<>();
  }
  @Override
  public String createNewClient(String firstName, String lastName) {
      String clientId = UUID.randomUUID().toString();
      Client newClient = new Client(clientId, firstName, lastName, LocalDate.now());
      clients.put(clientId, newClient);
      return clientId;
  }

    @Override
    public String activatePremiumAccount(String clientId) {
      Client client = clients.get(clientId);
      if (client != null) {
        client.setPremium(true);
        return clientId;
      } else{
        throw new ClientNotFoundException("Client not found: " + clientId);
      }
    }

  @Override
  public String getClientFullName(String clientId) {
      Client client = clients.get(clientId);
      if (client != null) {
          return client.getFirstName() + " " + client.getLastName();
      } else {
          throw new ClientNotFoundException("Client not found: " + clientId);     
      }
  }

  @Override
  public LocalDate getClientCreationDate(String clientId) {
      Client client = clients.get(clientId);
      if (client != null) {
          return client.getCreationDate();
      } else {
          throw new ClientNotFoundException("Client not found: " + clientId);
      }
      
  }

  @Override
  public boolean isPremiumClient(String clientId) {
      Client client = clients.get(clientId);
      if (client != null) {
          return client.isPremium();
      } else {
          throw new ClientNotFoundException("Client not found: " + clientId);
      }
  }

  @Override
  public int getNumberOfClients() {
      return clients.size();
      
  }

  @Override
  public int getNumberOfPremiumClients() {
      int count = 0;
      for (Client client : clients.values()) {
          if (client.isPremium()) {
              count++;
          }
      }
      return count;
  }
  public Client getClientById(String clientId) throws ClientNotFoundException {
    Client client = clients.get(clientId);
    if (client == null) {
        throw new ClientNotFoundException("Client not found: " + clientId);   
    }
    return client;
}
}

