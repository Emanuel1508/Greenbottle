package com.example.greenbottle.customer;


import com.example.greenbottle.customerDto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import com.example.greenbottle.converter.CustomerConverter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v2/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerConverter customerConverter;

    @Autowired
    public CustomerController(CustomerService customerService, CustomerConverter customerConverter) {this.customerService = customerService;
        this.customerConverter = customerConverter;
    }



    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public List<Customer> getCustomers() {return customerService.getCustomers();}

    @PostMapping
    @PreAuthorize("hasAuthority('customer:register')")
    public void registerNewCustomer(@RequestBody Customer customer) throws MyException {customerService.registerNewCustomer(customer);}

    @DeleteMapping(path = "{customerId}")
    @PreAuthorize("hasAuthority('customer:delete')")
    public void deleteCustomer(@PathVariable("customerId") String customerId) throws MyException {
        customerService.deleteCustomer(customerId);
    }

    @PutMapping(path = "{customerId}")
    @PreAuthorize("hasAuthority('customer:update')")
    public void updateCustomer(@PathVariable("customerId") String customerId, @RequestBody CustomerDto customerDto) throws MyException {
        customerService.updateCustomer(customerId,customerDto);
    }

    @PostMapping(path = "{customerId}/sellBottle/{bottleId}")
    @PreAuthorize("hasAuthority('customer:sell')")
    public void sellBottle(@PathVariable("customerId") String customerId, @PathVariable("bottleId") String bottleId) throws MyException {
        customerService.sellBottle(bottleId,customerId);
    }

    @PostMapping(path = "{customerId}/purchaseBottle/{bottleId}")
    @PreAuthorize("hasAuthority('customer:buy')")
    public void buyBottle(@PathVariable("customerId") String customerId, @PathVariable("bottleId") String bottleId) throws MyException {
        customerService.buyBottle(bottleId,customerId);
    }
}
