package minecraft.manager.api;

import java.nio.file.Path;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.DirectoryCodeResolver;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.rendering.template.JavalinJte;
import io.javalin.websocket.WsContext;
import minecraft.manager.api.controller.ServerController;

public class API {
    static final int PORT = 7000;
    static Javalin app;
    static String PATH_TO_SERVER;
    private static Set<WsContext> sessions = ConcurrentHashMap.newKeySet();
    private static ServerController serverController;
    public static void main(String[] args) {
        PATH_TO_SERVER = args[0];
        System.out.println("Minecraft Manager API has started.");
        app = setupApp().start(PORT);
    }

    // Separated method to easily test the server
    public static Javalin setupApp() {
        JavalinJte.init(createTemplateEngine());
         serverController = new ServerController(PATH_TO_SERVER);

        // Search static files inside the default folder in dev and just a "static"
        Javalin app = Javalin.create(config -> {
            String folder = "src/main/static";
            if (System.getenv("MINECRAFT_SERVER_API") != null) {
                folder = "static";
            }
            config.staticFiles.add(folder, Location.EXTERNAL);
        });

        app.get("/startServer", serverController::runServer);
        app.get("/stopServer",serverController::stopServer);
        app.get("/console", serverController::console);
        app.get("/", serverController::home);
        app.get("/home", serverController::home);
        setupWebsocket(app);

        return app;
    }

    public static void setupWebsocket(Javalin app) {
        app.ws("/console", ws -> {
            ws.onConnect(session -> {
                sessions.add(session);
            });
            ws.onClose(session -> {
                sessions.remove(session);
            });
            ws.onMessage((session) -> {
                String message = session.message();
                serverController.handleWebSocketMessage(message);
            });;
        });
    }

    public static void sendToAll(String message) {
        sessions.stream().filter(session -> session.session.isOpen()).forEach(session -> {
            session.send(message);
        });
    }

    // Configuration of JTE templates
    // Taken from the Javalin tutorials:
    // https://javalin.io/tutorials/jte#precompiling-templates
    private static TemplateEngine createTemplateEngine() {
        if (System.getenv("MINECRAFT_SERVER_API") != null) {
            // Production mode, use precompiled classes loaded in the JAR
            return TemplateEngine.createPrecompiled(Path.of("jte-classes"), ContentType.Html);
        } else {
            // Dev mode, compile on the fly templates in the default folder src/main/jte
            DirectoryCodeResolver codeResolver = new DirectoryCodeResolver(Path.of("src", "main", "jte"));
            return TemplateEngine.create(codeResolver, ContentType.Html);
        }
    }
}