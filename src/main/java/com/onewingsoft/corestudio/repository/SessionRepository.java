package com.onewingsoft.corestudio.repository;

import com.onewingsoft.corestudio.model.Session;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by natete on 24/06/17.
 */
@Repository
public interface SessionRepository extends PagingAndSortingRepository<Session, Long> {

    Iterable<Session> findByDateBetween(Date startDate, Date endDate);

}
