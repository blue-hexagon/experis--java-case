package com.recommender;

import com.product.Product;
import com.user.User;

import java.util.*;

public class HotProductFinder {
    /**
     * This class has the task of finding the most sold products.
     * <p>
     * Traverses each users purchases and generates a K,V pair of purchased movies
     * and how many times the movies has been bought by all users combined.
     * <p>
     * K = ProdutID, V = number of purchases
     *
     * @return Map<K, V>
     */
    public static Map<Integer, Integer> GenerateOftenBoughtProductsList(ArrayList<User> users, int pageSize) {
        Map<Integer, Integer> moviePurchaseMap = new HashMap<Integer, Integer>();
        for (User user : users) {
            for (Product product : user.getPurchasedProducts()) {
                if (!moviePurchaseMap.containsKey(product.getId())) {
                    moviePurchaseMap.put(product.getId(), 1);
                } else {
                    moviePurchaseMap.put(product.getId(), moviePurchaseMap.get(product.getId()) + 1);
                }
            }
        }
        return valueSort(moviePurchaseMap);
    }

    public static <K, V extends Comparable<V>> Map<K, V> valueSort(final Map<K, V> map) {
        Comparator<K> valueComparator = (k1, k2) -> {
            int comp = map.get(k1).compareTo(map.get(k2));
            if (comp == 0)
                return 1;
            else
                return comp;
        };
        Map<K, V> sorted = new TreeMap<K, V>(valueComparator).descendingMap();
        sorted.putAll(map);
        return sorted;
    }
}
