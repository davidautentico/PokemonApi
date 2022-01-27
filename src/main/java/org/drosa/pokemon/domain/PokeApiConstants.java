package org.drosa.pokemon.domain;

public class PokeApiConstants {

  public final static String POKE_GRAPHQL_GENERIC_QUERY =
      "{\"query\": \"{pokemons: pokemon_v2_pokemon(order_by: { %s : desc}, limit: %s, where: "
          + "{is_default: {_eq: true}}) {id,name}}\"}";

  public final static String POKE_GRAPHQL_QUERY =
      "{\"query\": \"{pokemons: pokemon_v2_pokemon(order_by: { %s : desc}, limit: %s, where: "
          + "{is_default: {_eq: true}}) {id,name,height,weight,base_experience}}\"}";
}
