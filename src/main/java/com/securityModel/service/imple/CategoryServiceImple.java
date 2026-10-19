package com.securityModel.service.imple;



import com.securityModel.models.Category;
import com.securityModel.payload.request.CategoryRequest;
import com.securityModel.payload.response.CategoryResponse;
import com.securityModel.repository.CategoryRepository;
import com.securityModel.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImple implements CategoryService {



    private final CategoryRepository categoryRepository;

    public CategoryServiceImple(CategoryRepository categoryRepositoy) {

        this.categoryRepository = categoryRepositoy;
    }

    @Override
    public CategoryResponse createcategory(CategoryRequest category) {
        Category c =CategoryResponse.toEntity(category);
        Category savedcategory= categoryRepository.save(c);
        return CategoryResponse.fromEntity(savedcategory);
    }

    @Override
    public List<CategoryResponse> allCategory() {
        return categoryRepository.findAll().stream()
                .map(CategoryResponse::fromEntity)
                .collect(Collectors.toList());

    }

    @Override
    public CategoryResponse categoryById(Long id) {

        return categoryRepository.findById(id)
                .map(CategoryResponse::fromEntity)
                .orElseThrow(() ->new RuntimeException("Category not found with this id:" +id));

    }

    @Override
    public CategoryResponse updatecategory(CategoryRequest categoryRequest, Long id) {

        Category category= categoryRepository.findById(id).orElseThrow(()->
                new RuntimeException("Category not found with this id:" +id));
        if (category != null) {
            Category c = CategoryResponse.toEntity(categoryRequest);
            c.setId(id);
            c.setName(c.getName() == null ? category.getName() : c.getName());
            Category savedcategory = categoryRepository.save(c);
            return CategoryResponse.fromEntity(savedcategory);
        }else{
            return null;
        }

    }

    @Override
    public HashMap<String, String> deletecategory(Long id) {

        HashMap message =new HashMap<>();
        Category c = categoryRepository.findById(id).orElse(null);
        if (c != null) {
            try {
                categoryRepository.deleteById(id);
                message.put("message", "Category deleted successfully");
            }catch (Exception a){
                message.put("message", a.getMessage());
            }
        }else{
            message.put("message", "Category not found: " +id);
        }
        return message;

    }
}
