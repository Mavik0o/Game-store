package org.example.gamestore.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.example.gamestore.enums.Device;
import org.example.gamestore.enums.Genre;
import org.example.gamestore.enums.Platform;

import java.math.BigDecimal;
import java.util.Set;

public record GameRequestDto (
        @NotBlank(message = "Game title is required")
        String title,

        @DecimalMin(value = "0.0", inclusive = true, message = "Price must be >= 0")
        BigDecimal price,

        @NotEmpty(message = "At least one genre is required")
        Set<Genre> genres,

        @NotEmpty(message = "At least one platform is required")
        Set<Platform> platforms,

        @NotEmpty(message = "At least one device is required")
        Set<Device> devices
    ){}
