import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        new TrustManagerImplementation().start();
        String name;

        while (true) {
            System.out.println("***");
            name = getString();
            if (name.toLowerCase().equals("exit")) break;
            new CountriesChecker().check(name);
        }
    }

    private static String getString() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Paste link or ID: ");
        String input = reader.readLine();
        return input.substring(input.lastIndexOf("-")+1);
    }
}
