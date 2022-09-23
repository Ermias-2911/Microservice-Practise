package com.dailycodebuffer.user.controller;


import com.dailycodebuffer.user.entity.Customer;
import com.dailycodebuffer.user.service.CustomerService;
import com.dailycodebuffer.user.valueObject.ResponseTemplateValueObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/")
    public Customer saveCustomer(@RequestBody Customer customer){
        log.info("Inside saveUser of UserController");
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/{id}")
    public ResponseTemplateValueObject getCustomerWithDepartment(@PathVariable("id") Long customerId){
        log.info("Inside ResponseTemplateValueObject of UserController");
        return customerService.getCustomerWithDepartment(customerId);
    }

}
