package com.securityModel.service.imple;


import com.securityModel.models.Product;
import com.securityModel.models.Provider;
import com.securityModel.models.SubCategory;
import com.securityModel.payload.request.ProductRequest;
import com.securityModel.payload.response.ProductResponse;
import com.securityModel.repository.ProductRepository;
import com.securityModel.repository.ProviderRepository;
import com.securityModel.repository.SubCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImple implements ProductService {



    private final SubCategoryRepository subCategoryRepository;

    private final ProductRepository productRepository;
    private final ProviderRepository providerRepository;

    public ProductServiceImple(ProductRepository productRepositoy, ProviderRepository providerRepository, SubCategoryRepository subCategoryRepository) {

        this.subCategoryRepository = subCategoryRepository;
        this.productRepository = productRepositoy;
        this.providerRepository = providerRepository;
    }

    @Override
    public ProductResponse createproduct(ProductRequest product) {
        Product c =ProductResponse.toEntity(product);
        Product savedproduct= productRepository.save(c);
        return ProductResponse.fromEntity(savedproduct);
    }

    @Override
    public List<ProductResponse> allProduct() {
        return productRepository.findAll().stream()
                .map(ProductResponse::fromEntity)
                .collect(Collectors.toList());

    }

    @Override
    public ProductResponse productById(Long id) {

        return productRepository.findById(id)
                .map(ProductResponse::fromEntity)
                .orElseThrow(() ->new RuntimeException("Product not found with this id:" +id));

    }

    @Override
    public ProductResponse updateproduct(ProductRequest productRequest, Long id) {

        Product product= productRepository.findById(id).orElseThrow(()->
                new RuntimeException("Product not found with this id:" +id));
        if (product != null) {
            Product c = ProductResponse.toEntity(productRequest);
            c.setId(id);
            c.setDescription(c.getDescription() == null ? product.getDescription() : c.getDescription());
            Product savedproduct = productRepository.save(c);
            return ProductResponse.fromEntity(savedproduct);
        }else{
            return null;
        }

    }

    @Override
    public HashMap<String, String> deleteproduct(Long id) {

        HashMap message =new HashMap<>();
        Product c = productRepository.findById(id).orElse(null);
        if (c != null) {
            try {
                productRepository.deleteById(id);
                message.put("message", "Product deleted successfully");
            }catch (Exception a){
                message.put("message", a.getMessage());
            }
        }else{
            message.put("message", "Product not found: " +id);
        }
        return message;

    }

    @Override
    public ProductResponse createProductwithprovider(ProductRequest productRequest, Long id_provider) {

        Provider provider = providerRepository.findById(id_provider).orElseThrow(() ->
                new RuntimeException("Provider non trouvée avec cet identifiant : " + id_provider));

// Créer une nouvelle sous-catégorie
        Product product = ProductResponse.toEntity(productRequest);
        product.setProvider(provider); // Associer la catégorie

// Sauvegarder la sous-catégorie dans la base de données
        Product savedproduct = productRepository.save(product);

// Retourner la réponse
        return ProductResponse.fromEntity(savedproduct);
    }

    @Override
    public ProductResponse updateProductwithprovider(ProductRequest productRequest, Long productId, Long provider_id) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Sous-product non trouvée avec cet identifiant : " + productId));

        if (provider_id!= null) {

            Provider p = providerRepository.findById(provider_id).orElse(null);
            if (p != null) {
                productRequest.setProvider(p);
            } else {
                throw new RuntimeException("provider not found");
            }
        }
        else{
            productRequest.setProvider(productRequest.getProvider()==null? product.getProvider(): productRequest.getProvider());
        }
        if (product != null) {
            Product product1 = ProductResponse.toEntity(productRequest);
            product1.setId( productId);
            product.setProvider(productRequest.getProvider());
            product.setDescription(productRequest.getDescription());
            Product savedsub = productRepository.save(product1);
            return ProductResponse.fromEntity(savedsub);
        } else {
            throw  new RuntimeException("Product not found ");
        }

    }

    @Override
    public ProductResponse createProductwithsubcategorie(ProductRequest productRequest, Long id_subcategorie) {
        SubCategory subCategory = subCategoryRepository.findById(id_subcategorie).orElseThrow(() ->
                new RuntimeException("Subcategorie non trouvée avec cet identifiant : " + id_subcategorie));

// Créer une nouvelle sous-catégorie
        Product product = ProductResponse.toEntity(productRequest);
        product.setSubcategory(subCategory); // Associer la catégorie

// Sauvegarder la sous-catégorie dans la base de données
        Product savedproduct = productRepository.save(product);

// Retourner la réponse
        return ProductResponse.fromEntity(savedproduct);
    }
}

