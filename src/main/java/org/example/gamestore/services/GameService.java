package org.example.gamestore.services;

import org.example.gamestore.dto.GameRequestDto;
import org.example.gamestore.dto.GameResponseDto;
import org.example.gamestore.exceptions.DuplicateGameNameException;
import org.example.gamestore.exceptions.GameNotFoundException;
import org.example.gamestore.models.Game;
import org.example.gamestore.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<GameResponseDto> findAll() {
        return gameRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    public GameResponseDto findById(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));

        return mapToResponseDto(game);
    }

    public GameResponseDto create(GameRequestDto requestDto) {
        if (gameRepository.findByTitleIgnoreCase(requestDto.title()).isPresent()) {
            throw new DuplicateGameNameException(requestDto.title());
        }

        Game game = mapToEntity(requestDto);
        Game savedGame = gameRepository.save(game);

        return mapToResponseDto(savedGame);
    }

    public GameResponseDto update(Long id, GameRequestDto requestDto) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));

        boolean duplicateExists = gameRepository.findByTitleIgnoreCase(requestDto.title())
                .map(found -> !found.getId().equals(id))
                .orElse(false);

        if (duplicateExists) {
            throw new DuplicateGameNameException(requestDto.title());
        }

        game.setTitle(requestDto.title());
        game.setPrice(requestDto.price());
        game.setGenres(requestDto.genres());
        game.setPlatforms(requestDto.platforms());
        game.setDevices(requestDto.devices());

        Game updatedGame = gameRepository.save(game);
        return mapToResponseDto(updatedGame);
    }

    public void delete(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));

        gameRepository.delete(game);
    }
    private GameResponseDto mapToResponseDto(Game game) {
        return new GameResponseDto(
                game.getId(),
                game.getTitle(),
                game.getPrice(),
                game.getPlatforms(),
                game.getGenres(),
                game.getDevices()
        );
    }

    private Game mapToEntity(GameRequestDto dto) {
        Game game = new Game();
        game.setTitle(dto.title());
        game.setPrice(dto.price());
        game.setGenres(dto.genres());
        game.setPlatforms(dto.platforms());
        game.setDevices(dto.devices());
        return game;
    }

}
