package cz.upce.fei.cviceni01.repository;

import cz.upce.fei.cviceni01.domain.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
}
