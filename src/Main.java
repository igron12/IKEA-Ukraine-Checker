import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new TrustManagerImplementation().start();
        new Manager().start();
    }
}
