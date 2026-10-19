package com.securityModel.service.imple;




import com.securityModel.models.Client;
import com.securityModel.payload.request.ClientRequest;
import com.securityModel.payload.response.ClientResponse;
import com.securityModel.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImple implements ClientService {



    private final ClientRepository ClientRepository;

    public ClientServiceImple(ClientRepository clientRepository) {
        ClientRepository = clientRepository;
    }



    @Override
    public ClientResponse createclient(ClientRequest client) {
        Client c =ClientResponse.toEntity(client);
        Client savedclient= ClientRepository.save(c);
        return ClientResponse.fromEntity(savedclient);
    }

    @Override
    public List<ClientResponse> allClient() {
        return ClientRepository.findAll().stream()
                .map(ClientResponse::fromEntity)
                .collect(Collectors.toList());

    }

    @Override
    public ClientResponse clientById(Long id) {

        return ClientRepository.findById(id)
                .map(ClientResponse::fromEntity)
                .orElseThrow(() ->new RuntimeException("Client not found with this id:" +id));

    }

    @Override
    public ClientResponse updateclient(ClientRequest clientRequest, Long id) {

        Client client= ClientRepository.findById(id).orElseThrow(()->
                new RuntimeException("Client not found with this id:" +id));
        if (client != null) {
            Client c = ClientResponse.toEntity(clientRequest);
            c.setId(id);
            c.setLocalisation(c.getLocalisation() == null ? client.getLocalisation() : c.getLocalisation());
            Client savedclient = ClientRepository.save(c);
            return ClientResponse.fromEntity(savedclient);
        }else{
            return null;
        }

    }

    @Override
    public HashMap<String, String> deleteclient(Long id) {

        HashMap message =new HashMap<>();
        Client c = ClientRepository.findById(id).orElse(null);
        if (c != null) {
            try {
                ClientRepository.deleteById(id);
                message.put("message", "Client deleted successfully");
            }catch (Exception a){
                message.put("message", a.getMessage());
            }
        }else{
            message.put("message", "Client not found: " +id);
        }
        return message;

    }
}
