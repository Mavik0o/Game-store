package org.example.gamestore.services;

import org.example.gamestore.dto.GameRequestDto;
import org.example.gamestore.dto.GameResponseDto;
import org.example.gamestore.exceptions.DuplicateGameNameException;
import org.example.gamestore.exceptions.GameNotFoundException;
import org.example.gamestore.models.Game;
import org.example.gamestore.repository.GameRepository;
import org.example.gamestore.mappers.GameMapper;
import org.springframework.data.jpa.domain.Specification;
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
    public GameResponseDto findById(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));

        return GameMapper.toDto(game);
    }

    public GameResponseDto create(GameRequestDto requestDto) {
        if (gameRepository.findByNameIgnoreCase(requestDto.title()).isPresent()) {
            throw new DuplicateGameNameException(requestDto.title());
        }
        Game game = GameMapper.toEntity(requestDto);

        game = gameRepository.save(game);
        return GameMapper.toDto(game);
    }

    public GameResponseDto update(Long id, GameRequestDto requestDto) {
        Game game = GameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));

        boolean duplicateNameExists = gameRepository.findByNameIgnoreCase(requestDto.title())
                .map(found -> !found.getId().equals(id))
                .orElse(false);

        if (duplicateNameExists) {
            throw new DuplicateGameNameException(requestDto.title());
        }

        game.setTitle(requestDto.title());
        game.setPlatforms(requestDto.platforms());
        game.setGenres(requestDto.genres());
        game.setDevices(requestDto.devices());

        game = gameRepository.save(game);
        return GameMapper.toDto(game);
    }

    public void delete(Long id) {
        Game game = GameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));

        GameRepository.delete(game);
    }

    public Game getGameEntityById(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));
    }

}
