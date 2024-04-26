package minecraft.manager.api.controller;
import io.javalin.http.Context;
import minecraft.manager.api.model.ServerManager;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ServerController {
    final String PATH_TO_SERVER;
    public ServerController(String PATH_TO_SERVER){
        this.PATH_TO_SERVER = PATH_TO_SERVER;
    }
    public boolean runServer(Context ctx){
        ServerManager serverManager = new ServerManager();
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
    public void console(Context ctx){

    }
}
