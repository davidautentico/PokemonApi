package org.drosa.pokemon.controller;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import io.restassured.http.ContentType;
import lombok.SneakyThrows;
import org.drosa.pokemon.PokemonDataConnectAdapter;
import org.drosa.pokemon.domain.dto.PokemonListDTO;
import org.drosa.pokemon.domain.dto.PokemonV2ListDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = PokemonDataConnectAdapter.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
class PokemonControllerV2IT {

  private static final String V2_POKEMONS_URL = "/v2/pokemons/attributes/{attribute}/{number-pokemons}";

  private static final String ATTRIBUTE_PATH_VARIABLE = "attribute";

  private static final String NUMBER_POKEMONS_PATH_VARIABLE = "number-pokemons";

  private static final String WEIGHT_ATTRIBUTE = "weight";

  private static final Integer POKEMONS_NUMBER = 5;

  @LocalServerPort
  private int serverPort;

  @SneakyThrows
  @Test
  void getHeaviestPokemons_whenValidNumber_shouldReturnExpectedResponse() {
    final var response = given().port(serverPort)
        .pathParam(NUMBER_POKEMONS_PATH_VARIABLE, POKEMONS_NUMBER)
        .pathParam(ATTRIBUTE_PATH_VARIABLE, WEIGHT_ATTRIBUTE)
        .contentType(ContentType.JSON)
        .when().log().all().get(V2_POKEMONS_URL).then().log().all().contentType(ContentType.JSON)
        .statusCode(HttpStatus.OK.value()).extract().as(PokemonV2ListDTO.class);

    // response check
    assertNotNull(response);
    assertEquals(response.getPokemonList().size(), 5);
  }
}