import countries.Country;
import countries.Croatia;
import org.jsoup.nodes.Document;

public class Price {
    Double price, originalPrice;
    Country country;

    public Price(Document doc, Country countryItem) {
        String stringPrice = doc.getElementsByClass(countryItem.className).first().text();
        if (stringPrice.contains("/")) stringPrice = stringPrice.split("/")[0];
        if (countryItem.equals(new Croatia())) stringPrice = stringPrice.replace(".", "");
        stringPrice = stringPrice.replace(",", ".").replaceAll("[^0-9.]","");
        this.originalPrice = Double.parseDouble(stringPrice);
        this.price = originalPrice * countryItem.rate;
        this.country = countryItem;
    }

    public double get() {
        return price;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    @Override
    public String toString() {
        return " (" + this.originalPrice + " " + country.currencySymbol + ")";
    }
}
