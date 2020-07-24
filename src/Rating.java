import org.jsoup.nodes.Document;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Rating {
    String ratingScore, ratingQty, stars;

    public Rating(Document doc) {
        ratingScore = doc.getElementsByClass("range-revamp-average-rating__header").first().text();
        ratingQty = doc.getElementsByClass("range-revamp-average-rating__reviews").first().text();
    }

    @Override
    public String toString() {
        stars = IntStream.iterate(1, i -> i <= Double.parseDouble(ratingScore), i -> i + 1)
                .mapToObj(i -> "★").collect(Collectors.joining("", " ", ""));
        return stars + this.ratingScore + " " + ratingQty;
    }

}
