package com.dioneybecker.msscbeerservice.repositories;

import java.util.UUID;

import com.dioneybecker.msscbeerservice.domain.Beer;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * BeerRepository
 */
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
   
}