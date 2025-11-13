package com.example.aska.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aska.model.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {

}
