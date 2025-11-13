package com.example.aska.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aska.model.Diciplina;

@Repository
public interface DiciplinaRepository extends JpaRepository<Diciplina, Integer> {

}
