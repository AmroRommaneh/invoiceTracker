package com.trainingHarri.com.amrTraining.Repositries;

import com.trainingHarri.com.amrTraining.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface roleRepo extends JpaRepository<Role,Long> {

    @Query(value = "select * from role where role.role_name= :rolename",nativeQuery = true)
    Role findbyname(@Param("rolename") String rolename);
//
    @Query(value = "select role_name from role where role.id= :roleId",nativeQuery = true)
    String findbyid(@Param("roleId") long roleId);
}
