package cz.upce.fei.cviceni01.graphql;

import cz.upce.fei.cviceni01.domain.Task;
import cz.upce.fei.cviceni01.dto.AppUserDto;
import cz.upce.fei.cviceni01.dto.TaskResponseDtoV1;
import cz.upce.fei.cviceni01.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class TaskQLController {
    private final TaskService taskService;

    @SchemaMapping(typeName = "AppUser")
    public List<TaskResponseDtoV1> tasks(AppUserDto appUserDto) {
        return taskService.findAllByAuthorId(appUserDto.getId())
                .stream()
                .map(Task::toDto)
                .collect(Collectors.toList());
    }
}
