package org.drosa.pokemon.domain;

import lombok.Getter;

@Getter
public enum PokemonAttribute {
  HEIGHT("height"),
  WEIGHT("weight"),
  EXPERIENCE("base_experience");

  private final String attribute;

  PokemonAttribute(String attribute) {
    this.attribute = attribute;
  }
}
