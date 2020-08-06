package com.dioneybecker.msscbeerservice.web.services;

import java.util.List;
import java.util.UUID;

import com.dioneybecker.msscbeerservice.web.models.BeerDto;


/**
 * BeerService
 */
public interface BeerService {

	BeerDto getBeerById(UUID beerId);

	BeerDto saveNewBeer(BeerDto beer);

	void updateBeer(UUID beerId, BeerDto beer);

	void deleteById(UUID beerId);

	List<BeerDto> getAll();
    
}