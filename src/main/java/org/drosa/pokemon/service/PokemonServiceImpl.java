package org.drosa.pokemon.service;

import static org.drosa.pokemon.domain.PokeApiConstants.POKE_GRAPHQL_GENERIC_QUERY;
import static org.drosa.pokemon.domain.PokeApiConstants.POKE_GRAPHQL_QUERY;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.drosa.pokemon.config.HazelcastConfig;
import org.drosa.pokemon.config.PokemonDataConnectProperties;
import org.drosa.pokemon.domain.Pokemon;
import org.drosa.pokemon.domain.PokemonAttribute;
import org.drosa.pokemon.domain.dto.PokemonResponseData;
import org.drosa.pokemon.domain.dto.PokemonV2Dto;
import org.drosa.pokemon.exceptions.PokemonDataParseException;
import org.drosa.pokemon.mappers.PokemonGraphqlDtoToPokemonMapper;
import org.drosa.pokemon.mappers.PokemonGrphqlDtoToPokemonV2Dto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@AllArgsConstructor
public class PokemonServiceImpl implements PokemonService {

  private final RestTemplate restTemplate;

  private final PokemonGraphqlDtoToPokemonMapper pokemonGraphqlDtoToPokemonMapper;

  private final PokemonGrphqlDtoToPokemonV2Dto pokemonGraphqlDtoToPokemonV2DtoMapper;

  private final PokemonDataConnectProperties pokemonDataConnectProperties;

  @Cacheable(value = "#attribute")
  public List<PokemonV2Dto> getOrderedPokemonsBasedOnAttribute(final String attribute, final Integer number) {
    if (number == null || number <= 0 || attribute.isEmpty()) {
      throw new InvalidParameterException();
    }

    return getPokemonListFromRemoteApi(attribute, number);
  }

  @Cacheable(HazelcastConfig.HIGHEST)
  public List<Pokemon> getHighestPokemons(final Integer number) {

    if (number == null || number <= 0) {
      throw new InvalidParameterException();
    }

    return getPokemonListFromRemoteApi(PokemonAttribute.HEIGHT, number);
  }

  @Cacheable(HazelcastConfig.HEAVIEST)
  public List<Pokemon> getHeaviestPokemons(final Integer number) {

    if (number == null || number <= 0) {
      throw new InvalidParameterException();
    }

    return getPokemonListFromRemoteApi(PokemonAttribute.WEIGHT, number);
  }

  @Cacheable(HazelcastConfig.MORE_EXPERIENCE)
  public List<Pokemon> getMoreExperiencePokemons(Integer number) {

    if (number == null || number <= 0) {
      throw new InvalidParameterException();
    }

    return getPokemonListFromRemoteApi(PokemonAttribute.EXPERIENCE, number);
  }

  private List<PokemonV2Dto> getPokemonListFromRemoteApi(final String attribute, final int number) {
    HttpEntity<String> request = getGenericRequest(attribute, number);

    log.info("Api <{}>", pokemonDataConnectProperties.getPokeApiUrl());

    PokemonResponseData pokemonResponseData =
        restTemplate.postForObject(pokemonDataConnectProperties.getPokeApiUrl(), request, PokemonResponseData.class);

    if (pokemonResponseData == null || pokemonResponseData.getData() == null) {
      throw new PokemonDataParseException();
    }

    log.info("Pokemons for attribute <{}> obtained in a ist of <{}> pokemons", attribute,
        pokemonResponseData.getData().getPokemons().size());

    return pokemonResponseData.getData().getPokemons().stream().map(pokemonGraphqlDtoToPokemonV2DtoMapper::map)
        .collect(Collectors.toList());
  }

  private List<Pokemon> getPokemonListFromRemoteApi(PokemonAttribute pokemonAttribute, int number) {
    HttpEntity<String> request = getRequest(pokemonAttribute, number);

    log.info("Api <{}>", pokemonDataConnectProperties.getPokeApiUrl());

    PokemonResponseData pokemonResponseData =
        restTemplate.postForObject(pokemonDataConnectProperties.getPokeApiUrl(), request, PokemonResponseData.class);

    if (pokemonResponseData == null || pokemonResponseData.getData() == null) {
      throw new PokemonDataParseException();
    }

    log.info("Pokemons for attribute <{}> obtained in a ist of <{}> pokemons", pokemonAttribute.getAttribute(),
        pokemonResponseData.getData().getPokemons().size());

    return pokemonResponseData.getData().getPokemons().stream().map(pokemonGraphqlDtoToPokemonMapper::map).collect(Collectors.toList());
  }

  private HttpEntity<String> getGenericRequest(final String attribute, final int pokemonNumber) {
    String query = String.format(POKE_GRAPHQL_GENERIC_QUERY, attribute, pokemonNumber);

    log.info("Query for request <{},{}>: <{}>", attribute, pokemonNumber, query);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    return new HttpEntity<>(query, headers);
  }

  private HttpEntity<String> getRequest(final PokemonAttribute pokemonAttribute, final int pokemonNumber) {
    String query = String.format(POKE_GRAPHQL_QUERY, pokemonAttribute.getAttribute(), pokemonNumber);

    log.info("Query for request <{},{}>: <{}>", pokemonAttribute.getAttribute(), pokemonNumber, query);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    return new HttpEntity<>(query, headers);
  }

}
