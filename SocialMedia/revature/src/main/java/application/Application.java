package application;

import io.javalin.Javalin;
import application.Controller.SocialMediaController;

public class Application 
{
    public static void main( String[] args )
    {
        SocialMediaController controller = new SocialMediaController();
        Javalin app = controller.startAPI();
        app.start(8080);
    }
}
