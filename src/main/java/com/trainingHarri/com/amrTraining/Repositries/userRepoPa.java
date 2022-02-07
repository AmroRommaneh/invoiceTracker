package com.trainingHarri.com.amrTraining.Repositries;

import com.trainingHarri.com.amrTraining.Model.sUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface userRepoPa extends PagingAndSortingRepository<sUser, Long> {
    Page <sUser> findAll (Pageable pageable);
}
