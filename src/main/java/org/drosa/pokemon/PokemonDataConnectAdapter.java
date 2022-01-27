package org.drosa.pokemon;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@Slf4j
@EnableCaching
public class PokemonDataConnectAdapter {

  public static void main(String[] args) {

    SpringApplication.run(PokemonDataConnectAdapter.class, args);

    Runtime.getRuntime().addShutdownHook(new Thread(PokemonDataConnectAdapter::shutdown));

    log.info("***************** PokemonDataConnectAdapter Started *******************");
  }

  private static void shutdown() {
    log.info("***************** PokemonDataConnectAdapter Stopped *******************");
  }


}
