package com.securityModel.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="subcategory")

public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;


    @ManyToOne
    @JoinColumn (name="category_id")
    @JsonIgnoreProperties("subcategoryList")
    private Category category;

    @OneToMany(mappedBy = "subcategory")
    @JsonIgnoreProperties("subcategory")
    private List<Product> ProductList;
}
