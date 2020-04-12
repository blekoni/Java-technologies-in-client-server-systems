import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Arrays;

public class Connection extends Thread {
    protected Socket socket;
    protected BufferedReader fromClient;
    protected PrintStream toClient;

    public Connection(Socket socket) {
        this.socket = socket;
        try {
            fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            toClient = new PrintStream(socket.getOutputStream());

        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException ex) {
                System.err.println("Unable to set up streams: " + ex);
                return;
            }
            e.printStackTrace();
        }
        this.start();
    }

    public void run() {
        String clientMessage;
        try {
            while (true) {
                toClient.println("Your string: ");
                clientMessage = fromClient.readLine();
                if (clientMessage == null || clientMessage.equals("end")) {
                    return;
                } else {
                    toClient.println(processMessage(clientMessage));
                }
            }
        } catch (IOException e) {
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }

    public String processMessage(String message) {
        String s[] = message.split(" ");
        String result = "Result: ";
        for (int i = 0; i < s.length; i++) {
            if (processWord(s[i])) {
                result = result.concat(s[i] + " ");
            }
        }
        return result;
    }

    public boolean processWord(String word) {
        char[] arr = word.toCharArray();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                return true;
            }
        }
        return false;
    }
}
