package com.test.api.cliente.security;

import com.test.api.cliente.security.auth.user.User;
import com.test.api.cliente.security.auth.AuthenticationDTO;
import com.test.api.cliente.security.auth.RegisterDTO;
import com.test.api.cliente.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO authDto){
        var userPass = new UsernamePasswordAuthenticationToken(authDto.login(),authDto.password());
        var auth = manager.authenticate(userPass);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO){

        if(userRepository.findByLogin(registerDTO.login()) != null){
            return ResponseEntity.badRequest().build();
        }

        String hashPass = new BCryptPasswordEncoder().encode(registerDTO.password());

        User user = new User(registerDTO.login(),hashPass,registerDTO.role());

        userRepository.save(user);

        return ResponseEntity.ok(user);
    }

}
