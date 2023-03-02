package cz.upce.fei.cviceni01.service;

import cz.upce.fei.cviceni01.domain.AppUser;
import cz.upce.fei.cviceni01.domain.Role;
import cz.upce.fei.cviceni01.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;

    @Transactional
    public AppUser findById(final Long id) throws ResourceNotFoundException {
        var result = appUserRepository.findById(id);
        if (result.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return result.get();
    }

    @Transactional
    public AppUser findByUsername(final String userName) throws ResourceNotFoundException {
        var result = appUserRepository.findAppUserByUsername(userName);
        if (result.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return result.get();
    }

    public List<AppUser> findAllByActiveEquals(boolean active) {
        return appUserRepository.findAllByActiveEquals(active);
    }

    public List<AppUser> findAllByRolesContaining(Role role) {
        return appUserRepository.findAllByRolesContaining(role);
    }

    public AppUser create(final AppUser entity) {
        return appUserRepository.save(entity);
    }

    @Transactional
    public AppUser update(final Long id, AppUser entity) throws ResourceNotFoundException {
        var userToUpdate = findById(id);
        userToUpdate.setUsername(entity.getUsername());
        userToUpdate.setPassword(entity.getPassword());
        userToUpdate.setActive(entity.getActive());
        userToUpdate.setUpdateDate(entity.getUpdateDate());
        userToUpdate.setCreationDate(entity.getCreationDate());
        return appUserRepository.save(userToUpdate);
    }

    @Transactional
    public void delete(final Long id) throws ResourceNotFoundException {
        var userToDelete = findById(id);
        userToDelete.setRoles(null);
        userToDelete.setTasks(null);
        appUserRepository.delete(userToDelete);
    }
}
