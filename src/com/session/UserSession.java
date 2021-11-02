package com.session;

import com.product.Product;
import com.user.User;

public class UserSession {
    User userId;
    Product productId;

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public UserSession() {

    }

    @Override
    public String toString() {
        return "Session{" +
                "userId=" + userId +
                ", productId=" + productId +
                '}';
    }
}
