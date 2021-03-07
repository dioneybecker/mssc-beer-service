package com.dioneybecker.msscbeerservice.repositories;

import java.util.UUID;

import com.dioneybecker.msscbeerservice.domain.Beer;
import com.dioneybecker.brewery.model.BeerStyleEnum;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * BeerRepository
 */
public interface BeerRepository extends JpaRepository<Beer, UUID> {
   
}