package com.projecte2.ad.dto;

import java.util.List;

public class OrderResponseDTO {
    private Long id;
    private String orderStatus;
    private Double totalAmounts;
    private List<OrderItemResponseDTO> orderItems;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getOrderStatus() { return orderStatus; }
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }
    public Double getTotalAmounts() { return totalAmounts; }
    public void setTotalAmounts(Double totalAmounts) { this.totalAmounts = totalAmounts; }
    public List<OrderItemResponseDTO> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItemResponseDTO> orderItems) { this.orderItems = orderItems; }
}
