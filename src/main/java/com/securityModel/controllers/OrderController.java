package com.securityModel.controllers;



import com.securityModel.payload.request.OrderRequest;
import com.securityModel.payload.response.OrderResponse;
import com.securityModel.service.imple.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/Order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/save")
    public OrderResponse addOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.createorder(orderRequest);
    }

    @GetMapping("/all")
    public List<OrderResponse> AllOrder() {
        return orderService.allOrder();
    }

    @GetMapping("/getone/{id}")
    public OrderResponse orderbyid(@PathVariable Long id) {
        return orderService.orderById(id);
    }

    @PutMapping("/update/{id}")
    public OrderResponse updateorder(@RequestBody OrderRequest orderRequest, @PathVariable Long id) {
        return orderService.updateorder(orderRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public HashMap<String, String> deleteorder(@PathVariable Long id) {
        return orderService.deleteorder(id);
    }

    @PostMapping("/savec/{id_clien}/{id_driv}")
    public OrderResponse addOrderDEClient(@RequestBody OrderRequest orderRequest, @PathVariable("id_clien") Long  id_client,  @PathVariable("id_driv") Long id_driver) {
        return  orderService.createOrderwithclientanddriver(orderRequest, id_client, id_driver);
    }


    @PutMapping("/updatee/{id_order}/{id_clien}/{id_driv}")
    public OrderResponse updateOrderDEClient(@RequestBody OrderRequest orderRequest, @PathVariable(value = "id_order", required = false) Long id_order, @PathVariable(value = "id_clien", required = false) Long id_clien,  @PathVariable("id_driv") Long id_driv) {
        return  orderService.updateOrderwithclientanddriver(orderRequest, id_order, id_clien, id_driv);
    }
}
