package com.securityModel.payload.response;



import com.securityModel.models.Order;
import com.securityModel.payload.request.OrderRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class OrderResponse {

    private Long id;
    private String ref;
    private String description;
    private Integer qte_totale;
    private Double price_totale;
    private Boolean state;
    private List<ProductResponse> products;

    public static OrderResponse fromEntity(Order order) {
        OrderResponse subOrderResponse = new OrderResponse();
        BeanUtils.copyProperties(order, subOrderResponse);
        if(order.getProductList() != null) {
            //Conversion des sous-catégories
            subOrderResponse.setProducts(order.getProductList().stream()
                    .map(ProductResponse::fromEntity)
                    .collect(Collectors.toList()));
        }
        return subOrderResponse;
    }

    public static Order toEntity(OrderRequest orderRequest) {
        Order s = new Order();
        BeanUtils.copyProperties(orderRequest, s);
        return s;
    }
}
