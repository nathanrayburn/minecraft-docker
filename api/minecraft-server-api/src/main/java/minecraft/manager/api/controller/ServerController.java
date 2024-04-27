package minecraft.manager.api.controller;
import io.javalin.http.Context;
import minecraft.manager.api.model.ServerManager;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ServerController {
    final String PATH_TO_SERVER;
    ServerManager serverManager;
    public ServerController(String PATH_TO_SERVER){
        this.PATH_TO_SERVER = PATH_TO_SERVER;
    }
    public boolean runServer(Context ctx){
        serverManager = new ServerManager();
        try{
            serverManager.startServer(PATH_TO_SERVER);
        }catch (IOException ex){
            return false;
        }
        if(serverManager.getProcess().isAlive()){
            return true;
        }
        return false;

    }
    public void stopServer(Context ctx){
        Optional<ProcessHandle> optionalProcessHandle = ProcessHandle.of(serverManager.getSubPid());
        if (optionalProcessHandle.isPresent()) {
            ProcessHandle processHandle = optionalProcessHandle.get();
            if (processHandle.isAlive()) {
                processHandle.destroy();
                serverManager.setSubPid(0);
                System.out.println("Server terminated.");
            }
        }
        if(serverManager.getProcess().isAlive()){
            serverManager.getProcess().destroy();
            serverManager.setPid(0);
            System.out.println("Script terminated.");
        }
    }
    public void console(Context ctx){

    }
}
