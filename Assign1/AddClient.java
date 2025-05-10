import java.rmi.Naming;
import java.util.Scanner;

public class AddClient {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            // Construct RMI URL
            String addServerURL = "rmi://localhost/AddServer";

            // Lookup the remote object
            AddServerIntf addServerIntf = (AddServerIntf) Naming.lookup(addServerURL);

            // Get user input for two numbers
            System.out.print("Enter the first number: ");
            int d1 = scanner.nextInt();

            System.out.print("Enter the second number: ");
            int d2 = scanner.nextInt();

            // Invoke remote method
            int result = addServerIntf.add(d1, d2);
            System.out.println("The sum is: " + result);

            scanner.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
