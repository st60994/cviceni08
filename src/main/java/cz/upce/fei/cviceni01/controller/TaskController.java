package cz.upce.fei.cviceni01.controller;

import cz.upce.fei.cviceni01.domain.Task;
import cz.upce.fei.cviceni01.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
