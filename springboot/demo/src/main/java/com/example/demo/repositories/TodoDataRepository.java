package com.example.demo.repositories;

import java.sql.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.demo.TodoData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface TodoDataRepository extends JpaRepository<TodoData, Long> {

    public Optional<TodoData> findById(Long id);
    public TodoData findByTodoAndKijitsu(String todo,Date date);    //使わない
    
}
