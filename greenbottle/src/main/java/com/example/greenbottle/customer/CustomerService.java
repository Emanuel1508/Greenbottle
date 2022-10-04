package com.example.greenbottle.customer;


import com.example.greenbottle.bottle.BottleRepository;
import com.example.greenbottle.converter.CustomerConverter;
import com.example.greenbottle.customerDto.CustomerDto;
import com.example.greenbottle.exchange.ExchangeEntity;
import com.example.greenbottle.exchange.ExchangeEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ExchangeEntityRepository exchangeRepository;
    private final BottleRepository bottleRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository,
                           ExchangeEntityRepository exchangeRepository,
                           BottleRepository bottleRepository) {

        this.customerRepository = customerRepository;
        this.exchangeRepository = exchangeRepository;
        this.bottleRepository = bottleRepository;

    }

    @Autowired
    public CustomerConverter customerConverter;


    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public void registerNewCustomer(Customer customer) throws MyException {
        Optional<Customer> customerOptional = customerRepository.findCustomerByPhoneNumber(customer.getPhoneNumber());

        if (customerOptional.isPresent()) {
            throw new MyException("The customer is already registered");
        }
        if(!isPhoneNumberValid(customer.getPhoneNumber())) {
            throw new MyException("The customer: " + customer.getName() + " has an invalid phone number");
        }
        customer.setSellingLevel(1);
        customerRepository.save(customer);
    }

    public void deleteCustomer(String customerId) throws MyException {
        if(!customerRepository.findById(customerId).isPresent()) {
            throw new MyException("The customer with id: " + customerId + " is not registered");
        }
        customerRepository.deleteById(customerId);
    }

    public void updateCustomer(String customerId, CustomerDto customerDto) throws MyException {
        Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new MyException("Customer not registered"));


        if(!(customerDto.getName() != null && customerDto.getName().length() > 0)) {
            throw new MyException("Invalid request");
        }

        if(!isPhoneNumberValid(customerDto.getPhoneNumber())) {
            throw new MyException("Phone number is not  valid");
        }

        customerConverter.dtoToEntity(customerDto,customer);
        customerRepository.save(customer);

    }

    public void levelUp(String customerId) throws MyException {

        Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new MyException("Customer not registered"));
        if(customer.getExperience() >= customer.getSellingLevel()*50) {
            customer.setSellingLevel(customer.getSellingLevel() + 1);
            customer.setExperience(0);
            customerRepository.save(customer);
        }
    }

    public void sellBottle(String bottleId,String customerId) throws MyException {

        ExchangeEntity entity = new ExchangeEntity();
        entity.setBottle(bottleRepository.findById(bottleId).orElseThrow(()->new MyException("Bottle not found")));
        entity.setCustomerId(customerId);
        exchangeRepository.save(entity);
        Customer customer = customerRepository.findById(entity.getCustomerId()).orElseThrow(()->new MyException("Customer not registered"));

        if(customer.getBottleQuantity() <= 0) {
            throw new MyException("The customer: " + customer.getName() + " doesn't have enough bottles");
        }

        customer.setExperience(customer.getExperience() + entity.getBottle().getAmount()*10);
        customer.setBottleQuantity(customer.getBottleQuantity() - entity.getBottle().getAmount());
        customer.setBalance(customer.getBalance() + entity.getBottle().getPrice()/5*entity.getBottle().getAmount() + customer.getSellingLevel());

       try {
           levelUp(entity.getCustomerId());
       } finally {
           customerRepository.save(customer);
       }
    }

    public void buyBottle(String bottleId, String customerId) throws MyException {

        ExchangeEntity entity = new ExchangeEntity();
        entity.setBottle(bottleRepository.findById(bottleId).orElseThrow(()->new MyException("Bottle not found")));
        entity.setCustomerId(customerId);
        exchangeRepository.save(entity);

        Customer customer = customerRepository.findById(entity.getCustomerId()).orElseThrow(()->new MyException("Customer not registered"));

        if(customer.getBalance() < entity.getBottle().getAmount() * entity.getBottle().getPrice()) {
            throw new MyException("The customer: " + customer.getName() + " doesn't have enough money");
        }
        customer.setBalance(customer.getBalance() - entity.getBottle().getAmount() * entity.getBottle().getPrice());
        customer.setBottleQuantity(customer.getBottleQuantity() + entity.getBottle().getAmount());
        customerRepository.save(customer);

    }


    //method to check the validity of a phone number
    public boolean isPhoneNumberValid(String phoneNumber) {
        if(phoneNumber.length() != 10) {
            return false;
        }
        char[] prefix = phoneNumber.toCharArray();
        if(prefix[0] != '0' || prefix[1] !='7') {
            return false;
        }
        return true;
    }
}
