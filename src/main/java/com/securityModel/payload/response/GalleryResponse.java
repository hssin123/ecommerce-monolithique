package com.securityModel.payload.response;




import com.securityModel.models.Gallery;
import com.securityModel.models.Product;
import com.securityModel.payload.request.GalleryRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class GalleryResponse {

    private Long id;
    private String url_photo;
    private Product products;

    public static GalleryResponse fromEntity(Gallery gallery) {
        GalleryResponse subGalleryResponse = new GalleryResponse();
        BeanUtils.copyProperties(gallery, subGalleryResponse);

        return subGalleryResponse;
    }

    public static Gallery toEntity(GalleryRequest galleryRequest) {
        Gallery s = new Gallery();
        BeanUtils.copyProperties(galleryRequest, s);
        return s;
    }
}
