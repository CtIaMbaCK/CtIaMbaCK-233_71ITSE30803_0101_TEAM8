package com.vanlang.thuvien.repository;

import com.vanlang.thuvien.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
