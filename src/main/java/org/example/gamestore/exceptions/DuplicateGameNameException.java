package org.example.gamestore.exceptions;

public class DuplicateGameNameException extends RuntimeException {
    public DuplicateGameNameException(String message) {
        super("Game with title " + message + " already exists");
    }
}
