package com.securityModel.service.imple;






import com.securityModel.payload.request.OrderRequest;
import com.securityModel.payload.response.OrderResponse;

import java.util.HashMap;
import java.util.List;

public interface OrderService {


    OrderResponse createorder(OrderRequest order);
    List<OrderResponse> allOrder();
    OrderResponse orderById(Long id);
    OrderResponse updateorder(OrderRequest orderRequest, Long id);
    HashMap<String, String> deleteorder(Long id);

    OrderResponse createOrderwithclientanddriver(OrderRequest orderRequest, Long id_client, Long id_driver);

    OrderResponse updateOrderwithclientanddriver(OrderRequest orderRequest, Long orderId, Long clientId, Long driverId );
}
