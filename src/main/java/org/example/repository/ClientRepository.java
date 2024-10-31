package org.example.repository;

import org.example.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Override
    Optional<Client> findById(Long aLong);
}
