package org.drosa.pokemon.service;

import static org.junit.jupiter.api.Assertions.*;

import java.security.InvalidParameterException;
import java.util.List;
import org.drosa.pokemon.config.PokemonDataConnectProperties;
import org.drosa.pokemon.domain.Pokemon;
import org.drosa.pokemon.mappers.PokemonGraphqlDtoToPokemonMapper;
import org.drosa.pokemon.mappers.PokemonGrphqlDtoToPokemonV2Dto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
class PokemonServiceImplTest {

  @Mock
  private RestTemplate restTemplate;

  @Mock
  private PokemonGraphqlDtoToPokemonMapper pokemonGraphqlDtoToPokemonMapper;

  @Mock
  private PokemonGrphqlDtoToPokemonV2Dto pokemonGraphqlDtoToPokemonV2DtoMapper;

  @Mock
  private PokemonDataConnectProperties pokemonDataConnectProperties;

  @InjectMocks
  private PokemonServiceImpl pokemonService;

  @Test
  public void whenHeaviestAndNumberIsNull_shouldReturnInvalidParameterException(){

    final var ex = assertThrows(InvalidParameterException.class,
        () -> pokemonService.getHeaviestPokemons(null));
  }

}