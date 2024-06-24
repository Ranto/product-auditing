package ara.labo.auditing.ws.mapper;

import ara.labo.auditing.entity.Product;
import ara.labo.auditing.ws.dto.ProductDto;

public final class ProductMapper {

    public static ProductDto productToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        return productDto;
    }
}
