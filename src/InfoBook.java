import countries.Country;

public class InfoBook {
    //price message    country (original price and symbol) stars   score   (qty)
    //179   грн in      Ukraine (179.0 ₴)                   ★★★★    4.8     (233)

    double price;
    String message;
    Country country;
    String originalPrice;
    Rating rating;

    public InfoBook(Country country, Price price, Rating rating) {
        this.price = price.get();
        this.country = country;
        this.originalPrice = price.toString();
        this.rating = rating;
        this.message = " грн in ";
    }

    public InfoBook(Country country, Price price) {
        this.country = country;
        this.price = price.get();
        this.originalPrice = price.toString();
        this.rating = null;
        this.message = " грн in ";
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public double getPrice() {
        return price;
    }

    public String getMessage() {
        return message;
    }

    public Country getCountry() {
        return country;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public Rating getRating() {
        return rating;
    }

    public void printInfo() {
        System.out.printf("%-6d %-7s %-20s %-15s %-5s\n", Math.round(price), message, country.countryName, originalPrice, rating.toString());
    }

    @Override
    public String toString() {
        return Math.round(price) + message +  country.countryName +  originalPrice + rating.toString();
    }
}
