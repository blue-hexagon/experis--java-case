---Movie example---

Products.txt
------------
id, name, year, keyword 1, keyword 2, keyword 3, keyword 4, keyword 5, rating, price

Users.txt
---------
id, name, viewed (products seperated by ;), purchased (products seperated by ;)

CurrentUserSessions.txt
-----------------------
userid, productid

String basePath = new File("").getAbsolutePath();
//        List<File> csvDataFiles = Arrays.asList(new File("src/com/data/CurrentUserSession.txt"), new File("src/com/data/Users.txt"));
//        productReader.createProductList(new File("src/com/data/Products.txt"));
