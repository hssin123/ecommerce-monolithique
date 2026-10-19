package com.securityModel.controllers;



import com.securityModel.payload.request.CategoryRequest;
import com.securityModel.payload.response.CategoryResponse;
import com.securityModel.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/Category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/save")
    public CategoryResponse addCategory(@RequestBody CategoryRequest categoryRequest) {
        return categoryService.createcategory(categoryRequest);
    }

    @GetMapping("/getone/{id}")
    public CategoryResponse categorybyid(@PathVariable Long id) {
        return categoryService.categoryById(id);
    }


    @GetMapping("/all")
    public List<CategoryResponse> AllCategory() {
        return categoryService.allCategory();
    }



    @PutMapping("/update/{id}")
    public CategoryResponse updatecategory(@RequestBody CategoryRequest categoryRequest, @PathVariable Long id) {
        return categoryService.updatecategory(categoryRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public HashMap<String, String> deletecategory(@PathVariable Long id) {
        return categoryService.deletecategory(id);
    }
}
