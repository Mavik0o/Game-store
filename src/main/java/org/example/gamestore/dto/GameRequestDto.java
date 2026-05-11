package org.example.gamestore.dto;

import jakarta.validation.constraints.NotBlank;
import org.aspectj.weaver.ast.Not;
import org.example.gamestore.enums.Device;
import org.example.gamestore.enums.Genre;
import org.example.gamestore.enums.Platform;

import java.util.Set;

public record GameRequestDto (
    @NotBlank(message = "Game name is required")
    String title,

    @NotBlank(message = "Game genre is required")
    Set<Genre> genres,

    @NotBlank(message = "Game platforms are required")
    Set<Platform> platforms,

    @NotBlank(message = "Game devices are required")
    Set<Device> devices
    ){}
