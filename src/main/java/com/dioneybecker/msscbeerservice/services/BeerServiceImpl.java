package com.dioneybecker.msscbeerservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.dioneybecker.msscbeerservice.domain.Beer;
import com.dioneybecker.msscbeerservice.repositories.BeerRepository;
import com.dioneybecker.msscbeerservice.web.controllers.NotFoundException;
import com.dioneybecker.msscbeerservice.web.mappers.BeerMapper;
import com.dioneybecker.brewery.model.BeerDto;
import com.dioneybecker.brewery.model.BeerPagedList;
import com.dioneybecker.brewery.model.BeerStyleEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.RequiredArgsConstructor;

/**
 * BeerServiceImpl
 */
@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    @Autowired
    BeerRepository beerRepository;

    @Autowired
    BeerMapper beerMapper;

    @Cacheable(cacheNames = "beerCache", key = "#beerId", condition = "#showInventoryOnHand == false ")
    @Override
    public BeerDto getBeerById(UUID beerId, boolean showInventoryOnHand) {
        if (showInventoryOnHand) {
            return beerMapper.beerToBeerDtoWithInventory(
                    beerRepository.findById(beerId).orElseThrow(NotFoundException::new)
            );
        } else {
            return beerMapper.beerToBeerDto(
                    beerRepository.findById(beerId).orElseThrow(NotFoundException::new)
            );
        }
    }

    @Override
    public List<BeerDto> getAll() {
        List<BeerDto> beerDtoList = new ArrayList<BeerDto>();
        this.beerRepository.findAll().forEach(beer -> {
            BeerDto beerDto = this.beerMapper.beerToBeerDto(beer);
            beerDtoList.add(beerDto);
        });

        return beerDtoList;
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return this.beerMapper.beerToBeerDto(this.beerRepository.save(this.beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        Beer beer = this.beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return beerMapper.beerToBeerDto(beerRepository.save(beer));
    }

    @Override
    public void deleteById(UUID beerId) {
        this.beerRepository.deleteById(beerId);
    }

    @Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOnHand == false ")
    @Override
    public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest,
            Boolean showInventoryOnHand) {
                BeerPagedList beerPagedList;
                Page<Beer> beerPage;

                if (!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
                    //search both
                    beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
                } else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
                    //search beer_service name
                    beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
                } else if (StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
                    //search beer_service style
                    beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
                } else {
                    beerPage = beerRepository.findAll(pageRequest);
                }
        
                if (showInventoryOnHand){
                    beerPagedList = new BeerPagedList(beerPage
                            .getContent()
                            .stream()
                            .map(beerMapper::beerToBeerDtoWithInventory)
                            .collect(Collectors.toList()),
                            PageRequest
                                    .of(beerPage.getPageable().getPageNumber(),
                                            beerPage.getPageable().getPageSize()),
                            beerPage.getTotalElements());
                } else {
                    beerPagedList = new BeerPagedList(beerPage
                            .getContent()
                            .stream()
                            .map(beerMapper::beerToBeerDto)
                            .collect(Collectors.toList()),
                            PageRequest
                                    .of(beerPage.getPageable().getPageNumber(),
                                            beerPage.getPageable().getPageSize()),
                            beerPage.getTotalElements());
                }
        
                return beerPagedList;
    }

   // @Cacheable(cacheNames = "beerUpcCache", condition = "#showInventoryOnHand == false ")
    @Override
    public BeerDto getByUpc(String upc, boolean showInventoryOnHand) {
        if(showInventoryOnHand){
            return beerMapper.beerToBeerDtoWithInventory(beerRepository.findByUpc(upc));
        } else {
            return beerMapper.beerToBeerDto(beerRepository.findByUpc(upc));
        }
        
    }

}