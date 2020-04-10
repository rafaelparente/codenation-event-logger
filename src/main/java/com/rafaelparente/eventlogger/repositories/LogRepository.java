package com.rafaelparente.eventlogger.repositories;

import com.rafaelparente.eventlogger.models.Log;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends PagingAndSortingRepository<Log, Long> {



}
