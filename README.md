# PokemonApi
Rest endpoints to retrieve prokemon information from PokeAPI using its Graphql interface.

Features:
* Hazelcast cache
* java 11

# Endpoints
* http://localhost:8080/v1/pokemons/actions/highest-pokemons
* http://localhost:8080/v1/pokemons/actions/heaviest-pokemons
* http://localhost:8080/v1/pokemons/actions/experience-pokemons
* http://localhost:8080/v2/pokemons/attributes/{attribute}/{number}

# Examples
* Retrieve 5 highest pokemons : http://localhost:8080/v1/pokemons/actions/highest-pokemons/5
* Retrieve 5 heaviest pokemons : http://localhost:8080/v1/pokemons/actions/heaviest-pokemons/5
* Retrieve 5 with more experience pokemons : http://localhost:8080/v1/pokemons/actions/experience-pokemons/5

* Generic queries root/{attribute}/{number}
Example of getting 5 heaviest pokemons: http://localhost:8080/v2/pokemons/attributes/height/5

# Run standalone
1. gradlew build
2. java" -jar build/libs/PokemonApiApp-1.0.jar

# Run with docker
1. docker build --build-arg JAR_FILE=build/libs/PokemonApiApp-1.0.jar -t drosa/pokemon-api-docker .
2. docker run -p 8080:8080 drosa/pokemon-api-docker




