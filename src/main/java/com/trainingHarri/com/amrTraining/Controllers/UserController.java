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

import java.util.ArrayList;
import java.util.List;
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
    @Autowired
    com.trainingHarri.com.amrTraining.Repositries.userRepo userRepo;

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
        try {
            sUser user = userSerivce.validateUser(email, password);
            return new ResponseEntity<>("loged in", HttpStatus.OK);

        }catch (customExeption e){
            return new ResponseEntity<>(e.getErrorMessage(), HttpStatus.BAD_REQUEST);

        }


    }

//    @PutMapping("/updateUser")
//    public ResponseEntity<Map<String, Object>> updateUser(@RequestBody userDto userDto) {
//        Map<String, Object> y = userService.updateUser(userDto);
//        return new ResponseEntity<>(y, HttpStatus.OK);
//    }

    @PreAuthorize("hasRole('ROLE_SUPER')")
    @GetMapping("/getAllUsers")
    public ResponseEntity<Page<sUser>> findAllUsers(Pageable pageable) {
        return new ResponseEntity<>(userSerivce.findAll(pageable), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_SUPER')")
    @DeleteMapping(path = "/deleteUser/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId,@RequestHeader String Authorization) {
        sUser x =userRepo.findByUserId(userId);
        String y= userSerivce.deleteUser(x,Authorization);
        return new ResponseEntity<>(y, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_SUPER')")
    @PutMapping(path = "/updateUSer")
    public ResponseEntity<String> updateUser(@RequestBody Map<String,Object> y) {
        userDto userDto =new userDto();
        userDto.setEmail(y.get("email").toString());
        userDto.setPhoneNumber(y.get("phoneNumber").toString());
        Role role = roleRepo.findbyname(y.get("Roles").toString());


        List<Role> x =new ArrayList<Role>();
        x.add(role);
        userDto.setRoles(x);
        Long convertedLong = Long.parseLong(y.get("userid").toString());
        userDto.setUserid(convertedLong);
        String z= userSerivce.update(userDto);
        System.out.println("<<<<<<>>>>>>>>>");
        System.out.println(y);
        System.out.println("<<<<<<>>>>>>>>>");

        System.out.println(userDto.getRoles());
        userSerivce.saveUserRole(userDto.getUserid(), role.getId());

        if(z.equals("User Has Been Updated"))
            return new ResponseEntity<>("User Has Been Updated", HttpStatus.OK);


      return null;
    }

}
