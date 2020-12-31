package com.ecommerce.repositories.implementations;

import com.ecommerce.EcommerceApplication;
import com.ecommerce.models.Category;
import com.ecommerce.models.Product;
import com.ecommerce.models.UserWishlist;
import com.ecommerce.repositories.specifications.ProductRepository;
import com.ecommerce.repositories.specifications.UserWishlistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static com.ecommerce.models.CategoryTypes.ELECTROCASNICE;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {EcommerceApplication.class})
@Transactional
public class UserWishlistImplementationTest {

    @Autowired
    private UserWishlistRepository userWishlistRepository;
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
    }

    @Test
    public void testAddToWishlist() {
        userWishlistRepository.addProductToWishlist(1L,1L);
        assertThat(userWishlistRepository.findWishlistItemsByUserId(1L)).size().isEqualTo(1);
    }

    @Test
    public void testFindProduct() {
        userWishlistRepository.addProductToWishlist(1L, 1L);
        Category category = new Category();
        category.setName(ELECTROCASNICE.name());
        Product product = ProductBuilder.builder()
                .name("Iphone X")
                .price(5000)
                .sale(0)
                .category(category)
                .build();
        assertThat(userWishlistRepository.findProductById(1L, 1L)).isEqualTo(product);
    }

    @Test
    public void testFindUserWishlist() {
        userWishlistRepository.addProductToWishlist(1L,1L);
        userWishlistRepository.addProductToWishlist(2L,1L);
        userWishlistRepository.addProductToWishlist(1L,2L);
        Category category = new Category();
        category.setName(ELECTROCASNICE.name());
        Product product = ProductBuilder.builder()
                .name("Iphone 11")
                .price(5000)
                .sale(0)
                .category(category)
                .build();
        productRepository.save(product);
        assertThat(userWishlistRepository.findWishlistItemsByUserId(1L)).size().isEqualTo(2);
    }
    //TODO test rest of the methods;
}

class UserWishListBuilder {
    private Long userId;
    private Long productId;

    public static UserWishListBuilder builder(){
        return new UserWishListBuilder();
    }

    public UserWishListBuilder userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public UserWishListBuilder productId(Long productId) {
        this.productId =  productId;
        return this;
    }

    public UserWishlist build() {
        UserWishlist userWishlist = new UserWishlist();
        userWishlist.setUserId(userId);
        userWishlist.setProductId(productId);
        return userWishlist;
    }

}


