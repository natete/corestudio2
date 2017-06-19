package com.onewingsoft.corestudio.repository;

import com.onewingsoft.corestudio.model.Professor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends PagingAndSortingRepository<Professor, Long> {

    @Query("SELECT p FROM Professor p WHERE p.registeredUser.username = :username")
    Professor findByUsername(@Param("username") String username);
}
