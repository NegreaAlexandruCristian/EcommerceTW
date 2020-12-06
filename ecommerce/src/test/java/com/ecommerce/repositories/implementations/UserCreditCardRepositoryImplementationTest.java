package com.ecommerce.repositories.implementations;

import com.ecommerce.EcommerceApplication;
import com.ecommerce.exceptions.NotFoundException;
import com.ecommerce.models.User;
import com.ecommerce.models.UserCreditCard;
import com.ecommerce.repositories.specifications.UserCreditCardRepository;
import com.ecommerce.repositories.specifications.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest(classes = {EcommerceApplication.class})
@Transactional
class UserCreditCardRepositoryImplementationTest {

    @Autowired
    private UserCreditCardRepository userCardRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        User user = UserBuilder.builder()
                .username("TEAM")
                .role("ADMIN")
                .password("project")
                .build();

        userRepository.save(user);

        UserCreditCard creditCard = getCreditCard();
        userCardRepository.save(creditCard, 2L);
    }

    @Test
    @DirtiesContext
    public void testSaveCard() {
        UserCreditCard creditCard = UserCreditCardBuilder.builder()
                                .cardHolder("Test Admin")
                                .cvv(444)
                                .expirationMonth(10)
                                .expirationYear(2023)
                                .cardNumber("4716980678542457")
                                .build();
        userCardRepository.save(creditCard, 2L); //TEAM user id
        User user = userRepository.findById(2L);
        //first card is the one inserted above in @BeforeEach
        assertThat(user.getUserCreditCards().get(1)).isEqualTo(creditCard);
    }

    @Test
    @DirtiesContext
    public void testFindCardById() {
        UserCreditCard creditCard = UserCreditCardBuilder.builder()
                .cardHolder("TEST Admin")
                .cvv(444)
                .expirationMonth(10)
                .expirationYear(2023)
                .cardNumber("4716980678542457")
                .build();
        userCardRepository.save(creditCard, 2L);
        UserCreditCard foundCard = userCardRepository.findById(2L);
        assertThat(creditCard).isEqualTo(foundCard);
    }

    @Test
    @DirtiesContext
    public void testFindCardException() {
        Exception e = assertThrows(NotFoundException.class, () -> userCardRepository.findById(2L));
        assertThat(e).isInstanceOf(NotFoundException.class);
    }

    @Test
    @DirtiesContext
    public void testExistCreditCard() {
        boolean exists = userCardRepository.existsById(1L);
        boolean notExist = userCardRepository.existsById(2L);

        assertThat(exists).isEqualTo(true);
        assertThat(notExist).isEqualTo(false);
    }


    @Test
    @DirtiesContext
    public void testDeleteCard() {
        UserCreditCard creditCard = getCreditCard();
        creditCard.setId(1L);
        userCardRepository.delete(2L, 1L);

        boolean exists = userCardRepository.existsById(1L);
        assertThat(exists).isEqualTo(false);
    }

    @Test
    @DirtiesContext
    public void testFindUserCreditCards() {
        UserCreditCard creditCard = UserCreditCardBuilder.builder()
                .cardHolder("TEST Admin")
                .cvv(444)
                .expirationMonth(10)
                .expirationYear(2023)
                .cardNumber("4716980678542457")
                .build();
        userCardRepository.save(creditCard, 2L);
        UserCreditCard secondaryCard = getCreditCard();

        assertThat(userCardRepository.findUserCreditCards(2L)).contains(creditCard, secondaryCard);
    }

    private UserCreditCard getCreditCard() {
        return UserCreditCardBuilder.builder()
                .cardHolder("TEST Admin")
                .cvv(444)
                .expirationMonth(10)
                .expirationYear(2023)
                .cardNumber("4716980678542457")
                .build();
    }

}

class UserCreditCardBuilder {
    private String cardNumber;
    private String cardHolder;
    private Integer expirationMonth;
    private Integer expirationYear;
    private Integer cvv;

    public static UserCreditCardBuilder builder(){
        return new UserCreditCardBuilder();
    }

    public UserCreditCardBuilder cardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public UserCreditCardBuilder cardHolder(String cardHolder){
        this.cardHolder = cardHolder;
        return this;
    }

    public UserCreditCardBuilder expirationMonth(Integer expirationMonth) {
        this.expirationMonth = expirationMonth;
        return this;
    }

    public UserCreditCardBuilder expirationYear(Integer expirationYear) {
        this.expirationYear = expirationYear;
        return this;
    }

    public UserCreditCardBuilder cvv(Integer cvv) {
        this.cvv = cvv;
        return this;
    }

    public UserCreditCard build() {
        UserCreditCard creditCard = new UserCreditCard();
        creditCard.setCardHolder(cardHolder);
        creditCard.setCardNumber(cardNumber);
        creditCard.setCvv(cvv);
        creditCard.setExpirationMonth(expirationMonth);
        creditCard.setExpirationYear(expirationYear);
        return creditCard;
    }
}
