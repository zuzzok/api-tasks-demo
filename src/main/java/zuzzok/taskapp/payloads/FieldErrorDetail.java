package zuzzok.taskapp.payloads;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FieldErrorDetail {

  private String field;
  private String message;
  
}
