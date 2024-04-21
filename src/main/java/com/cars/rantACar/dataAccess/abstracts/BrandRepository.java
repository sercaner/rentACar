package com.cars.rantACar.dataAccess.abstracts;

import com.cars.rantACar.entities.concretes.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand,Integer> {
    boolean existsByName(String name); //spring jpa keywords
}
