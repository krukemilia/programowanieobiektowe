package com.capgemini.programowanie.obiektowe;


public class ClientNotFoundException extends RuntimeException {
  public ClientNotFoundException(String message) {
    super(message);
  }
}
