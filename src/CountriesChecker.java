import countries.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CountriesChecker {
    ArrayList<Country> list = new ArrayList<>(Arrays.asList(new Ukraine(), new Poland(), new USA(),
            new UK(), new Slovakia(), new Germany()));
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
                String local = doc.getElementsByClass(item.className).first().text()
                        .replace(",", ".")
                        //.replaceAll("[^[0-9]([.][0-9]{1,2})]","");
                        .replace(".-","")
                        .replace("$", "").replace("€", "").replace(" ", "")
                        .replace("грн", "").replace("£", "")
                        ;
                        //System.out.println(local);
                double total = Double.parseDouble(local) * item.rate;
                map.put(total, item.countryName + " (" + local + " " + item.currencySymbol + ")");

                //Rating
                try {
                    map.put(total, map.get(total) + new Rating(doc));
                } catch (Exception ignored) {}

            } catch (Exception e) {
                notAvailable.add(item.countryName);
                //e.printStackTrace();
            }

            //^\d*\[.,]?\d*$
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
