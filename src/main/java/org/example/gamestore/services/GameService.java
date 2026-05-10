package org.example.gamestore.services;

import org.example.gamestore.repository.GameRepository;

public class GameService {
    private final GameRepository gameRepository;


    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

}
