package com.recommender;

import com.product.Product;
import com.product.ProductList;
import com.session.UserSession;

import java.util.*;

/**
 * This methods creates movie suggestions based on the currently viewed product.
 * <p>
 * It starts by creating a Key-Value mapping between movie categories and movie ids - e.g.:
 * Action=[1, 6, 7, ... 31, 33, 40], Adventure=[1, 3, 9, ... 29, 37, 40], ...
 * <p>
 * Secondly it creates an intersection Set of categories from the currently viewed product, all viewed products and purchased products.
 * E.g.: A ∩ B + A ∩ C  where A = currently viewed product , B = purchased products and C = viewed products.
 * -----------------------------------------------
 * Session of UserID(1):
 * Current:      [Action, Crime, Drama, Horror, Thriller]
 * Purchased:    [Action, Sci-Fi, Adventure, Horror, Thriller, Fantasy, Comedy]
 * Viewed:       [Film-Noir, Action, Sci-Fi, Adventure, Horror, Thriller, Crime, Fantasy, Romance, Animation, Comedy]
 * Intersection: [Action, Horror, Thriller, Crime]
 * -----------------------------------------------
 * <p>
 * We then (pageSize times) randomly select a category from the intersection and from that category randomly select a movie.
 */
public class RelatedProductSuggester {
    private static Map<String, ArrayList<Integer>> categoryMovieIdMapping;
    private static Set<String> categoryIntersection;
    private static List<String> currentProductCategories;

    public static void createMapOfCategoriesWithAssociatedMovieIds() {
        categoryMovieIdMapping = new TreeMap<>();
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
    }

    public static void setCategoryIntersection(UserSession session) {
        currentProductCategories = session.getProduct().getCategories().stream().toList();
        Set<String> viewedProductsCategories = new HashSet<String>();
        Set<String> purchasedProductsCategories = new HashSet<String>();
        categoryIntersection = new HashSet<>();
        for (Product purchasedProduct : session.getUser().getPurchasedProducts()) {
            purchasedProductsCategories.addAll(purchasedProduct.getCategories());
        }
        for (Product viewedProduct : session.getUser().getViewedProducts()) {
            viewedProductsCategories.addAll(viewedProduct.getCategories());
        }
        currentProductCategories.forEach(category -> {
            purchasedProductsCategories.forEach(purchasedProduct -> {
                if (purchasedProductsCategories.contains(category)) {
                    categoryIntersection.add(category);
                }
            });
            viewedProductsCategories.forEach(viewedProduct -> {
                if (viewedProductsCategories.contains(category)) {
                    categoryIntersection.add(category);
                }
            });
        });
        if (categoryIntersection.size() == 0) { // Edge case: new users with no view/purchase history
            categoryIntersection.addAll(currentProductCategories);
        }
        if (true) { // This block is only for illustrative purposes
            System.out.println(("Session of UserID(" + session.getUser().getId() + "):\n" +
                    "Current:      " + currentProductCategories + "\n" +
                    "Purchased:    " + purchasedProductsCategories + "\n" +
                    "Viewed:       " + viewedProductsCategories + "\n" +
                    "Intersection: " + categoryIntersection));
        }
    }

    public static Product[] getMovieSuggestion(UserSession session, int pageSize) {
        setCategoryIntersection(session);
        Random rand = new Random();
        Product[] suggestedProducts = new Product[pageSize];
        String[] randCategory = new String[pageSize];
        for (int i = 0; i < pageSize; i++) {
            if (categoryIntersection.size() == 0) {
                randCategory[i] = currentProductCategories.toArray()[rand.nextInt(0, currentProductCategories.size())].toString();
            } else if (categoryIntersection.size() == 1) {
                randCategory[i] = categoryIntersection.toArray()[0].toString();
            } else {
                randCategory[i] = categoryIntersection.toArray()[rand.nextInt(0, categoryIntersection.size())].toString();
            }
            suggestedProducts[i] =
                    ProductList.getList().get(categoryMovieIdMapping.get(randCategory[i]).get(
                            rand.nextInt(categoryMovieIdMapping.get(randCategory[i]).size() - 1)
                    ) - 1);
            /*  1. Suggested product should never be equal to current product
             *  2. There must not be two or more identical products in the suggestion list */
            if (Arrays.stream(suggestedProducts).toList().contains(session.getProduct()) || i > 0) {
                while (Arrays.stream(suggestedProducts).toList().contains(session.getProduct()) || (i > 0 && suggestedProducts[i].getId() == suggestedProducts[i - 1].getId())) {
                    suggestedProducts[i] =
                            ProductList.getList().get(categoryMovieIdMapping.get(randCategory[i]).get(
                                    rand.nextInt(categoryMovieIdMapping.get(randCategory[i]).size() - 1)
                            ) - 1);
                }
            }
        }
        return suggestedProducts;
    }
}
