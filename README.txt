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

----------------------------------------------------------------------------------------
Caseopgave

Produkt (film) anbefalinger:

Din arbejdsgiver har netop lanceret en ny onlinebutik for deres produkter (film), og de vil nu implementere en "anbefalet produkt" -funktion til kunderne, når de gennemser kataloget.
I det eksisterende system har webbutikken sporet, hvilke varer en given bruger kigger på, og hvilke produkter der er købt af brugeren (Users.txt).

For hver kunde (Users.txt):

    Et navn
    En liste over viste produkter
    En liste over købte produkter


Systemet har følgende for hvert produkt (Products.txt)

    Et navn
    En liste over søgeord (genre)
    En gennemsnitlig brugeranmeldelse (vurdering 0-5)
    En pris

Din løsning skal læse de relevante data fra de leverede filer for at hjælpe med at komme med anbefalinger til de kunder, der besøger webstedet.

1. Brug de eksisterende data til at skrive et system, der opretter en liste over "seneste populære produkter" (høj indkøbsrate og/eller høj brugeranmeldelse).

Du kan bestemme præcis, hvor mange produkter der skal vises, men 2 til 3 er et godt udgangspunkt.

2. For det andet, skal du oprette en løsning til at anbefale individuelle produkter til en brugerbase på deres aktuelle sessionsdata. CurrentUserSession.txt angiver, hvilket produkt brugeren i øjeblikket kigger på.

Tænk på "ofte købt sammen med"-funktionen på Amazon eller anbefalinger efter at have set en film på Netflix.

(Brugere, der kan lide produkter fra en given genre, vil normalt lide flere produkter fra den samme genre.)

I begge dele behøver systemet kun at være en simpel tekstgrænseflade (console/terminal). Det skal læse fra filerne og vise de relevante output for hver bruger i CurrentUserSession.txt.

Du behøver ikke at skrive nogen webstedskode eller brugergrænseflader.


Du har fået fire filer: (Alle filer findes i zip-filen "Movie product data")

    Products.txt
    Users.txt
    CurrentUserSession.txt
     README.txt (beskriver andre filers opbygning)

Bemærk: Resultaterne fra denne løsning er subjektive. Der er ikke et "korrekt" svar.

Løsninger vil blive individuelt evalueret for præstation, relevans af forslag samt pænhed og professionalisme af kildekoden.