package org.drosa.pokemon.exceptions;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.drosa.pokemon.domain.dto.ProblemDetailsDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler {

  @ExceptionHandler(PokemonDataParseException.class)
  public ResponseEntity<Object> handlePokemonDataParseException(final PokemonDataParseException ex,
      final ServerHttpRequest request) {
    return getErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, request, ex, null);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleInternalServerError(final Exception ex, final ServerHttpRequest request) {
    return getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, request, ex, null);
  }

  private HttpHeaders getDefaultHttpHeaders() {
    final HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    return headers;
  }

  private void doLogErrorMessage(final ServerHttpRequest request, final Exception ex, final String message) {
    final StringBuilder messageBuilder = new StringBuilder();
    final Optional<String> path = Optional.ofNullable(request != null ? request.getPath().value() : null);
    path.ifPresent(value -> messageBuilder.append(String.format("Error in request with path: '%s': ", value)));
    if (message != null) {
      messageBuilder.append(String.format("Error message: '%s': ", message));
    }
    log.error(messageBuilder.toString(), ex);
  }

  @SuppressWarnings("SameParameterValue")
  private ResponseEntity<Object> getErrorResponse(final HttpStatus status,
      final ServerHttpRequest request,
      final Exception cause,
      final String message) {
    return getErrorResponse(status, getDefaultHttpHeaders(), request, cause, message);
  }

  private ResponseEntity<Object> getErrorResponse(final HttpStatus status,
      final HttpHeaders headers,
      final ServerHttpRequest request,
      final Exception cause,
      final String message) {
    doLogErrorMessage(request, cause, message);

    // Build and return response to caller
    final ProblemDetailsDto errorDTO = ProblemDetailsDto.builder()
        .type(request.getPath().value())
        .title(cause.getMessage())
        .build();
    return new ResponseEntity<>(errorDTO, headers, status);
  }
}