package org.example.gamestore.controllers;

//        Your company is selling digital games in an online store.
//
//        You are asked to implement this online store's catalogue backend API using a web framework (Spring Boot or Quarkus)
//
//        Visitors can browse and search the games via the API
//
//        Store admins can add, update and delete games via the API
//
//        The code is covered with tests

import org.example.gamestore.dto.GameResponseDto;
import org.example.gamestore.services.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public List<GameResponseDto> findAll() {
        return gameService.findAll();
    }

    @GetMapping("/{id}")
    public GameResponseDto getById(@PathVariable Long id) {
        return gameService.findById(id);
    }
}
