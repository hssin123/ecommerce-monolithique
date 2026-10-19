package com.securityModel.service;





import com.securityModel.payload.request.CategoryRequest;
import com.securityModel.payload.response.CategoryResponse;

import java.util.HashMap;
import java.util.List;

public interface CategoryService {

    CategoryResponse createcategory(CategoryRequest category);
    List<CategoryResponse> allCategory();
    CategoryResponse categoryById(Long id);
    CategoryResponse updatecategory(CategoryRequest categoryRequest, Long id);
    HashMap<String, String> deletecategory(Long id);



}
