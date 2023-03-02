package cz.upce.fei.cviceni01.repository;

import cz.upce.fei.cviceni01.domain.AppUser;
import cz.upce.fei.cviceni01.domain.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends PagingAndSortingRepository<AppUser, Long> {
    List<AppUser> findAllByActiveEquals(Boolean active);

    Optional<AppUser> findAppUserByUsername(String username);

    @Query("""
                select app_user
                from AppUser app_user
                inner join app_user.roles r
                where r = :role
            """)
        // Using JPQL
    List<AppUser> findAllByRolesContaining(final Role role);
}
