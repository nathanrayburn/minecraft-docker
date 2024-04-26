package minecraft.manager.api.model;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLOutput;

public class ServerManager {

    private Process process;
    private PrintWriter writer;
    private BufferedReader reader;
    private OutputStream stdin;
    private InputStream stdout;
    private long pid;
    public void startServer(String pathToExecutable) throws IOException {
        ProcessBuilder builder = new ProcessBuilder(pathToExecutable);
        builder.redirectErrorStream(true);
        process = builder.start();
        pid = process.pid();

        System.out.println("Minecraft server start PID: " + pid);

        stdin = process.getOutputStream();
        stdout = process.getInputStream();

        writer = new PrintWriter(stdin, true);
        reader = new BufferedReader(new InputStreamReader(stdout));
    }
    public Process getProcess(){
        return this.process;
    }
    public void sendCommand(String command) {
        writer.println(command);
        writer.flush();
    }

    public String readResponse() throws IOException {
        return reader.readLine();
    }

    public static void main(String[] args) {
        try {
            ServerManager manager = new ServerManager();

            manager.startServer("/home/nathan/Documents/HEIG/minecraft-docker/api/minecraft-server-api/src/server-files/ServerStart.sh"); // Adjust path to your server executable

            // Example commands
            manager.sendCommand("help");
            System.out.println("Server Response: " + manager.readResponse());

            // Add more interactions as needed
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
