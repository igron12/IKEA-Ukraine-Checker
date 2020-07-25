package countries;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Objects;

public abstract class Country {
    public String id;
    public String lang;
    public double rate;
    public String className;
    public String currencySymbol;
    public String countryName;
    public NumberFormat numberFormat = DecimalFormat.getCurrencyInstance();

    public Country(String id, String lang, double rate, String className, String currencySymbol, String countryName) {
        this.id = id;
        this.lang = lang;
        this.rate = rate;
        this.className = className;
        this.currencySymbol = currencySymbol;
        this.countryName = countryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return id.equals(country.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}