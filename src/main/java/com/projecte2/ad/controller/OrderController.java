package com.projecte2.ad.controller;

import com.projecte2.ad.dto.OrderRequestDTO;
import com.projecte2.ad.dto.OrderResponseDTO;
import com.projecte2.ad.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Integrant 1
    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO dto) {
        return new ResponseEntity<>(orderService.createOrder(dto), HttpStatus.CREATED);
    }

    // Integrant 1
    @PutMapping("/{id}/process")
    public ResponseEntity<OrderResponseDTO> processOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.processOrder(id));
    }

    // Integrant 2
    @PostMapping("/{id}/products")
    public ResponseEntity<OrderResponseDTO> addProductsToOrder(
            @PathVariable Long id, @RequestBody List<Long> productIds) {
        return ResponseEntity.ok(orderService.addProducts(id, productIds));
    }

    // Integrant 2
    @PutMapping("/{id}/cancel")
    public ResponseEntity<OrderResponseDTO> cancelOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.cancelOrder(id));
    }
}
