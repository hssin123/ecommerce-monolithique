package com.securityModel.service.imple;





import com.securityModel.payload.request.ProductRequest;
import com.securityModel.payload.response.ProductResponse;

import java.util.HashMap;
import java.util.List;

public interface ProductService {


    ProductResponse createproduct(ProductRequest product);
    List<ProductResponse> allProduct();
    ProductResponse productById(Long id);
    ProductResponse updateproduct(ProductRequest productRequest, Long id);
    HashMap<String, String> deleteproduct(Long id);

    ProductResponse createProductwithprovider(ProductRequest productRequest, Long id_provider);
    ProductResponse createProductwithsubcategorie(ProductRequest productRequest, Long id_subcategorie);

    ProductResponse updateProductwithprovider(ProductRequest productRequest, Long productId, Long provider_id );
}
