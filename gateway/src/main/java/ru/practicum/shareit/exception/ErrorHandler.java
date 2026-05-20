package ru.practicum.shareit.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    /**
     * Обрабатывает нарушения @Valid на @RequestBody DTO.
     * Собирает все ошибки полей и возвращает первую человекочитаемую.
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleMethodArgumentNotValid(final MethodArgumentNotValidException e) {
        String details = e.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining("; "));
        log.warn("400 Validation failed: {}", details);
        return Map.of("error", details);
    }

    /**
     * Обрабатывает нарушения @Validated/@NotNull/@NotBlank на параметрах метода
     * (например, @RequestParam, @PathVariable).
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleConstraintViolation(final ConstraintViolationException e) {
        String details = e.getConstraintViolations()
                .stream()
                .map(cv -> cv.getPropertyPath() + ": " + cv.getMessage())
                .collect(Collectors.joining("; "));
        log.warn("400 Constraint violation: {}", details);
        return Map.of("error", details);
    }

    /**
     * Fallback: все необработанные исключения → 500.
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleUnexpected(final Exception e) {
        log.error("500 Unexpected error in gateway: {}", e.getMessage(), e);
        return Map.of("error", "Внутренняя ошибка шлюза: " + e.getMessage());
    }
}