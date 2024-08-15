package org.example.dev17final.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.example.dev17final.app.model.Customer;
import org.example.dev17final.app.model.dto.response.CustomerResponse;
import org.example.dev17final.app.model.dto.request.CustomerUpdateRequest;
import org.example.dev17final.app.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
@Tags( value = {
        @Tag(name = "Customer controller", description = "Provides basic customer management")
})
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{id}")
    @Operation(summary = "Finds customer for provided ID",
            responses = {@ApiResponse(
                            description = "OK",
                            responseCode = "200",
                            content = {@Content(mediaType = "application/json",
                                            contentSchema = @Schema(implementation = CustomerResponse.class))
                            }),
                    @ApiResponse(description = "NOT FOUND", responseCode = "404")}
    )
    public CustomerResponse findById(@PathVariable Long id) {
        return customerService.getById(id);
    }

    @GetMapping
    public CustomerResponse findByEmail(@RequestParam String email) {
        return customerService.getByEmail(email);
    }

    @PutMapping("/{id}")
    public CustomerResponse updateCustomer(@PathVariable Long id, @RequestBody CustomerUpdateRequest request) {
        return customerService.update(id, request);
    }

    @GetMapping("/findAll")
    public Page<Customer> findAll(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return customerService.findAll(pageable);
    }

    @GetMapping("/findAll/{name}")
    public List<Customer> findAllByName(@PathVariable String name) {
        return customerService.getCustomersByFirstName(name);
    }
}
