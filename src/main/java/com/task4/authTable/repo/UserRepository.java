package com.task4.authTable.repo;

import com.task4.authTable.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findFirstByEmailAndClientName(String email, String clientName );
    List <User> findUsersByClientName(String clientName);
}
