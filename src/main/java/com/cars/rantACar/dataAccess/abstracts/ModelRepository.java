package com.cars.rantACar.dataAccess.abstracts;

import com.cars.rantACar.entities.concretes.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model,Integer> {
}
