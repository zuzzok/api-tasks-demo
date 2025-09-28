package zuzzok.taskapp.exceptions;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import zuzzok.taskapp.payloads.ApiError;
import zuzzok.taskapp.payloads.FieldErrorDetail;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiError> handleNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
    return buildError(HttpStatus.NOT_FOUND, "Not Found", ex.getMessage(), request, null);
  }

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ApiError> handleBusinessException(BusinessException ex, HttpServletRequest request) {
    return buildError(HttpStatus.BAD_REQUEST, "Business Rule Violation", ex.getMessage(), request, null);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest request) {
    List<FieldErrorDetail> details = ex.getBindingResult().getFieldErrors().stream()
      .map(err -> FieldErrorDetail.builder().field(err.getField()).message(err.getDefaultMessage()).build())
      .toList();

    return buildError(HttpStatus.BAD_REQUEST, "Validation Error", "Validation failed for " + details.size() + " fields", request, details);
  }
  
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> handleGeneric(Exception ex, HttpServletRequest request) {
    return buildError(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex.getMessage(), request, null);
  }

  private ResponseEntity<ApiError> buildError(HttpStatus status, String error, String message, HttpServletRequest request, List<FieldErrorDetail> details) {
    ApiError apiError = ApiError.builder()
      .status(status.value())
      .error(error)
      .message(message)
      .timestamp(LocalDateTime.now())
      .path(request.getRequestURI())
      .errors(details)
      .build();

    return ResponseEntity.status(status).body(apiError);
  }

}
