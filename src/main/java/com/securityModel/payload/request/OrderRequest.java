package com.securityModel.payload.request;


import com.securityModel.models.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class OrderRequest {

    private Long id;
    private String ref;
    private String description;
    private Integer qte_totale;
    private Double price_totale;
    private Boolean state;
    private Client client;

    public void setClient(Client client) {
        this.client = client;
    }
}
