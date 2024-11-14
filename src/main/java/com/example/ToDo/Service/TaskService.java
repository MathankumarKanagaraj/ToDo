package com.example.ToDo.Service;

import com.example.ToDo.Entity.Task;
import com.example.ToDo.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task saveTask(final Task task) {
        return taskRepository.save(task);
    }

    public List<Task> retrieveTest() {
        return this.taskRepository.findAll();
    }

    public Task updateTask(final String id,final Task updatedTask) {
        final Optional<Task> OptionalTask = taskRepository.findById(id);
        if (OptionalTask.isPresent()) {
            Task task = OptionalTask.get();
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setStatus(updatedTask.getStatus());
            return taskRepository.save(task);

        } else {
            throw new RuntimeException("Task not found with id: " + id);
        }
    }

    public String deleteTask(final String id) {
        this.taskRepository.deleteById(id);
        return id;
    }

    public Page<Task> retrieveTasks(final Pageable pageable) {
        return this.taskRepository.findAll(pageable);
    }

}
