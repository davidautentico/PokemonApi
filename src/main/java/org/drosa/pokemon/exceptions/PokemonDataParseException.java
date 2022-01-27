package org.drosa.pokemon.exceptions;

public class PokemonDataParseException extends RuntimeException {

  private static final String DEFAULT_MESSAGE = "Error parsing org.drosa.pokemon data";

  public PokemonDataParseException() {
    super(DEFAULT_MESSAGE);
  }

}
