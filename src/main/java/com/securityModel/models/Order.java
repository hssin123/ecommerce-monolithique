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
@Table(name="commande")

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ref;
    private String description;
    private Integer qte_totale;
    private Double price_totale;
    private Boolean state;

    @ManyToMany
    @JsonIgnoreProperties("orderList")
    @JoinTable(name="order_product",joinColumns=@JoinColumn(name="order_id"),inverseJoinColumns=@JoinColumn(name="product_id"))
    private List<Product> productList;

    @ManyToOne
    @JoinColumn (name="client_id")
    @JsonIgnoreProperties("orderList")
    private Client client;

    @ManyToOne
    @JoinColumn (name="driver_id")
    @JsonIgnoreProperties("orderList")
    private Driver driver;

}
