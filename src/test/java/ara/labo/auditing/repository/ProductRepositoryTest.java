package ara.labo.auditing.repository;

import ara.labo.auditing.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

@DataJpaTest
class ProductRepositoryTest {
    public static final String TEST_PRODUCT_1 = "Test product 1";
    public static final String TEST_UPDATE = "TEST UPDATE";
    @Autowired
    ProductRepository underTest;

    @Test
    void testSave() throws Exception {
        long init = underTest.count();
        Product product = new Product();
        product.setName(TEST_PRODUCT_1);

        Product save = underTest.saveAndFlush(product);
        Long id = save.getId();
        long after = underTest.count();

        assertThat(after, is(init + 1));

        Optional<Product> maybeProduct = underTest.findById(id);
        assertThat(maybeProduct.isPresent(), is(true));

        Product productSaved = maybeProduct.get();
        assertThat(productSaved.getName(), is(TEST_PRODUCT_1));
        assertThat(productSaved.getCreationDate(), notNullValue());
        // assertThat(productSaved.getLastModifiedDate(), nullValue());

        Thread.sleep(2000);

        productSaved.setName(TEST_UPDATE);
        underTest.saveAndFlush(productSaved);
        maybeProduct = underTest.findById(id);
        assertThat(maybeProduct.isPresent(), is(true));
        assertThat(productSaved.getCreationDate(), not(equalTo(productSaved.getLastModifiedDate())));
    }
}