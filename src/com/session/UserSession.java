package com.session;

import java.text.MessageFormat;

import com.IReadable;
import com.product.Product;
import com.user.User;

public class UserSession {
    User user;
    Product product;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public UserSession() {

    }

    @Override
    public String toString() {
        return MessageFormat.format("Session'{'userId={0}, productId={1}'}'", user, product);
    }
}
