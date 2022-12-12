package com.michaeladrummonds.aguafina.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.michaeladrummonds.aguafina.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    public Role findRoleByName(String name);
}
