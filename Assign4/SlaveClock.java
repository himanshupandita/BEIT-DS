import java.io.*;
import java.net.*;
import java.util.Random;

public class SlaveClock {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            // Simulate clock time
            int localTime = 9500 + new Random().nextInt(1000);
            System.out.println("My current time: " + localTime);

            // Send time to master
            out.writeInt(localTime);

            // Receive offset
            int offset = in.readInt();
            localTime += offset;

            System.out.println("Adjusted time: " + localTime);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
