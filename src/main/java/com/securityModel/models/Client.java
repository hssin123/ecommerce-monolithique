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

@Table(name="client")

public class Client extends User {



    private String localisation;


    @OneToMany(mappedBy = "client")
    @JsonIgnoreProperties("client")
    private List<Order> order;

    public Client(String username, String email, String password, String localisation) {
        super(username, email, password);
        this.localisation = localisation;
    }

    public Client() {

    }
}
