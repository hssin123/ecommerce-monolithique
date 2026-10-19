package com.securityModel.service.imple;






import com.securityModel.payload.request.ClientRequest;
import com.securityModel.payload.response.ClientResponse;

import java.util.HashMap;
import java.util.List;

public interface ClientService {




    ClientResponse createclient(ClientRequest client);
    List<ClientResponse> allClient();
    ClientResponse clientById(Long id);
    ClientResponse updateclient(ClientRequest clientRequest, Long id);
    HashMap<String, String> deleteclient(Long id);


}
