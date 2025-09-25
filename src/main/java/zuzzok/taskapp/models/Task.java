package zuzzok.taskapp.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Size(max = 100, message = "Title cannot be longer than 100 characters")
  @NotBlank(message = "Title is mandatory")
  private String title;

  @Size(max = 500, message = "Description cannot be longer than 500 characters")
  private String description;

  @Enumerated(EnumType.STRING)
  private TaskStatus status;

  @Column(updatable = false)
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  @PrePersist
  protected void onCreate() {
    createdAt = LocalDateTime.now();
    updatedAt = createdAt;
  }

  @PreUpdate
  protected void onUpdate() {
    updatedAt = LocalDateTime.now();
  }

}
