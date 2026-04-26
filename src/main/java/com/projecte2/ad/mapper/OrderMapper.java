package com.projecte2.ad.mapper;

import com.projecte2.ad.dto.OrderItemResponseDTO;
import com.projecte2.ad.dto.OrderResponseDTO;
import com.projecte2.ad.dto.ProductDTO;
import com.projecte2.ad.model.Order;
import com.projecte2.ad.model.OrderItem;
import com.projecte2.ad.model.Product;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public OrderResponseDTO toDTO(Order order) {
        if (order == null) return null;
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setOrderStatus(order.getOrderStatus());
        dto.setTotalAmounts(order.getTotalAmounts());
        if (order.getOrderItems() != null) {
            dto.setOrderItems(order.getOrderItems().stream()
                .map(this::toOrderItemDTO)
                .collect(Collectors.toList()));
        }
        return dto;
    }

    public OrderItemResponseDTO toOrderItemDTO(OrderItem item) {
        if (item == null) return null;
        OrderItemResponseDTO dto = new OrderItemResponseDTO();
        dto.setId(item.getId());
        dto.setQuantity(item.getQuantity());
        dto.setPrice(item.getPrice());
        if (item.getProduct() != null) {
            dto.setProduct(toProductDTO(item.getProduct()));
        }
        return dto;
    }

    public ProductDTO toProductDTO(Product product) {
        if (product == null) return null;
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        return dto;
    }
}
