package com.securityModel.service.imple;



import com.securityModel.models.Provider;
import com.securityModel.payload.request.ProviderRequest;
import com.securityModel.payload.response.ProviderResponse;
import com.securityModel.repository.ProviderRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProviderServiceImple implements ProviderService {



    private final ProviderRepository providerRepository;

    public ProviderServiceImple(ProviderRepository providerRepositoy) {

        this.providerRepository = providerRepositoy;
    }

    @Override
    public ProviderResponse createprovider(ProviderRequest provider) {
        Provider c =ProviderResponse.toEntity(provider);
        Provider savedprovider= providerRepository.save(c);
        return ProviderResponse.fromEntity(savedprovider);
    }

    @Override
    public List<ProviderResponse> allProvider() {
        return providerRepository.findAll().stream()
                .map(ProviderResponse::fromEntity)
                .collect(Collectors.toList());

    }

    @Override
    public ProviderResponse providerById(Long id) {

        return providerRepository.findById(id)
                .map(ProviderResponse::fromEntity)
                .orElseThrow(() ->new RuntimeException("Provider not found with this id:" +id));

    }

    @Override
    public ProviderResponse updateprovider(ProviderRequest providerRequest, Long id) {

        Provider provider= providerRepository.findById(id).orElseThrow(()->
                new RuntimeException("Provider not found with this id:" +id));
        if (provider != null) {
            Provider c = ProviderResponse.toEntity(providerRequest);
            c.setId(id);
            c.setCompany(c.getCompany() == null ? provider.getCompany() : c.getCompany());
            Provider savedprovider = providerRepository.save(c);
            return ProviderResponse.fromEntity(savedprovider);
        }else{
            return null;
        }

    }

    @Override
    public HashMap<String, String> deleteprovider(Long id) {

        HashMap message =new HashMap<>();
        Provider c = providerRepository.findById(id).orElse(null);
        if (c != null) {
            try {
                providerRepository.deleteById(id);
                message.put("message", "Provider deleted successfully");
            }catch (Exception a){
                message.put("message", a.getMessage());
            }
        }else{
            message.put("message", "Provider not found: " +id);
        }
        return message;

    }
}
