package cz.upce.fei.cviceni01.service;

import cz.upce.fei.cviceni01.domain.Task;
import cz.upce.fei.cviceni01.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Task> findAllByAuthorId(Long authorId) {
        return taskRepository.findAllByAuthorId(authorId);
    }

    @GetMapping("")
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) throws ResourceNotFoundException {
        var result = taskRepository.findById(id);

        if (result.isPresent()){
            throw new ResourceNotFoundException();
        }

        return result.get();
    }
}
