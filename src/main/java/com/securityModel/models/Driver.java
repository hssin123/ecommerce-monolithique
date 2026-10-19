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

@Table(name="driver")


public class Driver extends User {


    private String adress;

    @OneToMany(mappedBy = "driver")
    @JsonIgnoreProperties("driver")
    private List<Order> orderList;

    public Driver(String username, String email, String password, String adress) {
        super(username, email, password);
        this.adress = adress;
    }

    public Driver() {

    }
}
