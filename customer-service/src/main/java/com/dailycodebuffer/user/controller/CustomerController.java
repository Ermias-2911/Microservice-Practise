package com.dailycodebuffer.user.controller;


import com.dailycodebuffer.user.entity.Customer;
import com.dailycodebuffer.user.service.CustomerService;
import com.dailycodebuffer.user.valueObject.ResponseTemplateValueObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer")
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/")
    public Customer saveCustom(@Valid @RequestBody Customer customer){
        log.info("Inside saveUser of UserController");
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/getCustomer/{id}")
    public Customer findCustomerById(@PathVariable("id") Long id){
        log.info("Inside findCustomer by Id of UserController");
        return customerService.getCustomerById(id);
    }


    @GetMapping("/getCustomers")
    public List<Customer> findCustomers(){
        log.info("Inside findCustomer of UserController");
        return customerService.getCustomers();
    }

    @GetMapping("/{id}")
    public ResponseTemplateValueObject getCustomerWithDepartment(@PathVariable("id") Long customerId){
        log.info("Inside ResponseTemplateValueObject of UserController");
        return customerService.getCustomerWithDepartment(customerId);
    }


    @DeleteMapping("/{id}")
    public void deleteCusomers(@PathVariable("id") Long customerId){
        log.info("Inside deleteCusomers of UserController");
        customerService.deleteCusomers(customerId);
    }

    @DeleteMapping("/")
    public void deleteCusomers(){
        log.info("Inside deleteCusomers of UserController");
        customerService.deletAllCustomers();
    }

}
