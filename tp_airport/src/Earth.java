import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

public class Earth extends Group {
    private static final double RADIUS = 300;
    private Sphere sph;
    private Rotate ry;
    private Sphere redSph=null;


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
                    double angle = (time / 1000000000.0) * (360 / 30);
                ry.setAngle(angle);
            }
        };
        animationTimer.start();
        this.getChildren().add(sph);
    }

    public Sphere createSphere(Aeroport a, Color c,int taille) {

        Sphere coloredSph = new Sphere(taille);
        PhongMaterial phg2 = new PhongMaterial();
        phg2.setDiffuseColor(c);
        coloredSph.setMaterial(phg2);

        coloredSph.setTranslateZ(-sph.getRadius());

        double latitude = a.getLatitude();
        double longitude = a.getLongitude();

        Rotate rPhi = new Rotate (-longitude,
                -coloredSph.getTranslateX(),-coloredSph.getTranslateY(),
                -coloredSph.getTranslateZ(),Rotate.Y_AXIS);

        coloredSph.getTransforms().add(rPhi);
        Rotate rTheta = new Rotate (-latitude*60.0/90.0,
                -coloredSph.getTranslateX(),-coloredSph.getTranslateY(),
                -coloredSph.getTranslateZ(),Rotate.X_AXIS);
        coloredSph.getTransforms().add(rTheta);


        return coloredSph;
    }


    public void displayRedSphere(Aeroport a){
        boolean isRedSph = false;
        if (isRedSph == false) {
            redSph = createSphere(a, Color.RED, 4);
            this.getChildren().add(redSph);
            isRedSph=true;
        }
        else {
            this.getChildren().remove(redSph);
            isRedSph=false;
        }
    }

    public void displayYellowSphere(World w){
        for(Aeroport aeroport : w.aeroportList){
            Sphere yellowSph = createSphere(aeroport, Color.YELLOW, 2);
            this.getChildren().add(yellowSph);
        }
    }
}






