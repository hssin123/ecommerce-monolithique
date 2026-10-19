package com.securityModel.payload.request;


import com.securityModel.models.Provider;
import com.securityModel.models.SubCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProductRequest {

    private Long id;
    private Double price;
    private String ref;
    private Integer qte;
    private String description;
    private Provider provider;
    private SubCategory subCategory;

    public void setProvider(Provider provider) {
        this.provider = provider;

    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }
}
