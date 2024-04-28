package minecraft.manager.api.model;
import java.io.*;
import java.sql.SQLOutput;

public class ServerManager {
    private ConsoleOutputListener consoleOutputListener;
    private Process process;
    private PrintWriter writer;
    private BufferedReader reader;
    private OutputStream stdin;
    private InputStream stdout;
    private long pid = 0;
    private long sub_pid = 0;

    public void setConsoleOutputListener(ConsoleOutputListener listener) {
        this.consoleOutputListener = listener;
    }

    public void startServer(String pathToExecutable) throws IOException {
        if(this.pid != 0 || this.sub_pid != 0) throw new Error("Server is already running.");
        File workingDirectory = new File(pathToExecutable).getParentFile();
        ProcessBuilder builder = new ProcessBuilder(pathToExecutable);
        builder.directory(workingDirectory);
        builder.redirectErrorStream(true);
        process = builder.start();
        pid = process.pid();

        System.out.println("Minecraft server start PID: " + pid);
        /*BufferedReader reader_sub = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String pidOfSubprocess = reader_sub.readLine();
        sub_pid = Integer.parseInt(pidOfSubprocess);
        System.out.println("Subprocess jar PID: " + pidOfSubprocess); */

        stdin = process.getOutputStream();
        stdout = process.getInputStream();

        writer = new PrintWriter(stdin, true);
        reader = new BufferedReader(new InputStreamReader(stdout));

        new Thread(() -> {
            System.out.println("Starting output reader thread...");
            BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    System.out.println("Read line: " + line);
                    if (consoleOutputListener != null) {
                        consoleOutputListener.onNewConsoleOutput(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }
    public Process getProcess(){
        return this.process;
    }
    public void setSubPid(long n){
        this.sub_pid = n;
    }
    public void setPid(long n){
        this.pid = n;
    }
    public void sendCommand(String command) {
        System.out.println("Sending message: " + command);
        writer.println(command);
        writer.flush();
    }

    public String readResponse() throws IOException {
        return reader.readLine();
    }
    public long getSubPid(){
        return this.sub_pid;
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
