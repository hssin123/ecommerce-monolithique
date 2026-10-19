package com.securityModel.service.imple;





import com.securityModel.payload.request.ProviderRequest;
import com.securityModel.payload.response.ProviderResponse;

import java.util.HashMap;
import java.util.List;

public interface ProviderService {


    ProviderResponse createprovider(ProviderRequest provider);
    List<ProviderResponse> allProvider();
    ProviderResponse providerById(Long id);
    ProviderResponse updateprovider(ProviderRequest providerRequest, Long id);
    HashMap<String, String> deleteprovider(Long id);


}
