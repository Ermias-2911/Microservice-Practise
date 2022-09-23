package com.dailycodebuffer.user.service;


import com.dailycodebuffer.user.entity.Customer;
import com.dailycodebuffer.user.repository.CustomerRepository;
import com.dailycodebuffer.user.valueObject.Department;
import com.dailycodebuffer.user.valueObject.ResponseTemplateValueObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RestTemplate restTemplate;

    public Customer saveCustomer(Customer customer){
        log.info("Inside saveUser of UserService");
        return customerRepository.save(customer);
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
