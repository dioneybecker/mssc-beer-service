package com.dioneybecker.msscbeerservice.repositories;

import java.util.UUID;

import com.dioneybecker.brewery.model.BeerStyleEnum;
import com.dioneybecker.msscbeerservice.domain.Beer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * BeerRepository
 */
public interface BeerRepository extends JpaRepository<Beer, UUID> {
    Page<Beer> findAllByBeerName(String beerName, Pageable pegeable);

    Page<Beer> findAllByBeerStyle(BeerStyleEnum beerStyle, Pageable pegeable);

    Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyleEnum beerStyle, Pageable pegeable);

    Beer findByUpc(String upc);
   
}