package com.securityModel.payload.request;


import com.securityModel.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class GalleryRequest {

    private Long id;
    private String url_photo;
    private Product product;

    public void setProduct(Product product) {
        this.product = product;
    }
}
