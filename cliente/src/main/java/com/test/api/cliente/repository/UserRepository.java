package com.test.api.cliente.repository;

import com.test.api.cliente.security.auth.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User,String> {

    UserDetails findByLogin(String login);

}
