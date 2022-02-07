package com.trainingHarri.com.amrTraining.Services;

import com.trainingHarri.com.amrTraining.Model.sUser;
import com.trainingHarri.com.amrTraining.Repositries.userRepo;
import com.trainingHarri.com.amrTraining.roleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    userRepo userRep;
    @Autowired
    com.trainingHarri.com.amrTraining.Repositries.roleRepo roleRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        sUser user =userRep.findByUserName(username);
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();

        System.out.println("user ua waaaard "+ user);


        Long role =userRep.findRolebyUserId(user.getUserid());
        String role1 =roleRepo.findbyid(role);

        if(role1.equals(roleName.ROLE_SUPPORT.toString()))
            authList.add(new SimpleGrantedAuthority(roleName.ROLE_SUPPORT.toString()));
        else
        if(role1.equals(roleName.ROLE_SUPER.toString()))
            authList.add(new SimpleGrantedAuthority(roleName.ROLE_SUPER.toString()));
        else
            authList.add(new SimpleGrantedAuthority(roleName.ROLE_AUDITOR.toString()));
//for (int i=0;i<user.getRoles().size();i++){
//
//       authList.add(new SimpleGrantedAuthority(user.getRoles().get(i).getRoleName().toString()));
//}

        if ((user.getName()).equals(username)) {
            return new User(username, user.getPassword(), authList);

        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

    }
}