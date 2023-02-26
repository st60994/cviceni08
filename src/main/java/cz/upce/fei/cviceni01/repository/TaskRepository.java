package cz.upce.fei.cviceni01.repository;

import cz.upce.fei.cviceni01.domain.Task;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {
}
