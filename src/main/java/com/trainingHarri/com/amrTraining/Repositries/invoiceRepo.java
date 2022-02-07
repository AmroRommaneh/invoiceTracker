package com.trainingHarri.com.amrTraining.Repositries;

import com.trainingHarri.com.amrTraining.Model.invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface invoiceRepo extends JpaRepository<invoice,Long> {

    @Query(value = "select * from invoice where external_invoice_id = :id",nativeQuery = true)
    invoice findByexid(@Param("id") long id);

    @Query(value = "select user_id from invoice where id = :id",nativeQuery = true)
    Long getuserId(@Param("id") long id);
}
