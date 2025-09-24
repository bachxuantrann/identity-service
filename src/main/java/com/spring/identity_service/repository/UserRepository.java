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
    
    /**
     * Retrieves users whose accounts are currently in a valid activation period.
     * A user is considered valid if:
     * - activationDate is null OR activationDate <= currentDate
     * - expirationDate is null OR expirationDate >= currentDate
     * 
     * This ensures that only users with active accounts within their designated
     * activation period are returned for organization creation purposes.
     * 
     * @param currentDate the current date to compare against activation/expiration dates
     * @return list of users with valid activation periods
     */
    @Query("SELECT u FROM User u WHERE " +
           "(u.activationDate IS NULL OR u.activationDate <= :currentDate) AND " +
           "(u.expirationDate IS NULL OR u.expirationDate >= :currentDate)")
    List<User> getUsersForCreateOrg(@Param("currentDate") LocalDate currentDate);
}
