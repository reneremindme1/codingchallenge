package com.fazekas.coding_challenge.repository;

import com.fazekas.coding_challenge.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
