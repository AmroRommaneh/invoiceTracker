package com.trainingHarri.com.amrTraining.Repositries;

import com.trainingHarri.com.amrTraining.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepo extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
