package com.github.prbrios.mfeapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.prbrios.mfeapi.entities.Config;

@Repository
public interface ConfigRepository extends JpaRepository<Config, Long> {
    
}
