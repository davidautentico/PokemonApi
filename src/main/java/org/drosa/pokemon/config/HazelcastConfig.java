package org.drosa.pokemon.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class HazelcastConfig {

  public static final String HEAVIEST = "heaviest";
  public static final String HIGHEST = "highest";
  public static final String MORE_EXPERIENCE = "moreExperience";

  @Bean
  public HazelcastInstance hazelcastInstance() {
    Config config = new Config();

    MapConfig heaviestConfig = config.getMapConfig(HEAVIEST);
    heaviestConfig.setTimeToLiveSeconds(3600);

    MapConfig highestConfig = config.getMapConfig(HIGHEST);
    highestConfig.setTimeToLiveSeconds(3600);

    MapConfig moreExperienceConfig = config.getMapConfig(MORE_EXPERIENCE);
    moreExperienceConfig.setTimeToLiveSeconds(3600);

    config.addMapConfig(heaviestConfig);
    config.addMapConfig(highestConfig);
    config.addMapConfig(moreExperienceConfig);

    return Hazelcast.newHazelcastInstance(config);
  }

  @Bean
  public CacheManager cacheManager(HazelcastInstance hazelcastInstance) {
    return new HazelcastCacheManager(hazelcastInstance);
  }
}
