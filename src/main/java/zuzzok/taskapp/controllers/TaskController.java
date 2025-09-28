package zuzzok.taskapp.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import zuzzok.taskapp.payloads.TaskResponse;
import zuzzok.taskapp.services.TaskService;



@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TaskController {

  private final TaskService taskService;
  
  @GetMapping("/tasks")
  public ResponseEntity<List<TaskResponse>> getAllTasks() {
    return ResponseEntity.ok(
      taskService.getAllTasks()
        .stream()
        .map(TaskResponse::fromEntity)
        .toList()
    );
  }

  @GetMapping("/tasks/{id}")
  public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id) {
    var task = taskService.getTaskById(id);
    if (task == null) {
      return ResponseEntity.notFound().build(); // TODO: Reemplazar con excepción personalizada
    }
    return ResponseEntity.ok(TaskResponse.fromEntity(task));
  }

}
