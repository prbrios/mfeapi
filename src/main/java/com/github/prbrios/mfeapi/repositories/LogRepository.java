package com.github.prbrios.mfeapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.prbrios.mfeapi.entities.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Integer> {
    
}
