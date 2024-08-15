package org.example.dev17final.app.mapper;


import org.example.dev17final.app.model.Customer;
import org.example.dev17final.app.model.dto.response.CustomerResponse;
import org.example.dev17final.app.model.dto.request.CustomerUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {
    @Mapping(source = "customerDetails.dateOfBirth", target = "dateOfBirth")
    @Mapping(source = "customerDetails.loyaltyPoints", target = "loyaltyPoints")
    CustomerResponse toCustomerResponse(Customer customer);

    Customer toEntity(CustomerUpdateRequest request);

}
