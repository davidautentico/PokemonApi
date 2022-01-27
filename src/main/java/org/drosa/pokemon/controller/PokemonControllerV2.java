package org.drosa.pokemon.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.drosa.pokemon.domain.dto.PokemonV2ListDTO;
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
public class PokemonControllerV2 {

  private final static String V2_PATH = "/v2/pokemons/attributes";

  private final PokemonService pokemonService;

  @RequestMapping(
      method = RequestMethod.GET,
      value = V2_PATH + "/{attribute}/{number-pokemons}",
      produces = {"application/json", "application/problem+json"}
  )
  public ResponseEntity<PokemonV2ListDTO> getNumberOrderedPokemonsBasedOnAttribute(@PathVariable("attribute") String attribute,
      @PathVariable("number-pokemons") Integer number) {

    log.info("Received GET to return <{}> pokemons based on attribute <{}>", number, attribute);

    PokemonV2ListDTO pokemonV2ListDTO =
        PokemonV2ListDTO.builder().pokemonList(pokemonService.getOrderedPokemonsBasedOnAttribute(attribute, number)).build();

    log.info("Pokemon list obtained <{}>", pokemonV2ListDTO);

    return new ResponseEntity<>(pokemonV2ListDTO, HttpStatus.OK);
  }
}
