package com.ecommerce.repositories.implementations;

import com.ecommerce.EcommerceApplication;
import com.ecommerce.exceptions.NotFoundException;
import com.ecommerce.models.Category;
import com.ecommerce.models.Product;
import com.ecommerce.repositories.specifications.ProductRepository;
import com.ecommerce.utils.ProductBuilder;
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
                .category(category)
                .build();

        Product product1 = ProductBuilder.builder()
                .name("Laptop")
                .price(4500)
                .sale(20)
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
                            .category(category)
                            .build();

        Product savedProduct = productRepository.save(product);

        assertThat(savedProduct).isEqualTo(product);
    }

    @Test
    @DirtiesContext
    public void testFindProductById() {
        Product product = productRepository.findById(1L);
        assertThat(product.getName()).isEqualTo("Iphone 10");
    }

    @Test
    @DirtiesContext
    public void testFindProductException() {
        Exception e = assertThrows(NotFoundException.class, () -> productRepository.findById(3L));
        assertThat(e).isInstanceOf(NotFoundException.class);
    }

    @Test
    @DirtiesContext
    public void testFindAllProducts() {
        List<Product> products = productRepository.findAll();
        assertThat(products).size().isEqualTo(2);
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
                .category(category)
                .build();
        product.setId(1L);
        productRepository.delete(product);
        Exception e = assertThrows(NotFoundException.class, () -> productRepository.findById(1L));
        assertThat(e).isInstanceOf(NotFoundException.class);
    }
}
