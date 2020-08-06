package com.dioneybecker.msscbeerservice.web.services;

import java.util.UUID;

import com.dioneybecker.msscbeerservice.domain.Customer;
import com.dioneybecker.msscbeerservice.repositories.CustomerRepository;
import com.dioneybecker.msscbeerservice.web.mappers.CustomerMapper;
import com.dioneybecker.msscbeerservice.web.models.CustomerDto;

import org.springframework.stereotype.Service;

/**
 * CustomerServiceImpl
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDto getCustomerById(UUID customerId) {
        return this.customerMapper.customerToCustomerDto(this.customerRepository.findById(customerId).get());
    }

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Customer newCustomer = this.customerRepository.save(this.customerMapper.customerDtoToCustomer(customerDto));
        return this.customerMapper.customerToCustomerDto(newCustomer);
    }

    @Override
    public void updateCustomerById(UUID cusomerId, CustomerDto customerDto) {
        // Beer updatedBeer = this.beerRepository.findById(beerId).get();
        Customer updatedCustomer = this.customerMapper.customerDtoToCustomer(customerDto);

        if (updatedCustomer != null) {
            updatedCustomer = this.customerRepository.save(updatedCustomer);
        }
    }

    @Override
    public void deleteCustomerById(UUID customerId) {
        this.customerRepository.deleteById(customerId);
    }

}