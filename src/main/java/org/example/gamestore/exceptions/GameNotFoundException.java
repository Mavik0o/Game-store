package org.example.gamestore.exceptions;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(Long id) {
      super("Game with id " + id + " not found");
    }
}
