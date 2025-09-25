package zuzzok.taskapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import zuzzok.taskapp.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
  
  public Task findByTitle(String title);

}
