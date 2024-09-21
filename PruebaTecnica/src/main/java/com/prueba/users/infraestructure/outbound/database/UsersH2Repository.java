package com.prueba.users.infraestructure.outbound.database;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prueba.users.infraestructure.outbound.database.entity.Users;


@Repository
public interface UsersH2Repository extends JpaRepository<Users, String> {

	@Query("SELECT u FROM Users u WHERE u.usr = :user AND u.password = :password")
    Optional<Users> login(
            @Param("user") String user, 
            @Param("password") String password);
}
