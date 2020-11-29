package com.ecommerce.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PASSWORDS")
public class Password implements Serializable {

    @Id
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user_id;

    @Column(name = "password")
    private String password;

    @Column(name = "old_password")
    private String oldPassword;

    public Password() {
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Password password = (Password) o;

        return user_id != null ? user_id.equals(password.user_id) : password.user_id == null;
    }

    @Override
    public int hashCode() {
        return user_id != null ? user_id.hashCode() : 0;
    }
}
