package com.example.aska.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aska.model.Comuna;
import com.example.aska.model.Region;

@Repository
public interface ComunaRepository extends JpaRepository<Comuna, Integer> {

    void deleteByIdRegion(Region region);

}
