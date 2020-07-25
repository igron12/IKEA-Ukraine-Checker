import countries.*;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CountriesChecker {
    ArrayList<Country> list = new ArrayList<>(Arrays.asList(new Ukraine(), new Poland(), new USA(),
            new UK(), new Slovakia(), new Germany(), new Croatia(), new Sweden(), new Canada()));
    TreeMap<Double, String> map;
    ArrayList<String> notAvailable;

    String baseCreator = "https://www.ikea.com/";

    public void check(String name) {
        map = new TreeMap<>();
        notAvailable = new ArrayList<>();

        list.parallelStream().forEach(item->
        {

            try {
                Document doc = Jsoup.connect(baseCreator + item.id + "/" + item.lang + "/p/-" + name).execute().parse();

                //Price
                Price local = new Price(doc, item);
                map.put(local.get(), item.countryName + local.toString());

                //Rating
                map.put(local.get(), map.get(local.price) + new Rating(doc));

            } catch (HttpStatusException e) {
                notAvailable.add(item.countryName);
            } catch (NumberFormatException e) {
                System.out.println("NumberFormatException " + e.getLocalizedMessage());
            } catch (Exception ignored) {}

        });

        writeResult();
    }

    public void writeResult() {
        for (Double price : map.keySet()) {
            System.out.println((int) Math.round(price) + " грн in " + map.get(price));
        }

        if (notAvailable.size() > 0) System.out.println("Product is not available in " + notAvailable.stream().map(Object::toString).collect(Collectors.joining(", ")));

    }
}
