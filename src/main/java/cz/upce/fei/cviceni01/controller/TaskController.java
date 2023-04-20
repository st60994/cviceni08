package cz.upce.fei.cviceni01.controller;

import cz.upce.fei.cviceni01.domain.Task;
import cz.upce.fei.cviceni01.service.ResourceNotFoundException;
import cz.upce.fei.cviceni01.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/task")
@CrossOrigin
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public List<Task> findAll() {
        return taskService.findAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable final Long id) throws ResourceNotFoundException {
        var result = taskService.findById(id);

        return ResponseEntity.ok(result.toDto());
    }
}
