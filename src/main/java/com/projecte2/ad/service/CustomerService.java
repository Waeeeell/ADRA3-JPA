package com.projecte2.ad.service;

import com.projecte2.ad.dto.AddressDTO;
import com.projecte2.ad.dto.CustomerResponseDTO;
import com.projecte2.ad.mapper.CustomerMapper;
import com.projecte2.ad.model.Address;
import com.projecte2.ad.model.Customer;
import com.projecte2.ad.repository.AddressRepository;
import com.projecte2.ad.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.customerMapper = customerMapper;
    }

    @Transactional
    public CustomerResponseDTO addAddresses(Long customerId, List<AddressDTO> addressDTOs) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        if (customer.getAddresses() == null) {
            customer.setAddresses(new ArrayList<>());
        }

        for (AddressDTO dto : addressDTOs) {
            Address address = new Address();
            address.setStreet(dto.getStreet());
            address.setCity(dto.getCity());
            address.setZipCode(dto.getZipCode());
            address.setCustomer(customer);
            customer.getAddresses().add(address);
        }

        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toDTO(savedCustomer);
    }

    public CustomerResponseDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return customerMapper.toDTO(customer);
    }

    public List<CustomerResponseDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteAddresses(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.getAddresses().clear();
        customerRepository.save(customer);
    }
}
