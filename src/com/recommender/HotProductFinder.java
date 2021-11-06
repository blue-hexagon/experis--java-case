package com.recommender;

import com.product.Product;
import com.product.ProductList;
import com.user.User;

import java.util.*;

public class HotProductFinder {
    public enum ComparatorValue {
        LOWER(-1),
        EQUAL(0),
        GREATER(1);


        private final int value;

        ComparatorValue(int value) {
            this.value = value;
        }
    }


    /**
     * This class has the task of finding the most sold products.
     * <p>
     * Traverses each users purchases and generates a K,V pair of purchased movies: K = ProdutID, V = total number of purchases
     * <p>
     *
     * @return Map<K, V>
     */
    public static Map<Integer, Integer> GenerateOftenBoughtProductsList(ArrayList<User> users, int pageSize) {
        Map<Integer, Integer> moviesPurchasedMap = new HashMap<Integer, Integer>();
        for (User user : users) {
            for (Product product : user.getPurchasedProducts()) {
                if (!moviesPurchasedMap.containsKey(product.getId())) {
                    moviesPurchasedMap.put(product.getId(), 1);
                } else {
                    moviesPurchasedMap.put(product.getId(), moviesPurchasedMap.get(product.getId()) + 1);
                }
            }
        }
//        return highestRated(mostPurchasedSort(moviesPurchasedMap));
        return highestRated(moviesPurchasedMap);
    }

    public static <K, V extends Comparable<V>> Map<K, V> mostPurchasedSort(final Map<K, V> map) {
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

    public static <K extends Comparable<K>, V> Map<K, V> highestRated(final Map<K, V> map) {
        Comparator<K> highestRatingComparator = (k1, k2) -> {
            int comp = Float.compare(
                    ProductList.getProductList().get((Integer) (k1) - 1).getRating(),
                    ProductList.getProductList().get((Integer) (k2) - 1).getRating()
            );
//            System.out.println(
//                    "k1(" + k1 +") " +ProductList.getProductList().get((Integer) (k1)-1).getRating() + ProductList.getProductList().get((Integer) (k1)-1).getTitle() +"\n " +
//                            "k2(" + k2 +") " + ProductList.getProductList().get((Integer) (k2)-1).getRating() + ProductList.getProductList().get((Integer) (k2)-1).getTitle() +"\n"
//            );
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
