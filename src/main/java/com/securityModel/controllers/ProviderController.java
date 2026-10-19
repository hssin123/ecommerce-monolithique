package com.securityModel.controllers;




import com.securityModel.payload.request.ProviderRequest;
import com.securityModel.payload.response.ProviderResponse;
import com.securityModel.service.imple.ProviderService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/Provider")
public class ProviderController {


    private final ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @PostMapping("/save")
    public ProviderResponse addProvider(@RequestBody ProviderRequest providerRequest) {
        return providerService.createprovider(providerRequest);
    }

    @GetMapping("/all")
    public List<ProviderResponse> AllProvider() {
        return providerService.allProvider();
    }

    @GetMapping("/getone/{id}")
    public ProviderResponse providerbyid(@PathVariable Long id) {
        return providerService.providerById(id);
    }

    @PutMapping("/update/{id}")
    public ProviderResponse updateprovider(@RequestBody ProviderRequest providerRequest, @PathVariable Long id) {
        return providerService.updateprovider(providerRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public HashMap<String, String> deleteprovider(@PathVariable Long id) {
        return providerService.deleteprovider(id);
    }
}
