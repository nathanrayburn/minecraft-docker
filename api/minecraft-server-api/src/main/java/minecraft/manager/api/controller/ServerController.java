package minecraft.manager.api.controller;
import io.javalin.http.Context;
import minecraft.manager.api.model.ServerManager;

import javax.imageio.IIOException;
import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ServerController {
    final String PATH_TO_SERVER;
    ServerManager serverManager;
    public ServerController(String PATH_TO_SERVER){
        serverManager = new ServerManager();
        this.PATH_TO_SERVER = PATH_TO_SERVER;
    }
    public void runServer(Context ctx){
        try{
            serverManager.startServer(PATH_TO_SERVER);
        }catch (Exception ex){
            System.out.printf(ex.getMessage());
        }
        //if(serverManager.getProcess().isAlive());
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
    }
    public void console(Context ctx){

    }
}
