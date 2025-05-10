import java.io.*;
import java.net.*;
import java.util.*;

public class MasterClock {
    public static void main(String[] args) {
        final int PORT = 5000;
        final int SLAVE_COUNT = 3; // Change as needed

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Master waiting for slave clocks...");

            List<Socket> sockets = new ArrayList<>();
            List<Integer> times = new ArrayList<>();

            int masterTime = 10000; // Fixed master time
            System.out.println("Master time: " + masterTime);

            // Accept connections
            for (int i = 0; i < SLAVE_COUNT; i++) {
                Socket socket = serverSocket.accept();
                sockets.add(socket);

                DataInputStream in = new DataInputStream(socket.getInputStream());
                int slaveTime = in.readInt();
                System.out.println("Received time from slave " + (i + 1) + ": " + slaveTime);
                times.add(slaveTime);
            }

            // Calculate average
            int sum = masterTime;
            for (int time : times) sum += time;
            int avgTime = sum / (SLAVE_COUNT + 1);
            int masterOffset = avgTime - masterTime;

            System.out.println("Average time: " + avgTime);
            System.out.println("Master offset: " + masterOffset);

            // Send offsets
            for (int i = 0; i < sockets.size(); i++) {
                int slaveTime = times.get(i);
                int offset = avgTime - slaveTime;

                DataOutputStream out = new DataOutputStream(sockets.get(i).getOutputStream());
                out.writeInt(offset);

                sockets.get(i).close();
            }

            System.out.println("All clocks synchronized.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
