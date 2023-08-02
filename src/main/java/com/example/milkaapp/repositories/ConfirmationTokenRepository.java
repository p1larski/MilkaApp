package com.example.milkaapp.repositories;

import com.example.milkaapp.models.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface ConfirmationTokenRepository
        extends CrudRepository<ConfirmationToken, Long> {

    ConfirmationToken findByToken(String token);

}
