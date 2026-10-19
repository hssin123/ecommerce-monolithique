package com.securityModel.service.imple;




import com.securityModel.models.Category;
import com.securityModel.models.SubCategory;
import com.securityModel.payload.request.SubCategoryRequest;
import com.securityModel.payload.response.SubCategoryResponse;
import com.securityModel.repository.CategoryRepository;
import com.securityModel.repository.SubCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;




@Service
public class SubCategoryServiceImple implements SubCategoryService {



    private final SubCategoryRepository subcategoryRepository;
    private final CategoryRepository categoryRepository;

    public SubCategoryServiceImple(SubCategoryRepository subcategoryRepository, CategoryRepository categoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public SubCategoryResponse createsubcategory(SubCategoryRequest subcategory) {
        SubCategory c =SubCategoryResponse.toEntity(subcategory);
        SubCategory savedsubcategory= subcategoryRepository.save(c);
        return SubCategoryResponse.fromEntity(savedsubcategory);
    }

    @Override
    public List<SubCategoryResponse> allSubCategory() {
        return subcategoryRepository.findAll().stream()
                .map(SubCategoryResponse::fromEntity)
                .collect(Collectors.toList());

    }

    @Override
    public SubCategoryResponse subcategoryById(Long id) {

        return subcategoryRepository.findById(id)
                .map(SubCategoryResponse::fromEntity)
                .orElseThrow(() ->new RuntimeException("SubCategory not found with this id:" +id));

    }

    @Override
    public SubCategoryResponse updatesubcategory(SubCategoryRequest subcategoryRequest, Long id) {

        SubCategory subcategory= subcategoryRepository.findById(id).orElseThrow(()->
                new RuntimeException("SubCategory not found with this id:" +id));
        if (subcategory != null) {
            SubCategory c = SubCategoryResponse.toEntity(subcategoryRequest);
            c.setId(id);
            c.setName(c.getName() == null ? subcategory.getName() : c.getName());
            SubCategory savedsubcategory = subcategoryRepository.save(c);
            return SubCategoryResponse.fromEntity(savedsubcategory);
        }else{
            return null;
        }

    }

    @Override
    public HashMap<String, String> deletesubcategory(Long id) {

        HashMap message =new HashMap<>();
        SubCategory c = subcategoryRepository.findById(id).orElse(null);
        if (c != null) {
            try {
                subcategoryRepository.deleteById(id);
                message.put("message", "SubCategory deleted successfully");
            }catch (Exception a){
                message.put("message", a.getMessage());
            }
        }else{
            message.put("message", "SubCategory not found: " +id);
        }
        return message;

    }


    @Override
    public SubCategoryResponse createSubCategorywithcategory(SubCategoryRequest subcategoryRequest, Long id_category) {
        // Récupérer la catégorie à partir de l'ID fourni

        Category category = categoryRepository.findById(id_category).orElseThrow(() ->
                new RuntimeException("Catégorie non trouvée avec cet identifiant : " + id_category));

// Créer une nouvelle sous-catégorie
        SubCategory subCategory = SubCategoryResponse.toEntity(subcategoryRequest);
        subCategory.setCategory(category); // Associer la catégorie

// Sauvegarder la sous-catégorie dans la base de données
        SubCategory savedsubCategory = subcategoryRepository.save(subCategory);

// Retourner la réponse
        return SubCategoryResponse.fromEntity(savedsubCategory);
    }

    @Override
    public SubCategoryResponse updateSubCategorywithcategory(SubCategoryRequest subcategoryRequest, Long subcategoryId, Long id_category) {
        // Récupérer la sous-catégorie à partir de l'ID fourni
        SubCategory subCategory = subcategoryRepository.findById(subcategoryId)
                .orElseThrow(() -> new RuntimeException("Sous-catégorie non trouvée avec cet identifiant : " + subcategoryId));

        if (id_category!= null) {

            Category c = categoryRepository.findById(id_category).orElse(null);
            if (c != null) {
                subcategoryRequest.setCategory(c);
            } else {
                throw new RuntimeException("categorie not found");
            }
        }
        else{
            subcategoryRequest.setCategory(subcategoryRequest.getCategory()==null? subCategory.getCategory(): subcategoryRequest.getCategory());
        }
        if (subCategory != null) {
            SubCategory subcategory1 = SubCategoryResponse.toEntity(subcategoryRequest);
            subcategory1.setId(subcategoryId);
            subCategory.setName(subcategoryRequest.getName());
            subCategory.setDescription(subcategoryRequest.getDescription());
            SubCategory savedsub = subcategoryRepository.save(subcategory1);
            return SubCategoryResponse.fromEntity(savedsub);
        } else {
            throw  new RuntimeException("subcategory not found ");
        }
    }
}
