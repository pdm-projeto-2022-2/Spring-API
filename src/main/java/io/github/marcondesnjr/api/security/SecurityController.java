package io.github.marcondesnjr.api.security;

import io.github.marcondesnjr.api.security.dao.AuthDAO;
import io.github.marcondesnjr.api.security.dao.TokenDao;
import io.github.marcondesnjr.api.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SecurityController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> authentication(@RequestBody AuthDAO authDAO){
        var token = authService.auth(authDAO);
        return ResponseEntity.ok(token);
    }

}
