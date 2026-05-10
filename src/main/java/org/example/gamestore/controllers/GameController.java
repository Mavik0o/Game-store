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

import jakarta.validation.Valid;
import org.example.gamestore.dto.GameRequestDto;
import org.example.gamestore.dto.GameResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @GetMapping
    public List<> findAll(@Valid @ModelAttribute  ) {
        return .findAll();
    }

    @GetMapping("/{id}")
    public GameResponseDto getById(@PathVariable Long id) {
        return GameService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GameResponseDto create(@RequestBody @Valid GameRequestDto requestDto) {
        return GameService.create(requestDto);
    }

    @PutMapping("/{id}")
    public GameResponseDto update(@PathVariable Long id,
                                     @RequestBody @Valid GameRequestDto requestDto) {
        return GameService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        GameRequestDto.delete(id);
    }

}
