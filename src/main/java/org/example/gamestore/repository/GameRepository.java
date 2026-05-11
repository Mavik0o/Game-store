package org.example.gamestore.repository;

import org.example.gamestore.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> findByTitleIgnoreCase(String title);
}