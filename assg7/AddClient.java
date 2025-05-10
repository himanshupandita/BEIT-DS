import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class AddClient {
    public static void main(String[] args) {
        try {
            Scanner sc= new Scanner(System.in);

            System.out.print("enter num1:");
            int num1 = sc.nextInt();
            System.out.println("enter num2:");
            int num2 = sc.nextInt();
            String urlStr = "http://localhost:5000/add?a=" + num1 + "&b=" + num2;
            
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream())
            );
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            System.out.println("Response from Web Service: " + content.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
