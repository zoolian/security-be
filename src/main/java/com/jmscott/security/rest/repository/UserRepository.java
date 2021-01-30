package com.jmscott.security.rest.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.jmscott.security.rest.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>, QuerydslPredicateExecutor<User> {
    
	Optional<User> getByUsername(String username);
	User findByUsername(String username);
	Optional<User> findById(String id);
}
