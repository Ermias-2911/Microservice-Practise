package com.dailycodebuffer.user.service;


import com.dailycodebuffer.user.entity.Customer;
import com.dailycodebuffer.user.exception.APIException;
import com.dailycodebuffer.user.exception.CustomerNotFoundException;
import com.dailycodebuffer.user.repository.CustomerRepository;
import com.dailycodebuffer.user.valueObject.Department;
import com.dailycodebuffer.user.valueObject.ResponseTemplateValueObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RestTemplate restTemplate;


    // Save customers
    public Customer saveCustomer(Customer customer){
        log.info("Inside saveUser of UserService");
        return customerRepository.save(customer);
    }

    // Get all added customers
    public List<Customer> getCustomers(){
        log.info("Inside getCustomer of UserService");
        List<Customer> customers = customerRepository.findAll();
            if (customers.isEmpty())
                throw new CustomerNotFoundException("Customers not added yet");
        return customerRepository.findAll();
    }


    // Get specific customer by id
    public Customer getCustomerById(Long id ) {
        Customer customer = customerRepository.findByCustomerId(id);
        if (customer == null)
            throw new CustomerNotFoundException("Customers not found because no customers added");
        return customerRepository.findByCustomerId(id);
    }

    // Delete specific customers
    public void deleteCusomers( Long id){
        log.info("Inside deleteCusomers of UserService");
        if(customerRepository.findById(id).isPresent())
                customerRepository.deleteById(id);
        else
            throw new CustomerNotFoundException("Can't find Customers ID");
    }

    // Delete all customers
    public void deletAllCustomers(){
        log.info("Inside deleteCusomers of UserService");
        if(!customerRepository.findAll().isEmpty())
            customerRepository.deleteAll();
        else
            throw new CustomerNotFoundException("No Available Customers found");
    }



    // To call Department microservie service and get department object and wrap it with user and retrun to caller
    public ResponseTemplateValueObject getCustomerWithDepartment(Long customerId) {
        log.info("Inside getUserWithDepartment of UserService");

        ResponseTemplateValueObject responseTemplateValueObject = new ResponseTemplateValueObject();
        Customer customer = customerRepository.findByCustomerId(customerId);
                             // Synchronous Call
        // To call another microservice from here we need to create Rest template object (Bean)
        // inside UserServiceApplication(main method class) and call it here.
        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/department/" + customer.getDepartmentId(), Department.class);

        responseTemplateValueObject.setDepartment(department);
        responseTemplateValueObject.setCustomer(customer);

        return responseTemplateValueObject;
    }
}
