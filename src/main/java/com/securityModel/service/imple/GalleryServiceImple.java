package com.securityModel.service.imple;




import com.securityModel.models.Gallery;
import com.securityModel.models.Product;
import com.securityModel.payload.request.GalleryRequest;
import com.securityModel.payload.response.GalleryResponse;
import com.securityModel.repository.GalleryRepository;
import com.securityModel.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GalleryServiceImple implements GalleryService {


    private final GalleryRepository galleryRepository;
    private final ProductRepository productRepository;

    public GalleryServiceImple(GalleryRepository galleryRepositoy, ProductRepository productRepository) {

        this.galleryRepository = galleryRepositoy;
        this.productRepository = productRepository;
    }

    @Override
    public GalleryResponse creategallery(GalleryRequest gallery) {
        Gallery c = GalleryResponse.toEntity(gallery);
        Gallery savedgallery= galleryRepository.save(c);
        return GalleryResponse.fromEntity(savedgallery);
    }

    @Override
    public List<GalleryResponse> allGallery() {
        return galleryRepository.findAll().stream()
                .map(GalleryResponse::fromEntity)
                .collect(Collectors.toList());

    }

    @Override
    public GalleryResponse galleryById(Long id) {

        return galleryRepository.findById(id)
                .map(GalleryResponse::fromEntity)
                .orElseThrow(() ->new RuntimeException("Gallery not found with this id:" +id));

    }

    @Override
    public GalleryResponse updategallery(GalleryRequest galleryRequest, Long id) {

        Gallery gallery= galleryRepository.findById(id).orElseThrow(()->
                new RuntimeException("Gallery not found with this id:" +id));
        if (gallery != null) {
            Gallery c = GalleryResponse.toEntity(galleryRequest);
            c.setId(id);
            c.setUrl_photo(c.getUrl_photo() == null ? gallery.getUrl_photo() : c.getUrl_photo());
            Gallery savedgallery = galleryRepository.save(c);
            return GalleryResponse.fromEntity(savedgallery);
        }else{
            return null;
        }

    }

    @Override
    public HashMap<String, String> deletegallery(Long id) {

        HashMap message =new HashMap<>();
        Gallery c = galleryRepository.findById(id).orElse(null);
        if (c != null) {
            try {
                galleryRepository.deleteById(id);
                message.put("message", "Gallery deleted successfully");
            }catch (Exception a){
                message.put("message", a.getMessage());
            }
        }else{
            message.put("message", "Gallery not found: " +id);
        }
        return message;

    }

    @Override
    public GalleryResponse createGallerywithproduct(GalleryRequest galleryRequest, Long id) {
        // Récupérer la catégorie à partir de l'ID fourni

        Product product = productRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Product non trouvée avec cet identifiant : " + id));

// Créer une nouvelle sous-catégorie
        Gallery gallery = GalleryResponse.toEntity(galleryRequest);
        gallery.setProduct(product); // Associer la catégorie

// Sauvegarder la sous-catégorie dans la base de données
        Gallery savegallery = galleryRepository.save(gallery);

// Retourner la réponse
        return GalleryResponse.fromEntity(savegallery);
    }

    @Override
    public GalleryResponse updateGallerywithproduct(GalleryRequest galleryRequest, Long productId, Long galleryId) {
        Gallery gallery = galleryRepository.findById(galleryId)
                .orElseThrow(() -> new RuntimeException("Sous-product non trouvée avec cet identifiant : " + galleryId));

        if (productId!= null) {

            Product p = productRepository.findById(productId).orElse(null);
            if (p != null) {
                galleryRequest.setProduct(p);
            } else {
                throw new RuntimeException("categorie not found");
            }
        }
        else{
            galleryRequest.setProduct(galleryRequest.getProduct()==null? gallery.getProduct(): galleryRequest.getProduct());
        }
        if (gallery != null) {
            Gallery gallery1 = GalleryResponse.toEntity(galleryRequest);
            gallery1.setId(galleryId);
            gallery.setUrl_photo(galleryRequest.getUrl_photo());
            gallery.setUrl_photo(galleryRequest.getUrl_photo());
            Gallery savedsub = galleryRepository.save(gallery1);
            return GalleryResponse.fromEntity(savedsub);
        } else {
            throw  new RuntimeException("gallery not found ");
        }
    }
}
