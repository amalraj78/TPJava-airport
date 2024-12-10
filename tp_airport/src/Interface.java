import javafx.application.Application;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Interface extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello world");
        Earth earth = new Earth();
        Pane pane = new Pane(earth);
        Scene theScene = new Scene(pane, 600, 600,true);
        primaryStage.setScene(theScene);
        primaryStage.show();

        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-1000);
        camera.setNearClip(0.1);
        camera.setFarClip(2000.0);
        camera.setFieldOfView(35);
        theScene.setCamera(camera);
    }
    public static void main(String[] args) {
        launch(args);
    }
}