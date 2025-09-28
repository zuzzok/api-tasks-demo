package zuzzok.taskapp.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import zuzzok.taskapp.models.Task;
import zuzzok.taskapp.models.TaskStatus;
import zuzzok.taskapp.payloads.TaskRequest;
import zuzzok.taskapp.payloads.TaskUpdateRequest;
import zuzzok.taskapp.repositories.TaskRepository;

@Service
@RequiredArgsConstructor
public class TaskService {

  private final TaskRepository taskRepository;

  public List<Task> getAllTasks() {
    return taskRepository.findAll();
  }

  public Task getTaskById(Long id) {
    return taskRepository.findById(id).orElseThrow(
      () -> new RuntimeException("Task not found")
    ); // TODO: Reemplazar con excepción personalizada
  }

  @Transactional
  public Task createTask(TaskRequest request) {
    var task = Task.builder()
      .title(request.getTitle())
      .description(request.getDescription())
      .status(TaskStatus.PENDING)
      .build();

    return taskRepository.save(task);
  }

  @Transactional
  public Task updateTask(Long id, TaskUpdateRequest request) {
    var task = taskRepository.findById(id).orElseThrow(
      () -> new RuntimeException("Task not found")
    ); // TODO: Reemplazar con excepción personalizada

    task.setTitle(request.getTitle());
    task.setDescription(request.getDescription());
    task.setStatus(request.getStatus() != null ? request.getStatus() : task.getStatus());
    task.setUpdatedAt(LocalDateTime.now());
    
    return task;
  }

  @Transactional
  public void deleteTask(Long id) {
    var task = taskRepository.findById(id).orElseThrow(
      () -> new RuntimeException("Task not found with id " + id)
    ); // TODO: Reemplazar con excepción personalizada

    taskRepository.delete(task);
  }

}
