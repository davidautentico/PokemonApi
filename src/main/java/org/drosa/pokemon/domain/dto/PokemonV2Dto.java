package org.drosa.pokemon.domain.dto;

import java.io.Serializable;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class PokemonV2Dto implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int id;

    private final String name;

    private final Instant lastUpdatedAt;
  }
