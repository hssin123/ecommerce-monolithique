package com.securityModel.controllers;




import com.securityModel.payload.request.ProductRequest;
import com.securityModel.payload.response.ProductResponse;

import com.securityModel.service.imple.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/Product")
public class ProductController {

    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/save")
    public ProductResponse addProduct(@RequestBody ProductRequest productRequest) {
        return productService.createproduct(productRequest);
    }

    @GetMapping("/all")
    public List<ProductResponse> AllProduct() {
        return productService.allProduct();
    }

    @GetMapping("/getone/{id}")
    public ProductResponse productbyid(@PathVariable Long id) {
        return productService.productById(id);
    }

    @PutMapping("/update/{id}")
    public ProductResponse updateproduct(@RequestBody ProductRequest productRequest, @PathVariable Long id) {
        return productService.updateproduct(productRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public HashMap<String, String> deleteproduct(@PathVariable Long id) {
        return productService.deleteproduct(id);
    }

    @PostMapping("/savesub/{id_sub}")
    public ProductResponse addProductSubCategorie(@RequestBody ProductRequest productRequest, @PathVariable("id_sub") Long id) {
        return  productService.createProductwithsubcategorie(productRequest, id);
    }


    @PostMapping("/savec/{id_prov}")
    public ProductResponse addProductDEProvider(@RequestBody ProductRequest productRequest, @PathVariable("id_prov") Long id) {
        return  productService.createProductwithprovider(productRequest, id);
    }


    @PutMapping("/updatee/{id_subprov}/{id_prov}")
    public ProductResponse updateProductDEProvider(@RequestBody ProductRequest productRequest, @PathVariable(value = "id_prov",required = false) Long id_prov, @PathVariable("id_subprov") Long id_subprov) {
        return  productService.updateProductwithprovider(productRequest, id_subprov, id_prov);
    }
}
