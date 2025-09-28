package zuzzok.taskapp.utils;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import zuzzok.taskapp.payloads.ApiResponse;

public class ResponseHelper {
  
  public static <T> ResponseEntity<ApiResponse<T>> ok(T data, String message) {
    ApiResponse<T> response = ApiResponse.<T>builder()
      .success(true)
      .data(data)
      .timestamp(LocalDateTime.now())
      .message(message)
      .build();
    
    return ResponseEntity.ok(response);
  }

  public static <T> ResponseEntity<ApiResponse<T>> created(T data, String message) {
    ApiResponse<T> response = ApiResponse.<T>builder()
      .success(true)
      .data(data)
      .timestamp(LocalDateTime.now())
      .message(message)
      .build();
    
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  public static <T> ResponseEntity<ApiResponse<T>> noContent(String message) {
    ApiResponse<T> response = ApiResponse.<T>builder()
      .success(true)
      .data(null)
      .timestamp(LocalDateTime.now())
      .message(message)
      .build();
    return ResponseEntity.ok(response);
  }

}
