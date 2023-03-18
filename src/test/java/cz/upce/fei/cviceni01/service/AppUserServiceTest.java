package cz.upce.fei.cviceni01.service;

import cz.upce.fei.cviceni01.Example;
import cz.upce.fei.cviceni01.domain.AppUser;
import cz.upce.fei.cviceni01.repository.AppUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AppUserServiceTest {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AppUserRepository appUserRepository;


    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
        appUserRepository.deleteAll();
    }

    @Test
    @Transactional
    void findById() throws ResourceNotFoundException {
        AppUser expectedUser = appUserRepository.save(Example.EXISTING);
        AppUser actualUser = appUserService.findById(expectedUser.getId());

        assertEquals(expectedUser, actualUser);
    }

    @Test
    void findByUsername() {
    }

    @Test
    void findAllByActiveEquals() {
    }

    @Test
    void findAllByRolesContaining() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}