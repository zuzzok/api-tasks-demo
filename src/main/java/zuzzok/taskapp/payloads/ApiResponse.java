package zuzzok.taskapp.payloads;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ApiResponse<T> {

  private boolean success;
  private T data;
  private LocalDateTime timestamp;
  private String message;
  
}