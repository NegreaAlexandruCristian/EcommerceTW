package com.ecommerce.repositories.implementations;

import com.ecommerce.EcommerceApplication;
import com.ecommerce.exceptions.NotAllowedException;
import com.ecommerce.models.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = {EcommerceApplication.class})
@Transactional
class CategoryRepositoryImplementationTest {

    @Autowired
    private CategoryRepositoryImplementation categoryRepository;

    @Test
    public void testAddAndFindCategory() {
        categoryRepository.addCategory("Telefoane");
        Category category = categoryRepository.getCategoryById(4L);
        assertThat(category.getName()).isEqualTo("Telefoane");
    }

    @Test
    public void testAddCategoryException() {
        categoryRepository.addCategory("Telefoane");
        Exception e = assertThrows(NotAllowedException.class, () -> categoryRepository.addCategory("Telefoane"));
        assertThat(e).isInstanceOf(NotAllowedException.class);
    }

}
