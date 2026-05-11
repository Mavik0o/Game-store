package org.example.gamestore.controllers;

import jakarta.validation.Valid;
import org.example.gamestore.dto.GameRequestDto;
import org.example.gamestore.dto.GameResponseDto;
import org.example.gamestore.services.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/games")
public class AdminGameController {

    private final GameService gameService;

    public AdminGameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GameResponseDto create(@RequestBody @Valid GameRequestDto requestDto) {
        return gameService.create(requestDto);
    }

    @PutMapping("/{id}")
    public GameResponseDto update(@PathVariable Long id,
                                  @RequestBody @Valid GameRequestDto requestDto) {
        return gameService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        gameService.delete(id);
    }
}