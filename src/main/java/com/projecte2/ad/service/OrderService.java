package com.projecte2.ad.service;

import com.projecte2.ad.dto.OrderRequestDTO;
import com.projecte2.ad.dto.OrderResponseDTO;
import com.projecte2.ad.mapper.OrderMapper;
import com.projecte2.ad.model.Customer;
import com.projecte2.ad.model.Order;
import com.projecte2.ad.model.OrderItem;
import com.projecte2.ad.model.Product;
import com.projecte2.ad.repository.CustomerRepository;
import com.projecte2.ad.repository.OrderRepository;
import com.projecte2.ad.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, ProductRepository productRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.orderMapper = orderMapper;
    }

    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO dto) {
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderStatus("PENDENT");
        order.setOrderItems(new ArrayList<>());
        
        double total = 0.0;
        
        for (Long productId : dto.getProductIds()) {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(1);
            item.setPrice(product.getPrice());
            
            order.getOrderItems().add(item);
            total += product.getPrice();
        }
        
        order.setTotalAmounts(total);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toDTO(savedOrder);
    }

    @Transactional
    public OrderResponseDTO processOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        if (!"PENDENT".equalsIgnoreCase(order.getOrderStatus())) {
            throw new RuntimeException("L'status ha de ser pendent");
        }
        order.setOrderStatus("PROCESSAT");
        return orderMapper.toDTO(orderRepository.save(order));
    }

    @Transactional
    public OrderResponseDTO cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        if (!"PENDENT".equalsIgnoreCase(order.getOrderStatus())) {
            throw new RuntimeException("L'status ha de ser pendent");
        }
        order.setOrderStatus("CANCELAT");
        return orderMapper.toDTO(orderRepository.save(order));
    }

    @Transactional
    public OrderResponseDTO addProducts(Long orderId, List<Long> productIds) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        
        double addedTotal = 0.0;
        for (Long productId : productIds) {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(1);
            item.setPrice(product.getPrice());
            
            order.getOrderItems().add(item);
            addedTotal += product.getPrice();
        }
        
        order.setTotalAmounts(order.getTotalAmounts() + addedTotal);
        return orderMapper.toDTO(orderRepository.save(order));
    }
}
