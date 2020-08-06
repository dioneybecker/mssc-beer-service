package com.dioneybecker.msscbeerservice.web.services;

import java.util.UUID;

import com.dioneybecker.msscbeerservice.web.models.CustomerDto;

/**
 * CustomerService
 */
public interface CustomerService {

    CustomerDto getCustomerById(UUID customerId);

    CustomerDto saveCustomer(CustomerDto customer);

    void updateCustomerById(UUID cusomerId, CustomerDto customer);

    void deleteCustomerById(UUID customerId);
}