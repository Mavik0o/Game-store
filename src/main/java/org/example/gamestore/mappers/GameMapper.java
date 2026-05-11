package org.example.gamestore.mappers;


import org.example.gamestore.dto.GameRequestDto;
import org.example.gamestore.dto.GameResponseDto;
import org.example.gamestore.models.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GameMapper {

    GameResponseDto toDto(Game game);

    @Mapping(target = "id", ignore = true)
    Game toEntity(GameRequestDto gameRequestDto);
    

}