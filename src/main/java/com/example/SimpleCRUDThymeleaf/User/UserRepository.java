package com.example.SimpleCRUDThymeleaf.User;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
/**
 * ! By extending the CrudRepository interface, Spring Data JPA will provide 
 * ! implementations for the repository’s CRUD methods for us
 */
public interface UserRepository extends CrudRepository<User,Long> {

    List<User> findByName(String name);
}
