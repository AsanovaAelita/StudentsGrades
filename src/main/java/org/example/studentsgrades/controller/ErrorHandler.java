package org.example.studentsgrades.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

// Превращает ошибки в понятный ответ 400 вместо длинного текста ошибки
@RestControllerAdvice
public class ErrorHandler {

    // не прошла проверка аннотаций (@NotBlank, @Min, @Email и т.д.)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> validation(MethodArgumentNotValidException e) {
        Map<String, String> errors = new LinkedHashMap<>();
        e.getBindingResult().getFieldErrors()
                .forEach(f -> errors.put(f.getField(), f.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    // повтор уникального значения (email, название группы) или несуществующий id
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> integrity(DataIntegrityViolationException e) {
        return ResponseEntity.badRequest().body(Map.of("error",
                "Нарушено ограничение: такое значение уже есть или указан неверный id связанной записи"));
    }

    // связанная запись указана без id, например "group": {}
    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<Map<String, String>> badReference(InvalidDataAccessApiUsageException e) {
        return ResponseEntity.badRequest().body(Map.of("error",
                "Связанная запись указана неверно: передайте существующий id, например {\"id\": 1}"));
    }
}
