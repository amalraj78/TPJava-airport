import javafx.application.Application;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.PickResult;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Translate;


public class Interface extends Application {
    private double lastMouseY = 0;
    public World w = new World("data/airport-codes_no_comma.csv");

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello world");
        Earth earth = new Earth();
        earth.displayYellowSphere(w);
        Pane pane = new Pane(earth);

        Scene ihm = new Scene(pane, 600, 400,true);
        primaryStage.setScene(ihm);
        primaryStage.show();

        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-1000);
        camera.setNearClip(0.1);
        camera.setFarClip(2000.0);
        camera.setFieldOfView(35);
        ihm.setCamera(camera);


        ihm.addEventHandler(MouseEvent.ANY, event -> {

            if (event.getButton()== MouseButton.PRIMARY && event.getEventType()==MouseEvent.MOUSE_CLICKED) {
                double currentMouseY = event.getSceneY();
                double deltaY = currentMouseY - lastMouseY;

                // Appliquer la transformation de zoom en fonction du sens
                Translate translate = new Translate(0, 0, deltaY * 0.5);
                camera.getTransforms().add(translate);
                lastMouseY = currentMouseY; // Mettre à jour la position précédente
            }
        });

        ihm.addEventHandler(MouseEvent.ANY, event ->{
            if (event.getButton()== MouseButton.SECONDARY && event.getEventType()==MouseEvent.MOUSE_CLICKED) {
                PickResult pickResult = event.getPickResult();
                if (pickResult.getIntersectedNode() != null) {
                    double x = pickResult.getIntersectedPoint().getX();
                    double y = pickResult.getIntersectedPoint().getY();
                    double z = pickResult.getIntersectedPoint().getZ();

                    double latitude = 180 * (0.5 - y) -180 ;
                    double longitude = 360 * (x - 0.5) + 90 ;

                    System.out.println("Longitude: "+longitude+", Latitude: "+latitude);

                    Aeroport closestAeroport = w.findNearest(latitude,longitude);
                    if(closestAeroport != null) {
                        System.out.println("Aéroport le plus proche: " + closestAeroport + "\n\n");
                        earth.displayRedSphere(closestAeroport);
                    }
                }
            }
        });

    }
    public static void main(String[] args) {
        launch(args);
    }
}