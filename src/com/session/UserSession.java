package com.session;

import com.product.Product;
import com.user.User;

import java.text.MessageFormat;

public class UserSession {
    private User user;
    private Product product;

    public UserSession() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return MessageFormat.format("Session'{'userId={0}, productId={1}'}'", user, product);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}
