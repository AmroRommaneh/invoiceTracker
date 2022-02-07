package com.trainingHarri.com.amrTraining.Repositries;

import com.trainingHarri.com.amrTraining.Model.invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

@Repository
public interface invoiceRepoPa extends PagingAndSortingRepository<invoice, Long> {
    Page<invoice> findAll (Pageable pageable);

}
