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
@Table(name="product")

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double price;
    private String ref;
    private Integer qte;
    private String description;




    @ManyToOne
    @JoinColumn (name="subcategory_id")
    @JsonIgnoreProperties("ProductList")
    private SubCategory subcategory;

    @ManyToMany(mappedBy = "productList")
    @JsonIgnoreProperties ("productList")
    private List<Order> orderList;

    @OneToMany(mappedBy = "product")
    @JsonIgnoreProperties("product")
    private List<Gallery> galleryList;


    @ManyToOne
    @JoinColumn(name="provider_id")
    @JsonIgnoreProperties("productList")
    private Provider provider;

}
