package com.projecte2.ad.controller;

import com.projecte2.ad.dto.AddressDTO;
import com.projecte2.ad.dto.CustomerResponseDTO;
import com.projecte2.ad.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Integrant 2
    @PostMapping("/{id}/addresses")
    public ResponseEntity<CustomerResponseDTO> addAddressesToCustomer(
            @PathVariable Long id, @RequestBody List<AddressDTO> addressDTOs) {
        return ResponseEntity.ok(customerService.addAddresses(id, addressDTOs));
    }

    // Integrant 2
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    // Integrant 1
    @DeleteMapping("/{id}/addresses")
    public ResponseEntity<Void> deleteCustomerAddresses(@PathVariable Long id) {
        customerService.deleteAddresses(id);
        return ResponseEntity.noContent().build();
    }

    // Integrant 1
    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }
}
