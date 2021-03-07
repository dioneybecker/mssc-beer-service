package com.dioneybecker.msscbeerservice.web.mappers;

import com.dioneybecker.msscbeerservice.domain.Beer;
import com.dioneybecker.brewery.model.BeerDto;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

/**
 * BeerMapper
 */
@Mapper(uses = { DateMapper.class })
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    BeerDto beerToBeerDtoWithInventory(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);
}