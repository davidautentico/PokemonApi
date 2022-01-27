package org.drosa.pokemon.domain.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class PokemonGraphqlDto implements Serializable {

  private int id;

  private String name;

  private int weight;

  private int height;

  private int base_experience;

}
