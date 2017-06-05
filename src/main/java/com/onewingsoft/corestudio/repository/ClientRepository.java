package com.onewingsoft.corestudio.repository;

import com.onewingsoft.corestudio.model.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ignacio González Bullón - <nacho.gonzalez.bullon@gmail.com>
 */
@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {

    @Query("SELECT c FROM Client c WHERE c.registeredUser.username = :username")
    Client findByUsername(String username);

}
