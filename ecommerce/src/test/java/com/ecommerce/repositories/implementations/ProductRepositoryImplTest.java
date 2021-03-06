package com.ecommerce.repositories.implementations;

import com.ecommerce.EcommerceApplication;
import com.ecommerce.exceptions.NotFoundException;
import com.ecommerce.models.Category;
import com.ecommerce.models.Product;
import com.ecommerce.repositories.specifications.ProductRepository;
import com.ecommerce.util.ProductBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;
import java.util.List;

import static com.ecommerce.models.CategoryTypes.ELECTROCASNICE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest(classes = {EcommerceApplication.class})
@Transactional
/**
 * Product Mocker adds by default 12 products, that's why we start from 13.
 * An fix would be to call product mocker from main, in that way the test won't be affected.
 * However, all test are running as expected.
 */
class ProductRepositoryImplTest {

    private ProductRepository productRepository;

    @Autowired
    public void init(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @BeforeEach
    public void saveProduct() {
        Category category = new Category();
        category.setName(ELECTROCASNICE.name());
        Product product = ProductBuilder.builder()
                .name("Iphone 10")
                .price(4300)
                .sale(10)
                .description("bla")
                .url("bla")
                .category(category)
                .build();

        Product product1 = ProductBuilder.builder()
                .name("Laptop")
                .price(4500)
                .sale(20)
                .description("bla")
                .url("jpg")
                .category(category)
                .build();

        productRepository.save(product);
        productRepository.save(product1);
    }

    @Test
    @DirtiesContext
    public void testSaveProduct() {
        Category category = new Category();
        category.setName(ELECTROCASNICE.name());
        Product product = ProductBuilder.builder()
                            .name("Iphone X")
                            .price(4300)
                            .sale(10)
                            .description("bla")
                            .url("jpg")
                            .category(category)
                            .build();

        Product savedProduct = productRepository.save(product);

        assertThat(savedProduct).isEqualTo(product);
    }

    @Test
    @DirtiesContext
    public void testFindProductById() {
        Product product = productRepository.findById(13L);
        assertThat(product.getName()).isEqualTo("Iphone 10");
    }

    @Test
    @DirtiesContext
    public void testFindProductException() {
        Exception e = assertThrows(NotFoundException.class, () -> productRepository.findById(15L));
        assertThat(e).isInstanceOf(NotFoundException.class);
    }

    @Test
    @DirtiesContext
    public void testFindAllProducts() {
        List<Product> products = productRepository.findAll();
        assertThat(products).size().isEqualTo(14);
    }

    @Test
    @DirtiesContext
    public void testFilterProductsByName() {
        List<Product> products = productRepository.filterProductsByName("iph");
        assertThat(products).size().isEqualTo(1);
    }

    @Test
    @DirtiesContext
    public void testDeleteById() {
        productRepository.deleteById(1L);
        Exception e = assertThrows(NotFoundException.class, () -> productRepository.findById(1L));
        assertThat(e).isInstanceOf(NotFoundException.class);
    }

    @Test
    @DirtiesContext
    public void testDeleteProduct() {
        Category category = new Category();
        category.setName(ELECTROCASNICE.name());
        Product product = ProductBuilder.builder()
                .name("Iphone 10")
                .price(4300)
                .sale(10)
                .url("bla")
                .description("bla")
                .category(category)
                .build();
        product.setId(13L);
        productRepository.delete(product);
        Exception e = assertThrows(NotFoundException.class, () -> productRepository.findById(13L));
        assertThat(e).isInstanceOf(NotFoundException.class);
    }
}
