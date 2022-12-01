package com.michaeladrummonds.aguafina.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.michaeladrummonds.aguafina.models.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    public List<UserRole> findByUserId(Integer userId);
}
