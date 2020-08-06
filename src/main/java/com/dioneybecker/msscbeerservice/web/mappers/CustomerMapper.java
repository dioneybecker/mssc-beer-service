package com.dioneybecker.msscbeerservice.web.mappers;

import com.dioneybecker.msscbeerservice.domain.Customer;
import com.dioneybecker.msscbeerservice.web.models.CustomerDto;

import org.mapstruct.Mapper;

/**
 * CustomerMapper
 */
@Mapper(uses = { DateMapper.class })
public interface CustomerMapper {
    CustomerDto customerToCustomerDto(Customer customer);

    Customer customerDtoToCustomer(CustomerDto customerDto);

}