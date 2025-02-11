package com.mini.springcloud.eureka.client.auth.user;

import com.mini.springcloud.eureka.client.auth.AuthService;
import com.mini.springcloud.eureka.client.auth.dto.AuthResponse;
import com.mini.springcloud.eureka.client.auth.dto.SignInRequest;
import com.mini.springcloud.eureka.client.auth.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;

    @PostMapping("/auth/signIn")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody SignInRequest request){
        String token = authService.signIn(request.getUserId(), request.getPassword());

        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/auth/signUp")
    public ResponseEntity<?> signUp(@RequestBody User user){
        User createdUser = authService.signUp(user);

        return ResponseEntity.ok(createdUser);
    }
}
