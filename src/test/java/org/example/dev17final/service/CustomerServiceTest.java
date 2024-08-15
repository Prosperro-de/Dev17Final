package org.example.dev17final.service;


import org.assertj.core.api.Assertions;
import org.example.dev17final.app.service.CustomerService;
import org.example.dev17final.app.mapper.CustomerMapperImpl;
import org.example.dev17final.app.model.Customer;
import org.example.dev17final.app.model.dto.request.CustomerUpdateRequest;
import org.example.dev17final.app.model.dto.response.CustomerResponse;
import org.example.dev17final.app.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;
    @Spy
    private CustomerMapperImpl customerMapper;

    @InjectMocks
    private CustomerService customerService;


    @Test
    void testCustomerIsUpdatedSuccessfully() {
        //given
        CustomerUpdateRequest request = new CustomerUpdateRequest();
        request.setFirstName("Barak");
        request.setLastName("Obama");
        request.setPhoneNumber("666-666");
        request.setPostCode("555");

        Customer customer = new Customer();
        customer.setFirstName("Barak");
        customer.setLastName("Obama");
        customer.setPhoneNumber("5555-555");
        customer.setPostCode("555");

        when(customerRepository.findById(1L))
                .thenReturn(Optional.of(customer));

        //when
        CustomerResponse result = customerService.update(1L, request);

        //then
        Assertions.assertThat(result).usingRecursiveComparison()
                        .ignoringFields("dateOfBirth", "loyaltyPoints")
                                .isEqualTo(customer);

        verify(customerRepository).findById(1L);
        verify(customerMapper).toCustomerResponse(customer);
    }
}