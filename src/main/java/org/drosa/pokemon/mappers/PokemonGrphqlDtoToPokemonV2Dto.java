package org.drosa.pokemon.mappers;

import java.time.Instant;
import org.drosa.pokemon.domain.dto.PokemonGraphqlDto;
import org.drosa.pokemon.domain.dto.PokemonV2Dto;
import org.springframework.stereotype.Component;

@Component
public class PokemonGrphqlDtoToPokemonV2Dto {

  public PokemonV2Dto map(PokemonGraphqlDto pokemonGraphqlDto) {
    return PokemonV2Dto.builder()
        .id(pokemonGraphqlDto.getId())
        .name(pokemonGraphqlDto.getName())
        .lastUpdatedAt(Instant.now())
        .build();
  }

}
