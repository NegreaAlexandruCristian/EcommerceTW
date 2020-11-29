package com.ecommerce.models;

import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "user_credit_cards")
public class UserCreditCard implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @CreditCardNumber(message = "Not a valid credit card number")
    @Column(name = "card_number")
    private String cardNumber;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z\\s]*$")
    @Column(name = "card_holder")
    private String cardHolder;

    @NotNull
    @Min(1)
    @Max(12)
    @Column(name = "expiration_month")
    private Integer expirationMonth;

    @NotNull
    @Min(2020)
    @Max(2030)
    @Column(name = "expiration_year")
    private Integer expirationYear;

    @NotNull
    @Min(100)
    @Max(999)
    @Column(name = "cvv")
    private Integer cvv;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "userCreditCards")
    private User user;

    public UserCreditCard() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public int getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(int expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public int getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(int expirationYear) {
        this.expirationYear = expirationYear;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
