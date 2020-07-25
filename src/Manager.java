import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Manager {

    public void start() throws IOException {
        String name;
        while (true) {
            System.out.println("***");
            name = getString();
            if (name.toLowerCase().equals("exit")) break;
            new CountriesChecker().check(name);
        }
    }

    public String getString() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Paste link or ID: ");
        String input = reader.readLine();
        return input.substring(input.lastIndexOf("-")+1);
    }
}
