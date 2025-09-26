package zuzzok.taskapp.payloads;

import lombok.Builder;
import lombok.Value;
import zuzzok.taskapp.models.Task;
import zuzzok.taskapp.models.TaskStatus;

@Value
@Builder
public class TaskResponse {
  
  private Long id;
  private String title;
  private String description;
  private TaskStatus status;
  private String createdAt;
  private String updatedAt;

  public static TaskResponse fromEntity(Task task) {
    return TaskResponse.builder()
      .id(task.getId())
      .title(task.getTitle())
      .description(task.getDescription())
      .status(task.getStatus())
      .createdAt(task.getCreatedAt().toString())
      .updatedAt(task.getUpdatedAt().toString())
      .build();
  }

}
