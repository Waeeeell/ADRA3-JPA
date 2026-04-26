package com.projecte2.ad.mapper;

import com.projecte2.ad.dto.AddressDTO;
import com.projecte2.ad.dto.CustomerResponseDTO;
import com.projecte2.ad.model.Address;
import com.projecte2.ad.model.Customer;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    public CustomerResponseDTO toDTO(Customer customer) {
        if (customer == null) return null;
        CustomerResponseDTO dto = new CustomerResponseDTO();
        dto.setId(customer.getId());
        dto.setNom(customer.getNom());
        dto.setCognom(customer.getCognom());
        dto.setTelefon(customer.getTelefon());
        if (customer.getAddresses() != null) {
            dto.setAddresses(customer.getAddresses().stream()
                .map(this::toAddressDTO)
                .collect(Collectors.toList()));
        }
        return dto;
    }

    public AddressDTO toAddressDTO(Address address) {
        if (address == null) return null;
        AddressDTO dto = new AddressDTO();
        dto.setId(address.getId());
        dto.setStreet(address.getStreet());
        dto.setCity(address.getCity());
        dto.setZipCode(address.getZipCode());
        return dto;
    }
}
