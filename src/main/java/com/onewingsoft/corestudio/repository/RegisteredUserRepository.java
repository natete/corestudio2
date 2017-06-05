package com.onewingsoft.corestudio.repository;

import com.onewingsoft.corestudio.model.RegisteredUser;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisteredUserRepository extends PagingAndSortingRepository<RegisteredUser, Long> {

    RegisteredUser findByUsername(String username);
}
