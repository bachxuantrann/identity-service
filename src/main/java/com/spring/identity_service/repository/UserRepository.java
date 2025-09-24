package com.spring.identity_service.repository;

import com.spring.identity_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String username);
    
    @Query("SELECT u FROM User u WHERE " +
           "(u.activationDate IS NULL OR u.activationDate <= :currentDate) AND " +
           "(u.expirationDate IS NULL OR u.expirationDate >= :currentDate)")
    List<User> getUsersForCreateOrg(@Param("currentDate") LocalDate currentDate);
}
