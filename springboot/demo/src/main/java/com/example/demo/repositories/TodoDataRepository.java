package com.example.demo.repositories;

import java.util.Optional;

import com.example.demo.TodoData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@javax.transaction.Transactional
public interface TodoDataRepository extends JpaRepository<TodoData, Long> {

    public Optional<TodoData> findById(Long todo);
    
}
