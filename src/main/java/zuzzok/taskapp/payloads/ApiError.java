package zuzzok.taskapp.payloads;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ApiError {

  private int status;
  private String error;
  private String message;
  private LocalDateTime timestamp;
  private String path;
  private List<FieldErrorDetail> errors;

}