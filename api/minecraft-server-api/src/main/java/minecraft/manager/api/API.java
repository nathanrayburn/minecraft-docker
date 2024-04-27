package minecraft.manager.api;

import java.nio.file.Path;

import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.DirectoryCodeResolver;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.rendering.template.JavalinJte;
import minecraft.manager.api.controller.ServerController;

public class API {
    static final int PORT = 7000;
    static Javalin app;
    static String PATH_TO_SERVER;


    public static void main(String[] args) {
        PATH_TO_SERVER = args[0];
        System.out.println("Minecraft Manager API has started.");
        app = setupApp().start(PORT);
    }

    // Separated method to easily test the server
    public static Javalin setupApp() {
        JavalinJte.init(createTemplateEngine());
        ServerController serverController = new ServerController(PATH_TO_SERVER);

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


        return app;
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
