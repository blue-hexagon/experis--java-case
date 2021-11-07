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
 * Secondly it creates an intersection Set of categories from the currently viewed product, all viewed products and purchased products e.g.:
 * A ∩ B + A ∩ C
 * -----------------------------------------------
 * Session of UserID(1):
 * Current:      [Action, Crime, Drama, Horror, Thriller]
 * Purchased:    [Action, Sci-Fi, Adventure, Horror, Thriller, Fantasy, Comedy]
 * Viewed:       [Film-Noir, Action, Sci-Fi, Adventure, Horror, Thriller, Crime, Fantasy, Romance, Animation, Comedy]
 * Intersection: [Action, Horror, Thriller, Crime]
 * -----------------------------------------------
 * <p>
 * We then have the users most favorite category which is related to the viewed product, and from the
 * Categories-MovieIds mapping selects movies of that category.
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
//        categoryMovieIdMapping = sortCategoryMapValuesById(categoryMovieIdMapping);
    }

    public static void getCategoryIntersection(UserSession session) {
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
        // A ∩ B + A ∩ C, where A = currently viewed product
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
        if (categoryIntersection.size() == 0) { // For the edge case
            categoryIntersection.addAll(currentProductCategories);
        }
        System.out.println(("Session of UserID(" + session.getUser().getId() + "):\n" +
                "Current:      " + currentProductCategories + "\n" +
                "Purchased:    " + purchasedProductsCategories + "\n" +
                "Viewed:       " + viewedProductsCategories + "\n" +
                "Intersection: " + categoryIntersection));

    }

    public static Product[] getMovieSuggestion(UserSession session, int pageSize) {
        getCategoryIntersection(session);
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
            if (i == 0) {
                suggestedProducts[i] = ProductList.getList().get(categoryMovieIdMapping.get(randCategory[i]).get(
                        rand.nextInt(categoryMovieIdMapping.get(randCategory[i]).size() - 1)
                )-1);

            } else {
                suggestedProducts[i] = ProductList.getList().get(categoryMovieIdMapping.get(randCategory[i]).get(
                        rand.nextInt(categoryMovieIdMapping.get(randCategory[i]).size() - 1)
                )-1);
                while (suggestedProducts[i].getId() == suggestedProducts[i - 1].getId()) {
                    suggestedProducts[i] = ProductList.getList().get(categoryMovieIdMapping.get(randCategory[i]).get(
                            rand.nextInt(categoryMovieIdMapping.get(randCategory[i]).size() - 1)
                    ));
                }
            }
            System.out.println(suggestedProducts[i]);
        }
        System.out.println();
        return suggestedProducts;
    }
}
