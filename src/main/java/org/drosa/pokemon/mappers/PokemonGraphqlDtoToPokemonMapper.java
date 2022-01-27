package org.drosa.pokemon.mappers;

import java.time.Instant;
import org.drosa.pokemon.domain.Pokemon;
import org.drosa.pokemon.domain.dto.PokemonGraphqlDto;
import org.springframework.stereotype.Component;

@Component
public class PokemonGraphqlDtoToPokemonMapper {

  public Pokemon map(PokemonGraphqlDto pokemonGraphqlDto) {
    return Pokemon.builder()
        .id(pokemonGraphqlDto.getId())
        .name(pokemonGraphqlDto.getName())
        .baseExperience(pokemonGraphqlDto.getBase_experience())
        .height(pokemonGraphqlDto.getHeight())
        .weight(pokemonGraphqlDto.getWeight())
        .lastUpdatedAt(Instant.now())
        .build();
  }
}
