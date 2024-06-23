package ara.labo.auditing.service;

import ara.labo.auditing.entity.Product;
import ara.labo.auditing.model.ProductSaveModel;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product addProduct(ProductSaveModel productToSave);

    List<Product> getProducts();

    Product updateProduct(Long productId, ProductSaveModel productToUpdate);

    void deleteProduct(Long productId);

    Optional<Product> getProduct(Long productId);
}
