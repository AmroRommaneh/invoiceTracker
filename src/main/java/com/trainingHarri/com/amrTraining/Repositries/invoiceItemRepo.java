package com.trainingHarri.com.amrTraining.Repositries;

import com.trainingHarri.com.amrTraining.Model.invoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface invoiceItemRepo extends JpaRepository<invoiceItem,Long> {

 //   @Query(value ="select item_id from invoice_item where invoice_id = invoiceId",nativeQuery = true)
 //   List<item> findItemByinvoiceid(long invoiceId);
 //
    @Query(value ="select item_id from invoice_item where invoice_item.invoice_id = :id",nativeQuery = true)
    List<Long> findd(@Param("id") Long id);
    //List <Long> getByInvoiceId(Long invoiceId);
}
