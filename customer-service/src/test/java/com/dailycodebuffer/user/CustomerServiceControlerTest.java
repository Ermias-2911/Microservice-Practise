package com.dailycodebuffer.user;

import com.dailycodebuffer.user.entity.Customer;
import com.dailycodebuffer.user.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerServiceControlerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    CustomerRepository customerRepository;

    @Test
    public void testGetCustomerByCustomerId() throws Exception {
        Long id = 1L;
        Customer expected = new Customer(id, "Ermi", "Ted", "test@gmail.com", 2L);

        Mockito.when(customerRepository.findByCustomerId(id)).thenReturn(expected);

        mockMvc.perform(get("/customer/getCustomer/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value(id))
                .andExpect(jsonPath("$.firstName").value(expected.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(expected.getLastName()))
                .andExpect(jsonPath("$.email").value(expected.getEmail()))
                .andExpect(jsonPath("$.departmentId").value(expected.getDepartmentId()))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void testGetCustomerShouldReturnCustomerNotFound() throws Exception {
        Long id = 1L;
        Mockito.when(customerRepository.findByCustomerId(id)).thenReturn(null);
        mockMvc.perform(get("/customer/getCustomer/{id}", id))
                .andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print());

    }

}
