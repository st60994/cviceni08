package cz.upce.fei.cviceni01.controller;

import cz.upce.fei.cviceni01.domain.AppUser;
import cz.upce.fei.cviceni01.domain.Role;
import cz.upce.fei.cviceni01.repository.AppUserRepository;
import cz.upce.fei.cviceni01.repository.RoleRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/app-user")
public class AppUserController {
    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;

    public AppUserController(AppUserRepository appUserRepository, RoleRepository roleRepository) {
        this.appUserRepository = appUserRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public List<AppUser> findAll() {
        return appUserRepository.findAllByActiveEquals(true);
    }

    @GetMapping("/role")
    public List<AppUser> findAllByRolesContaining(Role role) {
        return appUserRepository.findAllByRolesContaining(role);
    }

}
