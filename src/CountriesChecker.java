import countries.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CountriesChecker {
    ArrayList<Country> list = new ArrayList<>(Arrays.asList(new Ukraine(), new Poland(), new USA(),
            new UK(), new Slovakia(), new Germany(), new Croatia(), new Sweden(), new Canada()));

    public void check(String name) {
        Item item = new Item();

        list.parallelStream().forEach(country->
                item.check(country, name));

        item.writeResult();
    }
}
