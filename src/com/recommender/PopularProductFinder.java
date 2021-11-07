package com.recommender;

import com.product.Product;
import com.product.ProductList;
import com.user.User;

import java.util.*;

public class PopularProductFinder {
    private static final Map<Integer, Integer> movieIdsToPurchasesMapping = new HashMap<Integer, Integer>();

    /**
     * This class has the task of finding the most sold and/or highest rated products.
     * <p>
     * Traverses all users purchases and generates a K,V pair of purchased movies and the number of times they have been bought in total
     * E.g: K = ProdutID, V = total number of purchases
     */

    public static Product[] getHighestRatedMovies(int pageSize) {
        Product[] products = new Product[pageSize];
        List<Integer> movieIds = sortByRating(movieIdsToPurchasesMapping).keySet().stream().limit(pageSize).toList();
        for (int i = 0; i < pageSize; i++) {
            products[i] = ProductList.getList().get(movieIds.get(i) - 1);
        }
        return products;
    }

    public static Product[] getMostPurchasedMovies(int pageSize) {
        Product[] products = new Product[pageSize];
        List<Integer> movieIds = sortByNumberOfTotalPurchases(movieIdsToPurchasesMapping).keySet().stream().limit(pageSize).toList();
        for (int i = 0; i < pageSize; i++) {
            products[i] = ProductList.getList().get(movieIds.get(i) - 1);
        }
        return products;
    }

    public static void createMapOfMovieIdsToNumberOfPurchases(ArrayList<User> users) {
        for (User user : users) {
            for (Product product : user.getPurchasedProducts()) {
                if (!movieIdsToPurchasesMapping.containsKey(product.getId())) {
                    movieIdsToPurchasesMapping.put(product.getId(), 1);
                } else {
                    movieIdsToPurchasesMapping.put(product.getId(), movieIdsToPurchasesMapping.get(product.getId()) + 1);
                }
            }
        }
    }

    public static <K, V extends Comparable<V>> Map<K, V> sortByNumberOfTotalPurchases(final Map<K, V> map) {
        Comparator<K> mostPurchasedComparator = (k1, k2) -> {
            int comp = map.get(k1).compareTo(map.get(k2));
            if (comp == 0)
                return 1;
            else
                return comp;
        };
        Map<K, V> sorted = new TreeMap<K, V>(mostPurchasedComparator).descendingMap();
        sorted.putAll(map);
        return sorted;
    }

    public static <K, V> Map<K, V> sortByRating(final Map<K, V> map) {
        Comparator<K> highestRatingComparator = (k1, k2) -> {
            int comp = Float.compare(
                    ProductList.getList().get((Integer) k1 - 1).getRating(),
                    ProductList.getList().get((Integer) k2 - 1).getRating()
            );
            if (comp == 0)
                return 1;
            else
                return comp;
        };
        Map<K, V> sorted = new TreeMap<K, V>(highestRatingComparator).descendingMap();
        sorted.putAll(map);
        return sorted;
    }
}
