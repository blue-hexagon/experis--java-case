package com.recommender;

import com.product.Product;
import com.product.ProductList;
import com.session.UserSession;

import java.util.*;

/**
 * This methods creates movie suggestions based on the currently viewed product.
 * <p>
 * It starts by creating a Key-Value mapping between movie categories and movie ids - e.g. Action=[movieId23,movieId8,movieId1]
 * <p>
 * Secondly it creates an intersection Set of categories from the currently viewed product and products the user has purchased.
 * <p>
 * We then have the users most favorite category which is related to the viewed product, and from the
 * Categories-MovieIds mapping selects the N most popular movies of that category based on movie ratings.
 */
public class ProductSuggester {
    public static void createMapOfCategoriesWithAssociatedMovieIds() {
        Map<String, ArrayList<Integer>> categoryMovieIdMapping = new TreeMap<>();
        ProductList.getList().forEach(product -> {
            product.getCategories().forEach(category -> {
                if (!categoryMovieIdMapping.containsKey(category)) {
                    categoryMovieIdMapping.put(category, new ArrayList<>() {{
                        add(product.getId());
                    }});
                } else {
                    categoryMovieIdMapping.get(category).add(product.getId());
                }
            });
        });

        System.out.println(categoryMovieIdMapping);
    }

    public static Set<String> getCategoryIntersectionFromViewedProductAndPurchasedProducts(UserSession session, int pageSize) {
        List<String> viewedProductCategories = session.getProduct().getCategories().stream().toList();
        Set<String> purchasedProductCategories = new HashSet<String>();
        Set<String> categoryIntersection = new HashSet<>();
        for (Product purchasedProduct : session.getUser().getPurchasedProducts()) {
            purchasedProductCategories.addAll(purchasedProduct.getCategories());
        }

        viewedProductCategories.forEach(category -> {
            purchasedProductCategories.forEach(product -> {
                if (purchasedProductCategories.contains(category)) {
                    categoryIntersection.add(category);
                }
            });
        });
        System.out.println(("Session of UserID(" + session.getUser().getId() + "):\n" +
                "Viewing:      " + viewedProductCategories + "\n" +
                "Purchased:    " + purchasedProductCategories + "\n" +
                "Intersection: " + categoryIntersection + "\n"));
        return categoryIntersection;
    }

    public static <K, V> Map<K, ArrayList<V>> sortIdsByRating(final Map<K, ArrayList<V>> map) {
        Comparator<K> highestRatingComparator = (k1, k2) -> {
            System.out.println(k1);
            int comp = Float.compare(
                    ProductList.getList().get((Integer) k1 - 1).getRating(),
                    ProductList.getList().get((Integer) k2 - 1).getRating()
            );
            if (comp == 0)
                return 1;
            else
                return comp;
        };
        Map<K, ArrayList<V>> sorted = new TreeMap<K, ArrayList<V>>(highestRatingComparator).descendingMap();
        sorted.putAll(map);
        System.out.println(sorted);
        return sorted;
    }
}
