package com.securityModel.controllers;




import com.securityModel.payload.request.ClientRequest;
import com.securityModel.payload.response.ClientResponse;
import com.securityModel.service.imple.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/Client")
public class ClientController {


    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/save")
    public ClientResponse addClient(@RequestBody ClientRequest clientRequest) {
        return clientService.createclient(clientRequest);
    }

    @GetMapping("/all")
    public List<ClientResponse> AllClient() {
        return clientService.allClient();
    }

    @GetMapping("/getone/{id}")
    public ClientResponse clientbyid(@PathVariable Long id) {
        return clientService.clientById(id);
    }

    @PutMapping("/update/{id}")
    public ClientResponse updateclient(@RequestBody ClientRequest clientRequest, @PathVariable Long id) {
        return clientService.updateclient(clientRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public HashMap<String, String> deleteclient(@PathVariable Long id) {
        return clientService.deleteclient(id);
    }
}
