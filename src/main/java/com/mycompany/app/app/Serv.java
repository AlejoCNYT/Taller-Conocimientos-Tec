import java.net.*;
import java.io.*;

public class Serv {
    private static final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws IOException {

        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            boolean running = true;
            while (running) {
                Socket clientSocket = null;
                try {
                    System.out.println("Facade ready to receive...");
                } catch (IOException e) {
                    System.err.println("Accept failed " + e.getMessage());
                    System.exit(1);
                }
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    String inputLine, outputLine;
                    boolean firstLine = true;
                    String requestStringURI = "";
                    while ((inputLine = in.readLine()) != null) {
                        if (firstLine) {
                            System.out.println("Received: " + inputLine);
                            requestStringURI = inputLine.split(" ")[1];
                            firstLine = false;
                            continue;
                        }
                        if (!in.ready()) {
                            break;
                        }
                    }
                }

                // print result
                System.out.println(response.toString());

            }
            
                

    private static String getErrorPage() {
        return "HTTP/1.1 404 Not Found\n"
                + "Content-Type: text/html\n"
                + "\n"
                + "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<title>Not Found</title>\n"
                + "</head>"
                + "<body>"
                + "<h1>404 Not Found</h1>"
                + "</body>"
                + "</html>";
    }

}


