package com.securityModel.payload.response;

import com.securityModel.models.Product;
import com.securityModel.models.SubCategory;
import com.securityModel.payload.request.ProductRequest;
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

public class ProductResponse {

    private Long id;
    private Double price;
    private String ref;
    private Integer qte;
    private String description;
    private List<GalleryResponse> gallery;
    private SubCategory subCategory;

    public static ProductResponse fromEntity(Product product) {
        ProductResponse subProductResponse = new ProductResponse();
        BeanUtils.copyProperties(product, subProductResponse);
        // Mapping de la galerie
        if (product.getGalleryList() != null) {
            subProductResponse.setGallery(
                    product.getGalleryList().stream()
                            .map(GalleryResponse::fromEntity)
                            .collect(Collectors.toList())
            );
        }
        // Mapping de la sous-catégorie
        if (product.getSubcategory() != null) {
            subProductResponse.setSubCategory(product.getSubcategory()); // ou utilise un SubCategoryResponse si nécessaire
        }
        return subProductResponse;
    }

    public static Product toEntity(ProductRequest productRequest) {
        Product s = new Product();
        BeanUtils.copyProperties(productRequest, s);
        return s;
    }
}
