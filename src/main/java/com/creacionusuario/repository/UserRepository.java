package com.creacionusuario.repository;

import com.creacionusuario.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    User findOneByEmail(String email);
    Optional<User> findOneByIdAndIsActive(UUID id, Boolean active);
    Optional<List<User>> findAllByIsActive(Boolean active);
}
