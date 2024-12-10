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
    private PhongMaterial phg,phg2;
    private Rotate ry;


    public Earth() {
        sph = new Sphere(300);
        phg = new PhongMaterial();
        phg2 = new PhongMaterial();


        Image Texture = new Image("file:data/earth_lights_4800.png");
        phg.setDiffuseMap(Texture);
        sph.setMaterial(phg);

        this.getChildren().add(sph);

        ry = new Rotate(0,Rotate.Y_AXIS);
        sph.getTransforms().add(ry);
        AnimationTimer animationTimer = new AnimationTimer(){
            @Override
            public void handle(long time){
                //System.out.println("Valeur de time : " + time);
                    double angle = (time / 1000000000.0) * (360 / 15);
                ry.setAngle(angle);
            }
        };
        animationTimer.start();
    }

    public Sphere createSphere(Aeroport a, Color c){
        double latitude = Math.toRadians(a.getLatitude() * 13); // Correction empirique de latitude
        double longitude = Math.toRadians(a.getLongitude());

        double x = RADIUS * Math.cos(latitude) * Math.sin(longitude);
        double y = -RADIUS * Math.sin(latitude);
        double z = -RADIUS * Math.cos(latitude) * Math.cos(longitude);

        Sphere minSph = new Sphere(2);
        PhongMaterial phg2 = new PhongMaterial(c);
        minSph.setMaterial(phg2);

        minSph.setTranslateX(x);
        minSph.setTranslateX(y);
        minSph.setTranslateX(z);
        return minSph;
    }

    public void displayRedSphere(Aeroport a){
        Sphere redSph = new Sphere();
        redSph = createSphere(a,Color.RED);
        this.getChildren().add(redSph);
    }

    public void displayYellowSphere(World w){
        for(Aeroport aeroport : w.aeroportList){
            Sphere yellowSph = createSphere(aeroport, Color.YELLOW);
            this.getChildren().add(yellowSph);
        }
    }
}






