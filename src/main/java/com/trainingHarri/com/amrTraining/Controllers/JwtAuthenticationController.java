package com.trainingHarri.com.amrTraining.Controllers;


import com.trainingHarri.com.amrTraining.Model.JwtRequest;
import com.trainingHarri.com.amrTraining.Services.JwtUserDetailsService;
import com.trainingHarri.com.amrTraining.config.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
public class JwtAuthenticationController {

   @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<String> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        System.out.println(authenticationRequest.getUsername());
        System.out.println(authenticationRequest.getPassword());

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
              System.out.println("toooken from jwt contoleer" + token);

              HttpHeaders responseHeaders = new HttpHeaders();
            //  responseHeaders.add("acces-token",token);
            responseHeaders.set("acces-token",token);
              System.out.println(responseHeaders);

        return  ResponseEntity.ok().headers(responseHeaders).body("tmam");
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            System.out.println("\t 2222222ddddddddddd");
            System.out.println("\t 2222222ppppppppp");
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        System.out.println("\t ddddddddddd");
        System.out.println("\t ppppppppp");
    }
}