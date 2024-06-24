package ara.labo.auditing.ws;

import ara.labo.auditing.entity.Product;
import ara.labo.auditing.model.ProductSaveModel;
import ara.labo.auditing.service.ProductService;
import ara.labo.auditing.ws.dto.ProductDto;
import ara.labo.auditing.ws.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductSaveModel product) {
        Product newProduct = productService.addProduct(product);
        ProductDto productDto = ProductMapper.productToProductDto(newProduct);
        return ResponseEntity.ok(productDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductSaveModel model, @PathVariable("id") Long id) {
        Product updatedProduct = productService.updateProduct(id, model);
        ProductDto productDto = ProductMapper.productToProductDto(updatedProduct);
        return ResponseEntity.ok(productDto);
    }
}
