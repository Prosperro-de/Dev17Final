package org.example.dev17final.app.model.dto.request;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerUpdateRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String postCode;
}
