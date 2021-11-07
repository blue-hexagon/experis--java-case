package com;

import com.product.Product;
import com.product.ProductList;
import com.recommender.PopularProductFinder;
import com.recommender.RelatedProductSuggester;
import com.session.UserSession;
import com.session.UserSessionList;
import com.user.User;
import com.user.UserList;

import java.io.FileNotFoundException;

/**
 * Program Overview
 * <p>
 * Product, User and UserSession contain templates for each object along with getters and setters.
 * Each of the objects: Product, User and UserSession contains an enum FieldSpanRecord that tells the ProductList,
 * UserList and UserSessionList how the CSV files are put together.
 * <p>
 * The HotProductFinder retrieves products with either a high rating or high purchase score. The sample size is set by
 * the constant PAGE_SIZE in the Main class.
 * <p>
 * The ProductSuggester retrieves results based on the categories of the current product viewed in the webshop.
 * It runs through all user bought products and find the most often bought movie category that is within the set of the
 * currently viewed product in the usersession and then randomly selects movies within that category from the product list.
 * <p>
 * DEBUG in the main class can be set to true to print out all objects instantiated from the CSV files.
 */

public class Main {
    private static final int PAGE_SIZE = 3;
    private static final IReadable[] readables = new IReadable[]{new ProductList(), new UserList(), new UserSessionList()};

    public static void main(String[] args) {
        try {
            for (IReadable readable : readables) {
                readable.read();
                if (false) {
                    dumpParsedCSV();
                    System.exit(0);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PopularProductFinder.createMapOfMovieIdsAndHowManyTimesTheyHaveBeenBought(UserList.getList());

        RelatedProductSuggester.createMapOfCategoriesWithAssociatedMovieIds();

        System.out.println("High purchase number products: ");
        for (Product oftenBoughtProduct : PopularProductFinder.getMostPurchasedMovies(PAGE_SIZE)) {
            System.out.println(oftenBoughtProduct);
        }

        System.out.println("\nHigh rated products: ");
        for (Product highRatedProduct : PopularProductFinder.getHighestRatedMovies(PAGE_SIZE)) {
            System.out.println(highRatedProduct);
        }
        System.out.println();
        UserSessionList.getList().forEach(userSession -> {
            RelatedProductSuggester.getMovieSuggestion(userSession, PAGE_SIZE);
        });
    }

    private static void dumpParsedCSV() {
        System.out.printf("%-15s %-30s %-15s%n", "---", "Printing Products", "---");
        for (Product product : ProductList.getList()) {
            System.out.println(product.toString());
        }
        System.out.printf("%n%n%-15s %-30s %-15s%n", "---", "Printing Users", "---");
        for (User user : UserList.getList()) {
            System.out.println(user.toString());
        }

        System.out.printf("%n%n%-15s %-30s %-15s%n", "---", "Printing Sessions", "---");
        for (UserSession userSession : UserSessionList.getList()) {
            System.out.println(userSession.toString());
        }
    }

}
