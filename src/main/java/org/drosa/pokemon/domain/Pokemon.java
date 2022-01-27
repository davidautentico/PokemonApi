package org.drosa.pokemon.domain;

import java.io.Serializable;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class Pokemon implements Serializable {

  private static final long serialVersionUID = 1L;

  private final int id;

  private final String name;

  private final int weight;

  private final int height;

  private final int baseExperience;

  private final Instant lastUpdatedAt;
}
