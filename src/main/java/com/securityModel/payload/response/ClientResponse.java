package com.securityModel.payload.response;




import com.securityModel.models.Client;
import com.securityModel.payload.request.ClientRequest;
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

public class ClientResponse {

    private Long id;
    private String localisation;
    private List<OrderResponse> orders;

    public static ClientResponse fromEntity(Client client) {
        ClientResponse ClientResponse = new ClientResponse();
        BeanUtils.copyProperties(client, ClientResponse);
        if(client.getOrder() != null) {
            //Conversion des sous-catégories
            ClientResponse.setOrders(client.getOrder().stream()
                    .map(OrderResponse::fromEntity)
                    .collect(Collectors.toList()));
        }
        return ClientResponse;
    }

    public static Client toEntity(ClientRequest clientRequest) {
        Client s = new Client();
        BeanUtils.copyProperties(clientRequest, s);
        return s;
    }
}
