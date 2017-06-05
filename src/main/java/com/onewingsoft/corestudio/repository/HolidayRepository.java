package com.onewingsoft.corestudio.repository;

import com.onewingsoft.corestudio.model.Holiday;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author Ignacio González Bullón - <nacho.gonzalez.bullon@gmail.com>
 * @since 21/11/15.
 */
@Repository
public interface HolidayRepository extends PagingAndSortingRepository<Holiday, Long> {

    @Query("SELECT h FROM Holiday h WHERE year(h.date) = :year")
    Iterable<Holiday> findByYear(@Param("year") Integer year);

    @Query("SELECT h FROM Holiday h WHERE day(h.date) = :day AND month(h.date) = :month AND year(h.date) = :year")
    Holiday findByDate(@Param("day") Integer day, @Param("month") Integer month, @Param("year") Integer year);

    @Query("SELECT h FROM Holiday h WHERE h.date = :date")
    Holiday findByDate(@Param("date") Date date);
}
