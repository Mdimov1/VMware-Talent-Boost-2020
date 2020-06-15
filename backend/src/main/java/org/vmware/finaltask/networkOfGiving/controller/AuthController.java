package org.vmware.finaltask.networkOfGiving.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.vmware.finaltask.networkOfGiving.model.AuthUserRequest;
import org.vmware.finaltask.networkOfGiving.util.JwtUtil;

@RestController
@CrossOrigin("*")
public class AuthController {

    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthUserRequest authUserRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authUserRequest.getUsername(), authUserRequest.getPassword()));
        }catch (Exception e){
            throw new Exception("Invalid username or password!");
        }

        return jwtUtil.generateToken(authUserRequest.getUsername());
    }
}
