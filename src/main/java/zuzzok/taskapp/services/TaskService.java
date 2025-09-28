package zuzzok.taskapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import zuzzok.taskapp.models.Task;
import zuzzok.taskapp.models.TaskStatus;
import zuzzok.taskapp.payloads.TaskRequest;
import zuzzok.taskapp.repositories.TaskRepository;

@Service
@RequiredArgsConstructor
public class TaskService {

  private final TaskRepository taskRepository;

  public List<Task> getAllTasks() {
    return taskRepository.findAll();
  }

  public Task getTaskById(Long id) {
    return taskRepository.findById(id).orElse(null);
  }

  public Task createTask(TaskRequest request) {
    var task = Task.builder()
      .title(request.getTitle())
      .description(request.getDescription())
      .status(TaskStatus.PENDING)
      .build();
      
    return taskRepository.save(task);
  }

}
