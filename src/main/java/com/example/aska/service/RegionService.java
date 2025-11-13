package com.example.aska.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aska.model.Region;
import com.example.aska.repository.RegionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    public Region findById(Integer id) {
        return regionRepository.findById(id).orElseThrow();
    }

    public Region save(Region diciplinas) {
        return regionRepository.save(diciplinas);
    }

    public void deleteById(Integer id) {
        regionRepository.deleteById(id);
    }
}
