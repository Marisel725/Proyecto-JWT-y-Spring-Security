package com.example.api_gestion_facturas.repository;

import com.example.api_gestion_facturas.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail (@Param(("email")) String email);
}
