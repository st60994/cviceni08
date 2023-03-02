package cz.upce.fei.cviceni01.controller;

import cz.upce.fei.cviceni01.dto.AppUserInputDto;
import cz.upce.fei.cviceni01.dto.AuthenticationRequest;
import cz.upce.fei.cviceni01.dto.AuthenticationResponse;
import cz.upce.fei.cviceni01.service.MyUserDetailsService;
import cz.upce.fei.cviceni01.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("")
public class MainController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity createAuthenticationRequest(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        final String jwt = jwtUtil.generateJwtToken(authentication);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

//    @PostMapping("")
//    public ResponseEntity login(@RequestBody @Validated AppUserInputDto appUserInputDto) {
//        var result = appUserService.create(toEntity(appUserInputDto));
//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .body(toDto(result));
//    }
}
