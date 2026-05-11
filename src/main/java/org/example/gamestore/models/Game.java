package org.example.gamestore.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.gamestore.enums.Device;
import org.example.gamestore.enums.Genre;
import org.example.gamestore.enums.Platform;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "games")
@Getter
@Setter
@NoArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String title;

    @DecimalMin(value = "0.0", inclusive = true)
    @Column(nullable = false)
    private BigDecimal price;

    @ElementCollection(targetClass = Platform.class)
    @CollectionTable(name = "game_platforms", joinColumns = @JoinColumn(name = "game_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "platform", nullable = false)
    private Set<Platform> platforms = new HashSet<>();

    @ElementCollection(targetClass = Genre.class)
    @CollectionTable(name = "game_genres", joinColumns = @JoinColumn(name = "game_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "genre", nullable = false)
    private Set<Genre> genres = new HashSet<>();

    @ElementCollection(targetClass = Device.class)
    @CollectionTable(name = "game_devices", joinColumns = @JoinColumn(name = "game_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "device", nullable = false)
    private Set<Device> devices = new HashSet<>();
}