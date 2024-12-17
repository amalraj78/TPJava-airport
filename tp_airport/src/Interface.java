import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
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
    public World w = new World("tp_airport/data/airport-codes_no_comma.csv");
    private double mousePosX;
    private double mousePosY;
    Translate tz = new Translate();

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello world");
        Earth earth = new Earth();
        earth.displayYellowSphere(w);

        Scene ihm = new Scene(earth, 800, 800,true);
        primaryStage.setScene(ihm);

        primaryStage.show();

        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-1000);
        camera.setNearClip(0.1);
        camera.setFarClip(2000.0);
        camera.setFieldOfView(35);
        ihm.setCamera(camera);


        ihm.addEventHandler(MouseEvent.ANY, event -> {
            if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                mousePosX = event.getSceneX();
                mousePosY = event.getSceneY();
            }
            if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                tz.setZ((event.getSceneY() - mousePosY)*0.01);
                camera.getTransforms().add(tz);
            }
        });


        ihm.addEventHandler(MouseEvent.ANY, event ->{
            if (event.getButton()== MouseButton.SECONDARY && event.getEventType()==MouseEvent.MOUSE_CLICKED) {
                PickResult pickResult = event.getPickResult();
                if (pickResult.getIntersectedNode() != null) {
                    double radius = 300;
                    Point2D intersectionPoint = pickResult.getIntersectedTexCoord();

                    double x = intersectionPoint.getX();
                    double y = intersectionPoint.getY();

                    double latitude = 180*(0.5-y);
                    double longitude = 360*(x-0.5);

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