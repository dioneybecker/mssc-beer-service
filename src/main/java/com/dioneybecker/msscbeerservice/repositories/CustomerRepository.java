package com.dioneybecker.msscbeerservice.repositories;

import java.util.UUID;

import com.dioneybecker.msscbeerservice.domain.Customer;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * CustomerRepository
 */
public interface CustomerRepository  extends PagingAndSortingRepository<Customer, UUID> {

    
}