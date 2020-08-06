package com.dioneybecker.msscbeerservice.web.mappers;

import com.dioneybecker.msscbeerservice.domain.Beer;
import com.dioneybecker.msscbeerservice.web.models.BeerDto;

import org.mapstruct.Mapper;

/**
 * BeerMapper
 */
@Mapper(uses = { DateMapper.class })
public interface BeerMapper {
    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);
}