import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.image.Image;

import javax.swing.*;
import java.util.ArrayList;

public class Earth extends Group {
    private static final double RADIUS = 300;
    private Sphere sph;
    private Rotate ry;


    public Earth() {
        sph = new Sphere(300);
        PhongMaterial phg = new PhongMaterial();
        PhongMaterial phg2 = new PhongMaterial();

        Image Texture = new Image("file:tp_airport/data/earth_lights_4800.png");
        phg.setDiffuseMap(Texture);
        sph.setMaterial(phg);

        ry = new Rotate(0,Rotate.Y_AXIS);
        this.getTransforms().add(ry);
        AnimationTimer animationTimer = new AnimationTimer(){
            @Override
            public void handle(long time){
                //System.out.println("Valeur de time : " + time);
                    double angle = (time / 1000000000.0) * (360 / 60);
                ry.setAngle(angle);
            }
        };
        animationTimer.start();
        this.getChildren().add(sph);
    }

    public Sphere createSphere(Aeroport a, Color c) {
        Sphere coloredSph = new Sphere();
        PhongMaterial phg2 = new PhongMaterial();
        phg2.setDiffuseColor(c);
        coloredSph.setMaterial(phg2);

        double latitude = Math.toRadians(a.getLatitude());
        double longitude = Math.toRadians(a.getLongitude());

        double radius = sph.getRadius();

        // Conversion latitude/longitude en coordonnées 3D
        double x = radius * Math.cos(latitude) * Math.cos(longitude);
        double y = radius * Math.sin(latitude);
        double z = radius * Math.cos(latitude) * Math.sin(longitude);

        coloredSph.setTranslateX(x);
        coloredSph.setTranslateY(-y); // Inversion Y pour correspondre à la convention graphique
        coloredSph.setTranslateZ(z);

        return coloredSph;
    }


    public void displayRedSphere(Aeroport a){
        Sphere redSph = new Sphere(5);
        redSph = createSphere(a,Color.RED);
        //redSph = redSph.set
        this.getChildren().add(redSph);
    }

    public void displayYellowSphere(World w){
        Sphere yellowSph = new Sphere(2);
        for(Aeroport aeroport : w.aeroportList){
            yellowSph = createSphere(aeroport, Color.YELLOW);
            this.getChildren().add(yellowSph);
        }
    }
}






