package com.securityModel.payload.response;



import com.securityModel.models.Category;
import com.securityModel.payload.request.CategoryRequest;
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

public class CategoryResponse {

    private Long id;
    private String name;
    private String description;
    private List<SubCategoryResponse> subCategories;

    public static CategoryResponse fromEntity(Category category) {
        CategoryResponse CategoryResponse = new CategoryResponse();
        BeanUtils.copyProperties(category, CategoryResponse);
        if(category.getSubcategoryList() != null) {
            //Conversion des sous-catégories
            CategoryResponse.setSubCategories(category.getSubcategoryList().stream()
                    .map(SubCategoryResponse::fromEntity)
                    .collect(Collectors.toList()));
        }
        return CategoryResponse;
    }

    public static Category toEntity(CategoryRequest categoryRequest) {
        Category s = new Category();
        BeanUtils.copyProperties(categoryRequest, s);
        return s;
    }
}
