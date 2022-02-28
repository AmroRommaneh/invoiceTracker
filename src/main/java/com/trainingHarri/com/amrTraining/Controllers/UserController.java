package com.trainingHarri.com.amrTraining.Controllers;

import com.trainingHarri.com.amrTraining.DTOs.userDto;
import com.trainingHarri.com.amrTraining.Model.Role;
import com.trainingHarri.com.amrTraining.Model.sUser;
import com.trainingHarri.com.amrTraining.Services.JwtUserDetailsService;
import com.trainingHarri.com.amrTraining.Services.userService;
import com.trainingHarri.com.amrTraining.config.JwtTokenUtil;
import com.trainingHarri.com.amrTraining.exceptions.UsedEmailExeption;
import com.trainingHarri.com.amrTraining.exceptions.UsedNameExeption;
import com.trainingHarri.com.amrTraining.exceptions.customExeption;
import com.trainingHarri.com.amrTraining.roleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("api/User")
public class UserController {
    @Autowired
    userService userSerivce;
    @Autowired
    com.trainingHarri.com.amrTraining.Repositries.userRepoPa userRepoPa;
    @Autowired
    com.trainingHarri.com.amrTraining.Repositries.roleRepo roleRepo;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping(path = "/registerUser")
    public ResponseEntity<String> registerUser(@RequestBody userDto userDto) {
        sUser user = new sUser();
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setEmail(userDto.getEmail());
        //   user.setRoles(userDto.getRoles());
        user.setGender(userDto.getGender());
        Role role = roleRepo.findbyname(roleName.ROLE_SUPPORT.toString());
        System.out.println("role id " + role.getId());
        try {
            userSerivce.registerUser(user);

        } catch (UsedNameExeption e) {
            System.out.println("sajdkjadkasdkaskdaspkd NAME IS NOT AVAILABLE");
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "NAME IS NOT AVAILABLE");

            return ResponseEntity.badRequest().headers(responseHeaders).body("NAME IS NOT AVAILABLE");
        } catch (UsedEmailExeption e) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "EMAIL IS NOT AVAILABLE");

            return ResponseEntity.badRequest().headers(responseHeaders).body("EMAIL IS NOT AVAILABLE");
        } catch (customExeption e) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("error", "WRONG EMAIL FORMAT");


            return ResponseEntity.badRequest().headers(responseHeaders).body("WRONG EMAIL FORMAT");
        }


        System.out.println("user id " + user.getUserid());

        userSerivce.saveUserRole(user.getUserid(), role.getId());

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getName());

        final String token = jwtTokenUtil.generateToken(userDetails);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("acces-token", token);

        return ResponseEntity.ok().headers(responseHeaders).body("account is created");
    }

    @PostMapping("/loginUser")
    public ResponseEntity<String> loginUser(@RequestBody Map<String, Object> clientMap) {
        String email = (String) clientMap.get("email");
        String password = (String) clientMap.get("password");
        sUser user = userSerivce.validateUser(email, password);
        System.out.println(email);
        System.out.println(password);
        if (user == null)
            throw new customExeption("No user exsit");

        return new ResponseEntity<>("loged in", HttpStatus.OK);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<Map<String, Object>> updateUser(@RequestBody userDto userDto) {
        Map<String, Object> y = userService.updateUser(userDto);
        return new ResponseEntity<>(y, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_SUPER')")
    @GetMapping("/getAllUsers")
    public ResponseEntity<Page<sUser>> findAllUsers(Pageable pageable) {
        return new ResponseEntity<>(userSerivce.findAll(pageable), HttpStatus.OK);
    }

}
