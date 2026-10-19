package com.securityModel.payload.response;



import com.securityModel.models.Category;
import com.securityModel.models.SubCategory;
import com.securityModel.payload.request.SubCategoryRequest;
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

public class SubCategoryResponse {

    private Long id;
    private String name;
    private String description;
    private Category category;
    private List<ProductResponse> products;

    public static SubCategoryResponse fromEntity(SubCategory subcategory) {
        SubCategoryResponse subCategoryResponse = new SubCategoryResponse();
        BeanUtils.copyProperties(subcategory, subCategoryResponse);
        if(subcategory.getProductList() != null) {
            //Conversion des sous-catégories
            subCategoryResponse.setProducts(subcategory.getProductList().stream()
                    .map(ProductResponse::fromEntity)
                    .collect(Collectors.toList()));
        }
        return subCategoryResponse;
    }

    public static SubCategory toEntity(SubCategoryRequest subcategoryRequest) {
        SubCategory s = new SubCategory();
        BeanUtils.copyProperties(subcategoryRequest, s);
        return s;
    }

}
