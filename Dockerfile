FROM adoptopenjdk/openjdk11:ubi
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build/libs/PokemonApiApp-1.0.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]