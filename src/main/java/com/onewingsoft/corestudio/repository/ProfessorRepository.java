package com.onewingsoft.corestudio.repository;

import com.onewingsoft.corestudio.model.Professor;
import com.onewingsoft.corestudio.model.RegisteredUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends PagingAndSortingRepository<Professor, Long> {

    @Query("SELECT p FROM Professor p WHERE p.registeredUser.username = :username")
    Professor findByUsername(String username);
}
