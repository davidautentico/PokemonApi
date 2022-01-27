package org.drosa.pokemon.domain.dto;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.drosa.pokemon.domain.Pokemon;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PokemonListDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private List<Pokemon> pokemonList;

}
