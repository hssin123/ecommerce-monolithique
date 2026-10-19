package com.securityModel.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter

@Table(name="provider")

public class Provider extends User {

    private String company;

    @OneToMany(mappedBy = "provider")
    @JsonIgnoreProperties("provider")
    private List<Product> productList;

    public Provider(String username, String email, String password, String company) {
        super(username, email, password);
        this.company = company;
    }

    public Provider() {

    }
}
