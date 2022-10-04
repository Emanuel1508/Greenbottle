package com.example.greenbottle.converter;

import com.example.greenbottle.customer.Customer;
import com.example.greenbottle.customerDto.CustomerDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class CustomerConverter {

    public void entityToDto(Customer customer,CustomerDto customerDto) {

        customerDto.setName(customer.getName());
        customerDto.setPhoneNumber(customer.getPhoneNumber());

    }

    public void dtoToEntity(CustomerDto customerDto,Customer customer) {

        customer.setName(customerDto.getName());
        customer.setPhoneNumber(customerDto.getPhoneNumber());

    }

}
