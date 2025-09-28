package zuzzok.taskapp.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import zuzzok.taskapp.payloads.ApiResponse;
import zuzzok.taskapp.payloads.TaskRequest;
import zuzzok.taskapp.payloads.TaskResponse;
import zuzzok.taskapp.payloads.TaskUpdateRequest;
import zuzzok.taskapp.services.TaskService;
import zuzzok.taskapp.utils.ResponseHelper;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TaskController {

  private final TaskService taskService;
  
  @GetMapping("/tasks")
  public ResponseEntity<ApiResponse<List<TaskResponse>>> getAllTasks() {
    var tasks = taskService.getAllTasks().stream().map(TaskResponse::fromEntity).toList();
    return ResponseHelper.ok(tasks, "Tasks retrieved successfully");
  }

  @GetMapping("/tasks/{id}")
  public ResponseEntity<ApiResponse<TaskResponse>> getTaskById(@PathVariable Long id) {
    var task = taskService.getTaskById(id);
    return ResponseHelper.ok(TaskResponse.fromEntity(task), "Task retrieved successfully");
  }

  @PostMapping("/tasks")
  public ResponseEntity<ApiResponse<TaskResponse>> createTask(@RequestBody @Valid TaskRequest request) {
    var task = taskService.createTask(request);
    return ResponseHelper.created(TaskResponse.fromEntity(task), "Task created successfully");
  }
  
  @PutMapping("/tasks/{id}")
  public ResponseEntity<ApiResponse<TaskResponse>> updateTask(@PathVariable Long id, @RequestBody TaskUpdateRequest request) {
    var task = taskService.updateTask(id, request);
    return ResponseHelper.ok(TaskResponse.fromEntity(task), "Task updated successfully");
  }

  @DeleteMapping("/tasks/{id}")
  public ResponseEntity<ApiResponse<Void>> deleteTask(@PathVariable Long id) {
    taskService.deleteTask(id);
    return ResponseHelper.noContent("Task deleted successfully");
  }

}
