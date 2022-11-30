package com.tester.crs.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tester.crs.Models.User;

public interface UserRepository extends JpaRepository<User, String>{
    User findAllByUsername(String username);
}
