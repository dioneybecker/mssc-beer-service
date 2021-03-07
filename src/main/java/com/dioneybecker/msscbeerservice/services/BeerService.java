package com.dioneybecker.msscbeerservice.services;

import java.util.List;
import java.util.UUID;

import com.dioneybecker.brewery.model.BeerDto;
import com.dioneybecker.brewery.model.BeerPagedList;
import com.dioneybecker.brewery.model.BeerStyleEnum;

import org.springframework.data.domain.PageRequest;


/**
 * BeerService
 */
public interface BeerService {

	BeerDto getBeerById(UUID beerId, boolean showInventoryOnHand);

	BeerDto saveNewBeer(BeerDto beer);

	BeerDto updateBeer(UUID beerId, BeerDto beer);

	void deleteById(UUID beerId);

	List<BeerDto> getAll();

	BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest of, Boolean showInventoryOnHand);

	BeerDto getByUpc(String upc, boolean showInventoryOnHand);
    
}