import countries.Country;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Item {
    String link;        //format https://www.ikea.com/ua/uk/p/-20011408
    String baseCreator = "https://www.ikea.com/";
    TreeMap<Double, InfoBook> map = new TreeMap<>();  //contains price (double) and country + base price
    ArrayList<String> notAvailable = new ArrayList<>(); //country name added here when article not found here

    public void check(Country country, String article) {
        link = baseCreator + country.id + "/" + country.lang + "/p/-" + article;
        try {
            Document doc = Jsoup.connect(link).execute().parse();

            //Price
            //Rating
            InfoBook infoBook = new InfoBook(country, new Price(doc, country), new Rating(doc));

            map.put(infoBook.getPrice(), infoBook);
        } catch (HttpStatusException e) {
            notAvailable.add(country.countryName);
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException " + e.getLocalizedMessage());
        } catch (Exception ignored) {}
    }

    public void writeResult() {
        //price message    country (original price and symbol) stars   score   (qty)
        //179   грн in      Ukraine (179.0 ₴)                   ★★★★    4.8     (233)

        for (Double price : map.keySet()) {
            InfoBook infoBook = map.get(price);
            infoBook.printInfo();
        }

        if (notAvailable.size() > 0) System.out.println("Product is not available in " + notAvailable.stream().map(Object::toString).collect(Collectors.joining(", ")));

    }
}
