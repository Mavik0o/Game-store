package org.example.gamestore.dto;

import org.example.gamestore.enums.Device;
import org.example.gamestore.enums.Genre;
import org.example.gamestore.enums.Platform;

import java.math.BigDecimal;
import java.util.Set;

public record GameResponseDto (
        Long id,
        String title,
        BigDecimal price,
        Set<Platform> platforms,
        Set<Genre> genres,
        Set<Device> devices
    ) {}
