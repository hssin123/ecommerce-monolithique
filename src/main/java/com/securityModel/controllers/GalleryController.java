package com.securityModel.controllers;



import com.securityModel.payload.request.GalleryRequest;
import com.securityModel.payload.response.GalleryResponse;
import com.securityModel.service.imple.GalleryService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/Gallery")
public class GalleryController {

    private final GalleryService galleryService;

    public GalleryController(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @PostMapping("/save")
    public GalleryResponse addGallery(@RequestBody GalleryRequest galleryRequest) {
        return galleryService.creategallery(galleryRequest);
    }

    @GetMapping("/all")
    public List<GalleryResponse> AllGallery() {
        return galleryService.allGallery();
    }

    @GetMapping("/getone/{id}")
    public GalleryResponse gallerybyid(@PathVariable Long id) {
        return galleryService.galleryById(id);
    }

    @PutMapping("/update/{id}")
    public GalleryResponse updategallery(@RequestBody GalleryRequest galleryRequest, @PathVariable Long id) {
        return galleryService.updategallery(galleryRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public HashMap<String, String> deletegallery(@PathVariable Long id) {
        return galleryService.deletegallery(id);
    }

    @PostMapping("/savec/{id_prod}")
    public GalleryResponse addGalleryDEProduct(@RequestBody GalleryRequest galleryRequest, @PathVariable("id_prod") Long id) {
        return  galleryService.createGallerywithproduct(galleryRequest, id);
    }


    @PutMapping("/updatee/{id_subprod}/{id_prod}")
    public GalleryResponse updateGalleryDEProduct(@RequestBody GalleryRequest galleryRequest, @PathVariable(value = "id_prod",required = false) Long id_prod, @PathVariable("id_subprod") Long id_subprod) {
        return  galleryService.updateGallerywithproduct(galleryRequest, id_subprod, id_prod);
    }
}
