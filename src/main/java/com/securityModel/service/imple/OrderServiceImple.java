package com.securityModel.service.imple;




import com.securityModel.models.Client;
import com.securityModel.models.Order;
import com.securityModel.payload.request.OrderRequest;
import com.securityModel.payload.response.OrderResponse;
import com.securityModel.repository.ClientRepository;
import com.securityModel.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImple implements OrderService {



    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;

    public OrderServiceImple(OrderRepository orderRepository, ClientRepository clientRepository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public OrderResponse createorder(OrderRequest order) {
        Order c =OrderResponse.toEntity(order);
        Order savedorder= orderRepository.save(c);
        return OrderResponse.fromEntity(savedorder);
    }

    @Override
    public List<OrderResponse> allOrder() {
        return orderRepository.findAll().stream()
                .map(OrderResponse::fromEntity)
                .collect(Collectors.toList());

    }

    @Override
    public OrderResponse orderById(Long id) {

        return orderRepository.findById(id)
                .map(OrderResponse::fromEntity)
                .orElseThrow(() ->new RuntimeException("Order not found with this id:" +id));

    }

    @Override
    public OrderResponse updateorder(OrderRequest orderRequest, Long id) {

        Order order= orderRepository.findById(id).orElseThrow(()->
                new RuntimeException("Order not found with this id:" +id));
        if (order != null) {
            Order c = OrderResponse.toEntity(orderRequest);
            c.setId(id);
            c.setDescription(c.getDescription() == null ? order.getDescription() : c.getDescription());
            Order savedorder = orderRepository.save(c);
            return OrderResponse.fromEntity(savedorder);
        }else{
            return null;
        }

    }

    @Override
    public HashMap<String, String> deleteorder(Long id) {

        HashMap message =new HashMap<>();
        Order c = orderRepository.findById(id).orElse(null);
        if (c != null) {
            try {
                orderRepository.deleteById(id);
                message.put("message", "Order deleted successfully");
            }catch (Exception a){
                message.put("message", a.getMessage());
            }
        }else{
            message.put("message", "Order not found: " +id);
        }
        return message;

    }

    @Override
    public OrderResponse createOrderwithclientanddriver(OrderRequest orderRequest, Long id_client, Long id_driver) {
        Client client = clientRepository.findById(id_client).orElseThrow(() ->
                new RuntimeException("Catégorie non trouvée avec cet identifiant : " + id_client));

// Créer une nouvelle sous-catégorie
        Order order = OrderResponse.toEntity(orderRequest);
        order.setClient(client); // Associer la catégorie

// Sauvegarder la sous-catégorie dans la base de données
        Order savedorder = orderRepository.save(order);

// Retourner la réponse
        return OrderResponse.fromEntity(savedorder);
    }

    @Override
    public OrderResponse updateOrderwithclientanddriver(OrderRequest orderRequest, Long orderId, Long clientId, Long driverId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Sous-client non trouvée avec cet identifiant : " + orderId));

        if (clientId!= null) {

            Client c = clientRepository.findById(clientId).orElse(null);
            if (c != null) {
                orderRequest.setClient(c);
            } else {
                throw new RuntimeException("client not found");
            }
        }
        else{
            orderRequest.setClient(orderRequest.getClient()==null? order.getClient(): orderRequest.getClient());
        }
        if (order != null) {
            Order order1 = OrderResponse.toEntity(orderRequest);
            order1.setId(orderId);
            order.setDescription(orderRequest.getDescription());
            order.setDescription(orderRequest.getDescription());
            Order savedsub = orderRepository.save(order1);
            return OrderResponse.fromEntity(savedsub);
        } else {
            throw  new RuntimeException("order not found ");
        }
    }
}
