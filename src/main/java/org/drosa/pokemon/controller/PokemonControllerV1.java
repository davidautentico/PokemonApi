package org.drosa.pokemon.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.drosa.pokemon.domain.dto.PokemonListDTO;
import org.drosa.pokemon.service.PokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
public class PokemonControllerV1 {

  private final static String V1_PATH = "/v1/pokemons/actions";

  private final PokemonService pokemonService;

  @RequestMapping(
      method = RequestMethod.GET,
      value = V1_PATH + "/heaviest-pokemons/{number-pokemons}",
      produces = {"application/json", "application/problem+json"}
  )
  public ResponseEntity<PokemonListDTO> getHeaviestPokemons(@PathVariable("number-pokemons") Integer number) {

    log.info("Received GET for the <{}> heaviest pokemons", number);

    PokemonListDTO pokemonListDTO = PokemonListDTO.builder().pokemonList(pokemonService.getHeaviestPokemons(number)).build();

    log.info("Heaviest org.drosa.pokemon list <{}> obtained", pokemonListDTO.getPokemonList().size());

    return new ResponseEntity<>(pokemonListDTO, HttpStatus.OK);
  }

  @RequestMapping(
      method = RequestMethod.GET,
      value = V1_PATH + "/highest-pokemons/{number-pokemons}",
      produces = {"application/json", "application/problem+json"}
  )
  public ResponseEntity<PokemonListDTO> getHighestPokemons(@PathVariable("number-pokemons") Integer number) {

    log.info("Received GET for the <{}> highest pokemons", number);

    PokemonListDTO pokemonListDTO = PokemonListDTO.builder().pokemonList(pokemonService.getHighestPokemons(number)).build();

    log.info("Highest org.drosa.pokemon list <{}> obtained", pokemonListDTO.getPokemonList().size());

    return new ResponseEntity<>(pokemonListDTO, HttpStatus.OK);
  }

  @RequestMapping(
      method = RequestMethod.GET,
      value = V1_PATH + "/experience-pokemons/{number-pokemons}",
      produces = {"application/json", "application/problem+json"}
  )
  public ResponseEntity<PokemonListDTO> getMoreExperiencePokemons(@PathVariable("number-pokemons") Integer number) {
    log.info("Received GET for the <{}> more experience pokemons", number);

    PokemonListDTO pokemonListDTO = PokemonListDTO.builder().pokemonList(pokemonService.getMoreExperiencePokemons(number)).build();

    log.info("Highest org.drosa.pokemon list <{}> more experience pokemons", pokemonListDTO.getPokemonList().size());

    return new ResponseEntity<>(pokemonListDTO, HttpStatus.OK);
  }
}
