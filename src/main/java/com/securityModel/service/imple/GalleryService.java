package com.securityModel.service.imple;

import com.securityModel.payload.request.GalleryRequest;
import com.securityModel.payload.response.GalleryResponse;

import java.util.HashMap;
import java.util.List;

public interface GalleryService {


    GalleryResponse creategallery(GalleryRequest gallery);
    List<GalleryResponse> allGallery();
    GalleryResponse galleryById(Long id);
    GalleryResponse updategallery(GalleryRequest galleryRequest, Long id);
    HashMap<String, String> deletegallery(Long id);

    GalleryResponse createGallerywithproduct(GalleryRequest galleryRequest, Long id);
    GalleryResponse updateGallerywithproduct(GalleryRequest galleryRequest, Long productId, Long galleryId);
}
