package com.example.milkaapp.repositories;

import com.example.milkaapp.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findClientByEmail(String email);

    User findUserByEmail(String email);
}
