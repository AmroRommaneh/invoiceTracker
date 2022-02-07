package com.trainingHarri.com.amrTraining.Services;

import com.trainingHarri.com.amrTraining.DTOs.userDto;
import com.trainingHarri.com.amrTraining.Model.sUser;
import com.trainingHarri.com.amrTraining.Repositries.userRepo;
import com.trainingHarri.com.amrTraining.exceptions.customExeption;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class userService {
@Autowired
com.trainingHarri.com.amrTraining.Repositries.userRepoPa userRepoPa;
    @Autowired
    static
    userRepo userRep;
    @Autowired

    userRepo userRep2;
    public static Map<String,Object> updateUser(userDto userDto) {

        sUser user =userRep.findByEmail(userDto.getEmail());
        if (user ==null)
            throw new  ResponseStatusException(HttpStatus.NOT_FOUND,"THERE IS NO USER WITH THIS EMAIL ADDRESS");
        else {

            if(userDto.getPassword()!=null) {
                String hashedPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt(10));

                user.setPassword(hashedPassword);
            }
            if(userDto.getName()!=null)
                user.setName(userDto.getName());
            if(userDto.getPhoneNumber()!=null)
                user.setPhoneNumber(userDto.getPhoneNumber());
            if(userDto.getRoles()!=null) {
                for (int i=0;i<userDto.getRoles().size();i++)
                userRep.insertIntoUserRole(user.getUserid(), userDto.getRoles().get(i).getId());
            }

        }
        userRep.save(user);

        Map<String,Object>x =new HashMap<>();

        x.put("user id",user.getUserid());
        x.put("user email",user.getEmail());
        x.put("user phone number",user.getPhoneNumber());
        x.put("user name",user.getName());
        x.put("user gender",user.getGender());


        return x;



    }

    public void  registerUser(sUser c)  {
        String email = null;
        System.out.println("reeeched2");

        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if(c.getEmail() != null) {
            email = c.getEmail().toLowerCase();
        }
        System.out.println(email +"emmmmmmmmmmmmmm");

        if(!pattern.matcher(email).matches())
            throw new customExeption("Invalid email format");
        sUser count = userRep2.findByEmail(email);

        if(count != null)
            throw new customExeption("Email already in use");
        System.out.println("hehehehe");
        String hashedPassword = BCrypt.hashpw(c.getPassword(), BCrypt.gensalt(10));
        c.setPassword(hashedPassword);
        userRep2.save(c);
        long x= c.getUserid();

    }

    public sUser validateUser(String email, String password) throws customExeption {
        if(email != null)
            email = email.toLowerCase();
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
        sUser user= userRep2.findByEmail(email);
        System.out.println(user.getEmail()+"\n"+user.getName());
        System.out.println(user.getPassword()+"\n"+hashedPassword);
        if (BCrypt.checkpw(password, user.getPassword()))
            return user;
        else return null;
    }
public void saveUserRole(long userId,long roleId){
System.out.println("user id "+userId+"role id is"+roleId);
        userRep2.insertIntoUserRole(userId,roleId);
}

    public Page<sUser> findAll(Pageable page) {
        return   userRepoPa.findAll(page);

    }
}
