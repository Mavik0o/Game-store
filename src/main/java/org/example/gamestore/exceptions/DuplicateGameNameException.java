package org.example.gamestore.exceptions;

public class DuplicateGameNameException extends RuntimeException {
    public DuplicateGameNameException(String message) {
        super("Game with name " + message + "already exists");
    }
}
