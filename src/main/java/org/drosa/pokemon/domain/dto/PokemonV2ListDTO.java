package org.drosa.pokemon.domain.dto;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PokemonV2ListDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private List<PokemonV2Dto> pokemonList;

}