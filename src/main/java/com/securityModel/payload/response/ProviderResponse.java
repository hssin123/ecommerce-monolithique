package com.securityModel.payload.response;

import com.securityModel.models.Provider;
import com.securityModel.payload.request.ProviderRequest;
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

public class ProviderResponse {

    private Long id;
    private String company;
    private List<ProductResponse> products;

    public static ProviderResponse fromEntity(Provider provider) {
        ProviderResponse subProviderResponse = new ProviderResponse();
        BeanUtils.copyProperties(provider, subProviderResponse);
        if(provider.getProductList() != null) {
            //Conversion des sous-catégories
            subProviderResponse.setProducts(provider.getProductList().stream()
                    .map(ProductResponse::fromEntity)
                    .collect(Collectors.toList()));
        }
        return subProviderResponse;
    }

    public static Provider toEntity(ProviderRequest providerRequest) {
        Provider s = new Provider();
        BeanUtils.copyProperties(providerRequest, s);
        return s;
    }
}
