package com.trainingHarri.com.amrTraining.Repositries;

import com.trainingHarri.com.amrTraining.Model.item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface itemRepo extends JpaRepository<item,Long> {

    @Query(value = "select * from item ",nativeQuery = true)
    List<item> findAll ();

    @Query(value = "select * from item where item.id = :id",nativeQuery = true)
    item find(@Param("id") long id);

    @Query(value = "select name from item ",nativeQuery = true)
    List<String> findAllNames ();
}
