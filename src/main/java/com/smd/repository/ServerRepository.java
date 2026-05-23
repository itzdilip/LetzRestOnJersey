package com.smd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smd.model.Server;

@Repository
public interface ServerRepository extends JpaRepository<Server, String> {
}
