package com.onewingsoft.corestudio.repository;

import com.onewingsoft.corestudio.model.Hall;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by natete on 24/06/17.
 */
@Repository
public interface HallRepository extends PagingAndSortingRepository<Hall, Long> {
}
