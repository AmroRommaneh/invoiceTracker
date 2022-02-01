package com.trainingHarri.com.amrTraining.Repositries;

import com.trainingHarri.com.amrTraining.Model.invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface invoiceRepo extends JpaRepository<invoice,Long> {
}
