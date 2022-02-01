package com.trainingHarri.com.amrTraining.Services;

import com.trainingHarri.com.amrTraining.Model.User;
import com.trainingHarri.com.amrTraining.Repositries.userRepo;
import com.trainingHarri.com.amrTraining.exceptions.customExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;

import java.util.regex.Pattern;

@Service
public class userService {

    @Autowired
    userRepo userRep;

    public void registerUser(User c)  {
        String email = null;
        System.out.println("reeeched2");

        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if(c.getEmail() != null) {
            email = c.getEmail().toLowerCase();
        }
        if(!pattern.matcher(email).matches())
            throw new customExeption("Invalid email format");
        User count = userRep.findByEmail(email);
        if(count != null)
            throw new customExeption("Email already in use");
        System.out.println("hehehehe");
        String hashedPassword = BCrypt.hashpw(c.getPassword(), BCrypt.gensalt(10));
        c.setPassword(hashedPassword);
        userRep.save(c);
        long x= c.getUserid();

    }

    public User validateUser(String email, String password) throws customExeption {
        if(email != null)
            email = email.toLowerCase();
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
        User user= userRep.findByEmail(email);
        System.out.println(user.getEmail()+"\n"+user.getName());
        System.out.println(user.getPassword()+"\n"+hashedPassword);
        if (BCrypt.checkpw(password, user.getPassword()))
            return user;
        else return null;
    }
}
