package org.drosa.pokemon.service;

import java.util.Collections;
import java.util.List;
import org.drosa.pokemon.domain.Pokemon;
import org.drosa.pokemon.domain.dto.PokemonV2Dto;

public interface PokemonService {

  default List<PokemonV2Dto> getOrderedPokemonsBasedOnAttribute(String attribute, Integer number) {
    return Collections.emptyList();
  }

  default List<Pokemon> getHighestPokemons(Integer number) {
    return Collections.emptyList();
  }

  default List<Pokemon> getHeaviestPokemons(Integer number) {
    return Collections.emptyList();
  }

  default List<Pokemon> getMoreExperiencePokemons(Integer number) {
    return Collections.emptyList();
  }
}
