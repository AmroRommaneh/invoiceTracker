package com.trainingHarri.com.amrTraining.Repositries;

import com.trainingHarri.com.amrTraining.Model.item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface itemRepo extends JpaRepository<item,Long> {
}
