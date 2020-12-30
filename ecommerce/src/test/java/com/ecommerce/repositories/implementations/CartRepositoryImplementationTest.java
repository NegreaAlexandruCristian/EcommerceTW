package com.ecommerce.repositories.implementations;

import com.ecommerce.EcommerceApplication;
import com.ecommerce.models.CartItem;
import com.ecommerce.repositories.specifications.CartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = {EcommerceApplication.class})
@Transactional
public class CartRepositoryImplementationTest {

    @Autowired
    private CartRepository cartRepository;

    @Test
    public void addProductToCardTest() {
        CartItem cartItem = cartRepository.addProductToCart(1L, 1L);
        CartItem copyCart = CartItemsBuilder.builder()
                .userId(1L)
                .productId(1L)
                .quantity(1L)
                .build();

        assertThat(cartItem).isEqualTo(copyCart);
    }

    @Test
    public void addProductIncreaseQuantity() {
        addCartItem(1L, 1L);
        CartItem cartItem = cartRepository.addProductToCart(1L, 1L);
        CartItem copyCart = CartItemsBuilder.builder()
                .userId(1L)
                .productId(1L)
                .quantity(2L)
                .build();

        assertThat(cartItem).isEqualTo(copyCart);
    }

    @Test
    public void testFindItem() {
        //GIVEN
        addCartItem(1L, 1L);
        addCartItem(1L, 2L);
        addCartItem(2L, 2L);
        //WHEN
        List<CartItem> cartItems = cartRepository.findCartItemsByUserId(1L);
        //THEN
        CartItem copyCart1 = CartItemsBuilder.builder()
                .userId(1L)
                .productId(1L)
                .quantity(1L)
                .build();

        CartItem copyCart2 = CartItemsBuilder.builder()
                .userId(1L)
                .productId(1L)
                .quantity(1L)
                .build();

        assertThat(cartItems).contains(copyCart1, copyCart2);
    }

    @Test
    public void testDeleteCartItem() {
        addCartItem(1L, 1L);
        cartRepository.deleteCartItem(1L, 1L);

        List<CartItem> cartItems = cartRepository.findCartItemsByUserId(1L);

        assertThat(cartItems).isEmpty();
    }

    @Test
    public void testDeleteCartItemsForUser() {
        addCartItem(1L, 1L);
        addCartItem(1L, 2L);
        addCartItem(2L, 2L);
        cartRepository.deleteCartItems(1L);

        List<CartItem> cartItems = cartRepository.findCartItemsByUserId(1L);

        assertThat(cartItems).isEmpty();
    }

    private void addCartItem(Long userId, Long productId) {
        cartRepository.addProductToCart(userId, productId);
    }

}

class CartItemsBuilder {
    private long userId;
    private long productId;
    private long quantity;

    public static CartItemsBuilder builder() {
        return new CartItemsBuilder();
    }

    public CartItemsBuilder userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public CartItemsBuilder productId(Long productId) {
        this.productId = productId;
        return this;
    }

    public CartItemsBuilder quantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public CartItem build() {
        CartItem cartItem = new CartItem();
        cartItem.setUserId(userId);
        cartItem.setProductId(productId);
        cartItem.setQuantity(quantity);
        return cartItem;
    }
}
