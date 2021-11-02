package com.recommender;

import com.product.Product;
import com.user.User;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Returns an ordered list of hot products
 * <p>
 * This class has the task of finding the latests hot products.
 */
public class SubsetFinder {
    /**
     * Get the most bought movie ids.
     *
     * @param users
     * @return
     */
    public static ArrayList<Integer> FindUserPurchasesUnionSet(ArrayList<User> users) {
        ArrayList<Integer> movieIds = new ArrayList<>();
        for (User user : users) {
            for (Product product : user.getPurchasedProducts()) {
                movieIds.add(product.getId());
            }
        }
        System.out.println(movieIds);
        return movieIds;
    }
}
