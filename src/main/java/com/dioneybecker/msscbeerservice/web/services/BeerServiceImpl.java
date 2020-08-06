package com.dioneybecker.msscbeerservice.web.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.dioneybecker.msscbeerservice.domain.Beer;
import com.dioneybecker.msscbeerservice.repositories.BeerRepository;
import com.dioneybecker.msscbeerservice.web.mappers.BeerMapper;
import com.dioneybecker.msscbeerservice.web.models.BeerDto;
import com.dioneybecker.msscbeerservice.web.models.BeerStyleEnum;

import org.springframework.stereotype.Service;

/**
 * BeerServiceImpl
 */
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    public BeerServiceImpl(BeerRepository beerRepository, BeerMapper beerMapper) {
        this.beerRepository = beerRepository;
        this.beerMapper = beerMapper;
    }

    @Override
    public BeerDto getBeerById(UUID beerId) {
        Optional<Beer> beer = this.beerRepository.findById(beerId);

        if(beer.isPresent()){
            return this.beerMapper.beerToBeerDto(beer.get());
        } else {
            return null;
        }
        
    }

    @Override
    public List<BeerDto> getAll(){
        List<BeerDto> beerDtoList = new ArrayList<BeerDto>();
        this.beerRepository.findAll().forEach(beer -> {
            BeerDto beerDto = this.beerMapper.beerToBeerDto(beer);
            beerDtoList.add(beerDto);
        });

        return beerDtoList;
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        Beer newBeer = this.beerRepository.save(this.beerMapper.beerDtoToBeer(beerDto));
        return this.beerMapper.beerToBeerDto(newBeer);
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {
        // Beer updatedBeer = this.beerRepository.findById(beerId).get();
        Beer updatedBeer = this.beerMapper.beerDtoToBeer(beerDto);

        if (updatedBeer != null) {
            updatedBeer = this.beerRepository.save(updatedBeer);
        }
    }

    @Override
    public void deleteById(UUID beerId) {
        this.beerRepository.deleteById(beerId);
    }

}