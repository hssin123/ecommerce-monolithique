package com.securityModel.service.imple;






import com.securityModel.payload.request.SubCategoryRequest;
import com.securityModel.payload.response.SubCategoryResponse;

import java.util.HashMap;
import java.util.List;

public interface SubCategoryService {


    SubCategoryResponse createsubcategory(SubCategoryRequest subcategory);
    List<SubCategoryResponse> allSubCategory();
    SubCategoryResponse subcategoryById(Long id);
    SubCategoryResponse updatesubcategory(SubCategoryRequest subcategoryRequest, Long id);
    HashMap<String, String> deletesubcategory(Long id);


    SubCategoryResponse createSubCategorywithcategory(SubCategoryRequest subcategoryRequest, Long id_category);

    SubCategoryResponse updateSubCategorywithcategory(SubCategoryRequest subcategoryRequest,Long subcategoryId, Long id_category );
}
