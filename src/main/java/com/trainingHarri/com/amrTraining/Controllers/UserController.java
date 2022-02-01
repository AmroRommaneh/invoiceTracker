package com.trainingHarri.com.amrTraining.Controllers;

import com.trainingHarri.com.amrTraining.Constants;
import com.trainingHarri.com.amrTraining.DTOs.userDto;
import com.trainingHarri.com.amrTraining.Model.User;
import com.trainingHarri.com.amrTraining.Services.userService;
import com.trainingHarri.com.amrTraining.exceptions.customExeption;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("api/User")

public class UserController {
    @Autowired
    userService userSerivce;
    @PostMapping(path = "/registerUser")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody userDto userDto) {
        User user = new User();
System.out.println("reeeched1");
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setEmail(userDto.getEmail());
        user.setRoles(userDto.getRoles());
        user.setGender(userDto.getGender());



        userSerivce.registerUser(user);

        return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);
    }

    //function to generate tokens
    private Map<String, String> generateJWTToken(User user) {
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("email", user.getEmail())
                .claim("Name", user.getName())
                .compact();
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }

    @PostMapping("/loginUser")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, Object> clientMap) {
        String email = (String) clientMap.get("email");
        String password = (String) clientMap.get("password");
        User user = userSerivce.validateUser(email, password);
        System.out.println(email);
        System.out.println(password);
        if (user == null)
            throw new customExeption("No user exsit");

        return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);
    }

}
