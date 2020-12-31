package com.ecommerce.repositories.implementations;

import com.ecommerce.EcommerceApplication;
import com.ecommerce.exceptions.NotFoundException;
import com.ecommerce.models.Category;
import com.ecommerce.models.Product;
import com.ecommerce.models.Review;
import com.ecommerce.models.User;
import com.ecommerce.repositories.specifications.ProductRepository;
import com.ecommerce.repositories.specifications.ReviewRepository;
import com.ecommerce.repositories.specifications.UserRepository;
import com.ecommerce.utils.ProductBuilder;
import com.ecommerce.utils.ReviewBuilder;
import com.ecommerce.utils.UserBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static com.ecommerce.models.CategoryTypes.ELECTROCASNICE;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {EcommerceApplication.class})
@Transactional
class ReviewRepositoryImplementationTest {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void init() {

        User user = UserBuilder.builder()
                .username("TEAM")
                .role("ADMIN")
                .password("project")
                .build();

        userRepository.save(user);

        Category category = new Category();
        category.setName(ELECTROCASNICE.name());
        Product product = ProductBuilder.builder()
                .name("Iphone 10")
                .price(4300)
                .sale(10)
                .category(category)
                .build();
        productRepository.save(product);

        Review userReview = ReviewBuilder.builder()
                .review(5.0)
                .comment("Romania")
                .localDate(LocalDate.now())
                .build();

        reviewRepository.save(userReview, 1L, 1L);
        System.out.println(reviewRepository.findById(1L));
    }

    @Test
    @DirtiesContext
    void findById() {
        Review userReview = ReviewBuilder.builder()
                .review(3.0)
                .comment("Romania e foarte mare si mandra")
                .localDate(LocalDate.now())
                .build();
        reviewRepository.save(userReview, 1L, 1L);
        assertThat(reviewRepository.findById(2L)).isEqualTo(userReview);
    }


    @Test
    @DirtiesContext
    void findAll() {
        assertThat(reviewRepository.findAll()).size().isEqualTo(1);
        Review userReview = ReviewBuilder.builder()
                .review(3.0)
                .comment("Romania e foarte mare si mandra")
                .localDate(LocalDate.now())
                .build();
        reviewRepository.save(userReview, 1L, 1L);
        assertThat(reviewRepository.findAll()).size().isEqualTo(2);
    }
//
//    @Test
//    void deleteById() {
//    }
//
//    @Test
//    void delete() {
//    }
//
//    @Test
//    void save() {
//    }
}