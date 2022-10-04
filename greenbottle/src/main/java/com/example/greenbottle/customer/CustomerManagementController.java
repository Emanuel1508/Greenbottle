
package com.example.greenbottle.customer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "management/api/v2/customer")
public class CustomerManagementController {

    private final CustomerService customerService;

    @Autowired
    public CustomerManagementController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('customer:register')")
    public void registerNewCustomer(@RequestBody Customer customer) throws MyException {
        customerService.registerNewCustomer(customer);
    }


    @DeleteMapping(path = "{customerId}")
    @PreAuthorize("hasAuthority('customer:delete')")
    public void deleteCustomer(@PathVariable("customerId") String customerId) throws MyException {
        customerService.deleteCustomer(customerId);
    }

    @PutMapping(path = "{customerId}")
    @PreAuthorize("hasAuthority('customer:register')")
    public void updateCustomer(@PathVariable("customerId") String customerId,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String phoneNumber
    ) throws MyException {

        //customerService.updateCustomer(customerId,name,phoneNumber);
    }
}
