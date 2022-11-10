package com.dailycodebuffer.user;

import com.dailycodebuffer.user.entity.Customer;
import com.dailycodebuffer.user.exception.CustomerNotFoundException;
import com.dailycodebuffer.user.repository.CustomerRepository;
import com.dailycodebuffer.user.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerService customerService;


    @Test
    @DisplayName("Testing CustomerService class save method")
    public  void TestSaveCustomerMethod(){
        Customer expected = new Customer(1L, "Ermi","Ted","test@gmail.com",2L);
        Customer expected2 = new Customer(3L, "Tedi","Jhon","jhon@gmail.com",4L);

        // Mockito.doNothing().when(customerRepository.save(Mockito.any(Customer.class)));
        Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(expected);

       Customer actual =  customerService.saveCustomer(expected);
        Assertions.assertNotEquals(expected2.getFirstName(), actual.getFirstName());
        Assertions.assertEquals(expected.getFirstName(), actual.getFirstName());
    }

    @Test
    @DisplayName("Testing CustomerService class get customers method")
    public  void TestGetCustomersMethod(){
        Customer expected = new Customer(1L, "Ermi","Ted","test@gmail.com",2L);
        Customer expected2 = new Customer(3L, "Tedi","Jhon","jhon@gmail.com",4L);
        List<Customer> expectedCustomers = new ArrayList<>();
        expectedCustomers.add(expected);
        expectedCustomers.add(expected2);

        Mockito.when(customerRepository.findAll()).thenReturn(expectedCustomers);

        List<Customer> actual =  customerService.getCustomers();

        Assertions.assertEquals(expectedCustomers, actual);
        Assertions.assertEquals(expectedCustomers.get(0).getFirstName(), actual.get(0).getFirstName());
    }

    @Test
    @DisplayName("Testing CustomerService class get customers method exception")
    public  void TestGetCustomersMethod_CustomerNotFoundException(){

        Mockito.when(customerRepository.findAll()).thenReturn(Collections.emptyList());
       Assertions.assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomers());
    }


    @Test
    @DisplayName("Testing CustomerService class get specific customer method")
    public  void TestGetSpecificCustomerMethod(){
        Customer expected = new Customer(1L, "Ermi","Ted","test@gmail.com",2L);
        Customer expected2 = new Customer(3L, "Tedi","Jhon","jhon@gmail.com",4L);

        Mockito.when(customerRepository.findByCustomerId(Mockito.anyLong())).thenReturn(expected);
        Customer actual = customerService.getCustomerById(1L);

        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(expected.getFirstName(), actual.getFirstName());
    }
}
