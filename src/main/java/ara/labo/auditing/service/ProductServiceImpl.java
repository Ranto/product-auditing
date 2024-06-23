package ara.labo.auditing.service;

import ara.labo.auditing.entity.Product;
import ara.labo.auditing.model.ProductSaveModel;
import ara.labo.auditing.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product addProduct(ProductSaveModel productToSave) {
        Product product = mapProductSaveModel(productToSave);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long productId, ProductSaveModel productToUpdate) {
        Optional<Product> maybeProduct = getProduct(productId);
        if (maybeProduct.isEmpty()) {
            throw new IllegalArgumentException("Product doesn't exist.");
        }
        Product product = maybeProduct.get();
        product.setName(productToUpdate.name());
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        Optional<Product> maybeProduct = getProduct(productId);
        if (maybeProduct.isEmpty()) {
            throw new IllegalArgumentException("Product doesn't exist.");
        }
        productRepository.deleteById(productId);
    }

    @Override
    public Optional<Product> getProduct(Long productId) {
        return productRepository.findById(productId);
    }

    private Product mapProductSaveModel(ProductSaveModel productSaveModel) {
        Product product = new Product();
        product.setName(productSaveModel.name());
        return product;
    }
}
