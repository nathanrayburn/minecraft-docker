package minecraft.manager.api.controller;

import io.javalin.http.Context;
import minecraft.manager.api.API;
import minecraft.manager.api.model.ConsoleOutputListener;
import minecraft.manager.api.model.ServerManager;
import io.javalin.Javalin;
import io.javalin.websocket.WsContext;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ServerController implements ConsoleOutputListener {
    final String PATH_TO_SERVER;
    ServerManager serverManager;
    ConcurrentLinkedQueue<String> consoleOutput = new ConcurrentLinkedQueue<>();

    public ServerController(String PATH_TO_SERVER){
        serverManager = new ServerManager();
        serverManager.setConsoleOutputListener(this);
        this.PATH_TO_SERVER = PATH_TO_SERVER;
    }

    @Override
    public void onNewConsoleOutput(String line) {
        consoleOutput.add(line);
        API.sendToAll(line);
    }

    public void handleWebSocketMessage(String message) {
        try{
            serverManager.sendCommand(message);
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }

    }

    public void runServer(Context ctx){
        try{
            serverManager.startServer(PATH_TO_SERVER);
        }catch (Exception ex){
            System.out.printf(ex.getMessage());
        }
        ctx.render("console.jte");
    }

    public void stopServer(Context ctx){
        Optional<ProcessHandle> optionalProcessHandle = ProcessHandle.of(serverManager.getSubPid());
        if (optionalProcessHandle.isPresent()) {
            ProcessHandle processHandle = optionalProcessHandle.get();
            if (processHandle.isAlive()) {
                processHandle.destroy();
                System.out.println("Server terminated.");
            }
        }
        if(serverManager.getProcess().isAlive()){
            serverManager.getProcess().destroy();
            System.out.println("Script terminated.");
        }

        serverManager.setSubPid(0);
        serverManager.setPid(0);
        ctx.render("console.jte");
    }

    public void console(Context ctx){
        Map<String, Object> model = new HashMap<>();
        ctx.render("console.jte", model);
    }
    public void home(Context ctx){
        ctx.render("home.jte");
    }
}