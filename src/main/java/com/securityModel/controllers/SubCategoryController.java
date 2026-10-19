package com.securityModel.controllers;





import com.securityModel.payload.request.SubCategoryRequest;
import com.securityModel.payload.response.SubCategoryResponse;
import com.securityModel.service.imple.SubCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/SubCategory")
public class SubCategoryController {


    private final SubCategoryService subcategoryService;

    public SubCategoryController(SubCategoryService subsubcategoryService) {
        this.subcategoryService = subsubcategoryService;
    }

    @PostMapping("/save")
    public SubCategoryResponse addSubSubCategory(@RequestBody SubCategoryRequest subsubcategoryRequest) {
        return subcategoryService.createsubcategory(subsubcategoryRequest);
    }


    @GetMapping("/all")
    public List<SubCategoryResponse> AllSubCategory() {
        return subcategoryService.allSubCategory();
    }

    @GetMapping("/getone/{id}")
    public SubCategoryResponse subcategorybyid(@PathVariable Long id) {
        return subcategoryService.subcategoryById(id);
    }

    @PutMapping("/update/{id}")
    public SubCategoryResponse updatesubcategory(@RequestBody SubCategoryRequest subcategoryRequest, @PathVariable Long id) {
        return subcategoryService.updatesubcategory(subcategoryRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public HashMap<String, String> deletesubcategory(@PathVariable Long id) {
        return subcategoryService.deletesubcategory(id);
    }

    @PostMapping("/savec/{id_cat}")
    public SubCategoryResponse addSubCategoryDECategory(@RequestBody SubCategoryRequest subCategoryRequest, @PathVariable("id_cat") Long id) {
        return  subcategoryService.createSubCategorywithcategory(subCategoryRequest, id);
    }


    @PutMapping("/updatee/{id_subcat}/{id_cat}")
    public SubCategoryResponse updateSubCategoryDECategory(@RequestBody SubCategoryRequest subCategoryRequest, @PathVariable(value = "id_cat",required = false) Long id_cat, @PathVariable("id_subcat") Long id_subcat) {
        return  subcategoryService.updateSubCategorywithcategory(subCategoryRequest, id_subcat, id_cat);
    }
}
