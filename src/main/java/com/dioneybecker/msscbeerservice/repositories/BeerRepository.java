package com.dioneybecker.msscbeerservice.repositories;

import java.util.UUID;

import com.dioneybecker.msscbeerservice.domain.Beer;
import com.dioneybecker.msscbeerservice.web.models.BeerStyleEnum;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * BeerRepository
 */
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
    Page<Beer> findAllByBeerName(String beerName, Pageable pegeable);

    Page<Beer> findAllByBeerStyle(BeerStyleEnum beerStyle, Pageable pegeable);

    Page<Beer> findAllByBeerNameAndBeerStyle(String beerName,BeerStyleEnum beerStyle, Pageable pegeable);

    Beer findByUpc(String upc);
   
}