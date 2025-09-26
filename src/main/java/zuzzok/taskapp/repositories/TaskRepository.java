package zuzzok.taskapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import zuzzok.taskapp.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
  
  public Optional<Task> findByTitle(String title);

}
