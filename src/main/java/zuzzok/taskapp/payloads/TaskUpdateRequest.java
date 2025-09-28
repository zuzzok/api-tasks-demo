package zuzzok.taskapp.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import zuzzok.taskapp.models.TaskStatus;

@Data
@Builder
public class TaskUpdateRequest {
  
  @Size(max = 100, message = "Title cannot be longer than 100 characters")
  @NotBlank(message = "Title is required")
  private String title;

  @Size(max = 500, message = "Description cannot be longer than 500 characters")
  private String description;

  private TaskStatus status;

}
