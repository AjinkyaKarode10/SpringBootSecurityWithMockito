package com.test.hotelsearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.hotelsearch.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long>{

	Permission findByName(String permission);
}
