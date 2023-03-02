package cz.upce.fei.cviceni01.service;

import cz.upce.fei.cviceni01.domain.AppUser;
import cz.upce.fei.cviceni01.dto.UserDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    AppUserService appUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return toUserDetailsDto(appUserService.findByUsername(username));
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    private static UserDetails toUserDetailsDto(AppUser appUser) {
        return new UserDetailsDto(
                appUser.getUsername(),
                appUser.getPassword(),
                new ArrayList<>()
        );
    }
}
