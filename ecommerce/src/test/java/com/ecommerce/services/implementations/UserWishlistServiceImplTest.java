package com.ecommerce.services.implementations;

import com.ecommerce.EcommerceApplication;
import com.ecommerce.exceptions.NotFoundException;
import com.ecommerce.models.Category;
import com.ecommerce.models.Product;
import com.ecommerce.repositories.specifications.ProductRepository;
import com.ecommerce.services.specifications.UserWishlistService;
import com.ecommerce.util.ProductBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;

import static com.ecommerce.models.CategoryTypes.ELECTROCASNICE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest(classes = {EcommerceApplication.class})
@Transactional
class UserWishlistServiceImplTest {

    @Autowired
    private UserWishlistService userWishlistService;
    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void initProduct() {
        Category category = new Category();
        category.setName(ELECTROCASNICE.name());
        Product product = ProductBuilder.builder()
                .name("Iphone X")
                .price(5000)
                .sale(0)
                .category(category)
                .build();
        productRepository.save(product);
        userWishlistService.addProductToWishlist(1L, 1L);
    }

    @Test
    @DirtiesContext
    void TestFindWishlistItemsByUserId() {

        assertThat(userWishlistService.findWishlistItemsByUserId(1L)).size().isEqualTo(1);

        Category category = new Category();
        category.setName(ELECTROCASNICE.name());
        Product product2 = ProductBuilder.builder()
                .name("Iphone 11")
                .price(5000)
                .sale(0)
                .category(category)
                .build();
        productRepository.save(product2);
        userWishlistService.addProductToWishlist(1L, 2L);
        assertThat(userWishlistService.findWishlistItemsByUserId(1L)).size().isEqualTo(2);

    }

    @Test
    @DirtiesContext
    void TestFindProductById() {
        assertThat(userWishlistService.findProductById(1L,1L)).isEqualTo(productRepository.findById(1L));
        Exception e = assertThrows(NotFoundException.class, () -> userWishlistService.findProductById(1L, 2L));
        assertThat(e).isInstanceOf(NotFoundException.class);

        Category category = new Category();
        category.setName(ELECTROCASNICE.name());
        Product product = ProductBuilder.builder()
                .name("Iphone 11")
                .price(5000)
                .sale(0)
                .category(category)
                .build();
        productRepository.save(product);
        userWishlistService.addProductToWishlist(1L, 2L);
        assertThat(userWishlistService.findProductById(1L,2L)).isEqualTo(productRepository.findById(2L));


    }

    @Test
    @DirtiesContext
    void TestAddProductToWishlist() {

        Category category = new Category();
        category.setName(ELECTROCASNICE.name());
        Product product = ProductBuilder.builder()
                .name("Iphone 11")
                .price(5000)
                .sale(0)
                .category(category)
                .build();
        productRepository.save(product);
        userWishlistService.addProductToWishlist(1L, 2L);
        assertThat(userWishlistService.findProductById(1L,2L)).isEqualTo(product);
    }

    @Test
    @DirtiesContext
    void TestDeleteWishlistItems() {

        Category category = new Category();
        category.setName(ELECTROCASNICE.name());
        Product product = ProductBuilder.builder()
                .name("Iphone 11")
                .price(5000)
                .sale(0)
                .category(category)
                .build();
        productRepository.save(product);
        userWishlistService.addProductToWishlist(1L, 2L);
        userWishlistService.deleteWishlistItems(1L);
        assertThat(userWishlistService.findWishlistItemsByUserId(1L)).size().isEqualTo(0);

        userWishlistService.addProductToWishlist(1L, 2L);
        assertThat(userWishlistService.findWishlistItemsByUserId(1L)).size().isEqualTo(1);
        assertThat(userWishlistService.findWishlistItemsByUserId(2L)).size().isEqualTo(0);
    }

    @Test
    @DirtiesContext
    void TestDeleteWishlistItem() {
        assertThat(userWishlistService.findWishlistItemsByUserId(1L)).size().isEqualTo(1);

        userWishlistService.deleteWishlistItem(1L, 1L);
        assertThat(userWishlistService.findWishlistItemsByUserId(1L)).size().isEqualTo(0);

        Category category = new Category();
        category.setName(ELECTROCASNICE.name());
        Product product = ProductBuilder.builder()
                .name("Iphone 11")
                .price(5000)
                .sale(0)
                .category(category)
                .build();
        productRepository.save(product);
        userWishlistService.addProductToWishlist(1L, 2L);
        assertThat(userWishlistService.findWishlistItemsByUserId(1L)).size().isEqualTo(1);
    }

}

