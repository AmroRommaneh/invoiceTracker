package com.trainingHarri.com.amrTraining.Repositries;

import com.trainingHarri.com.amrTraining.Model.invoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface invoiceItemRepo extends JpaRepository<invoiceItem,Long> {

    //   @Query(value ="select item_id from invoice_item where invoice_id = invoiceId",nativeQuery = true)
    //   List<item> findItemByinvoiceid(long invoiceId);
    //
    @Query(value = "select *from invoice_item where invoice_item.invoice_id = :id", nativeQuery = true)
    List<invoiceItem> findd(@Param("id") Long id);

    //List <Long> getByInvoiceId(Long invoiceId);
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM invoice_item WHERE invoice_id = :invoiceId", nativeQuery = true)
    void deleteAll(@Param("invoiceId") Long invoiceId);

    @Query(value = "select  invoice_item.id,item_id,invoice_id,item.name,item.price,quantity from invoice_item INNER JOIN  item ON invoice_item.item_id=item.id where invoice_id = :invid", nativeQuery = true)
    List<invoiceItem> findDif(@Param("invid") Long invid);


}
//id,invoice_id,item_id,name,price