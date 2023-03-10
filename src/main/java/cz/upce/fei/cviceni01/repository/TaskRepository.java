package cz.upce.fei.cviceni01.repository;

import cz.upce.fei.cviceni01.domain.Task;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {
    List<Task> findAllByAuthorId(final Long authorId);
}
