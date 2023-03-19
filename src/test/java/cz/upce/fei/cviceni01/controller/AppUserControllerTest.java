package cz.upce.fei.cviceni01.controller;

import cz.upce.fei.cviceni01.Example;
import cz.upce.fei.cviceni01.domain.AppUser;
import cz.upce.fei.cviceni01.repository.AppUserRepository;
import cz.upce.fei.cviceni01.service.AppUserService;
import cz.upce.fei.cviceni01.util.JwtUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpClient;
import java.util.Date;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application.yml")
@AutoConfigureMockMvc
class AppUserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @LocalServerPort
    private int port;
    @Autowired
    private AppUserRepository appUserRepository;

    private String secret = JwtUtil.JWT_SECRET;

    private HttpHeaders headers = new HttpHeaders();

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
        appUserRepository.deleteAll();
    }

    @Test
    void findAppUser() throws Exception {
        String token = Jwts.builder()
                .setSubject("userName")
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
        headers = new HttpHeaders();
        headers.setBearerAuth(token);

        AppUser appUser = appUserRepository.save(Example.EXISTING);
        String url = "http://localhost:" + port + "/app-user/" + appUser.getId();
        mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void findAppUserNonExistant() throws Exception {
        String token = Jwts.builder()
                .setSubject("userName")
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
        headers = new HttpHeaders();
        headers.setBearerAuth(token);
        AppUser appUser = appUserRepository.save(Example.EXISTING);
        String url = "http://localhost:" + port + "/app-user/" + appUser.getId() + 1;
        mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    void findAllByRolesContaining() {
    }

    @Test
    void getAppUserJson() {
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