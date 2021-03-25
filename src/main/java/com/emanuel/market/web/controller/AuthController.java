package com.emanuel.market.web.controller;

import com.emanuel.market.domain.dto.AuthenticationRequest;
import com.emanuel.market.domain.dto.AuthenticationResponse;
import com.emanuel.market.domain.service.EmanuelUserDetailsService;
import com.emanuel.market.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmanuelUserDetailsService emanuelUserDetailsService;

    @Autowired
    private JWTUtil jwtUntil;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest request){
        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetails userDetails = emanuelUserDetailsService.loadUserByUsername(request.getUsername());
            String jwt = jwtUntil.generateToken(userDetails);

            return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
        }catch (BadCredentialsException e) {
            return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }
}
