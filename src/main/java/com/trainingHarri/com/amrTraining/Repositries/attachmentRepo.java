package com.trainingHarri.com.amrTraining.Repositries;

import com.trainingHarri.com.amrTraining.Model.attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface attachmentRepo extends JpaRepository<attachment,Long> {

    attachment findById(long id);
    @Query(value = "select * from attachment where attachment.invoice_id= :inv ",nativeQuery = true)
    List<attachment> findAttachmentsByInvoiceID(@Param("inv") Long inv);
}
