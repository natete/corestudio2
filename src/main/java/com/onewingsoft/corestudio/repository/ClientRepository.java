package com.onewingsoft.corestudio.repository;

import com.onewingsoft.corestudio.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Ignacio González Bullón - <nacho.gonzalez.bullon@gmail.com>
 */
@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {

    @Query("SELECT c FROM Client c WHERE c.registeredUser.username = :username")
    Client findByUsername(@Param("username") String username);

    @Query("SELECT c FROM Client c WHERE CONCAT (c.name, ' ', c.firstSurname, ' ', c.secondSurname) LIKE %:name%")
    Page<Client> findByFullNameLike(@Param("name") String name, Pageable pageRequest);
}
