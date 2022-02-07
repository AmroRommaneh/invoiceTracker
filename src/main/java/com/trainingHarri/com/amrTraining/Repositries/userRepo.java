package com.trainingHarri.com.amrTraining.Repositries;

import com.trainingHarri.com.amrTraining.Model.sUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Map;

@Repository
public interface userRepo extends JpaRepository<sUser,Long> {
    @Query(value ="select * from user where user.email= :email",nativeQuery = true)
    sUser findByEmail(String email);

    @Modifying
    @Transactional
    @Query(value ="INSERT INTO user_role (user_id, role_id) VALUES (:userId,:roleId)",nativeQuery = true)
    void insertIntoUserRole(@Param("userId") Long userId,@Param("roleId") Long roleId);





    @Query(value = "select * from user_role",nativeQuery = true)
    Map<Long,Long> getAllUsersAndRoles ();

    @Query(value = "select * from user_role where user_id= :id",nativeQuery = true)
    Map<Long,Long> getRolebyUserId(@Param("id") Long userId);

//    @Query(value = "select * from user INNER JOIN user_role ON user.id = user_role.user_id where name= :username ",nativeQuery = true)
//    sUser findByUserName(@Param("username") String username);
    @Query(value = "select * from user where name= :username ",nativeQuery = true)
    sUser findByUserName(@Param("username") String username);

        @Query(value = "select role_id from user_role where user_role.user_id= :userId",nativeQuery = true)
        Long findRolebyUserId(@Param("userId") long userId);

        @Query(value = "select * from user where name = :userName",nativeQuery = true)
        sUser findUserIdByUsername(@Param("userName") String userName);
}
