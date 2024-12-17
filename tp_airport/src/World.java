import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class World {
    public List<Aeroport> aeroportList = new ArrayList<>();

    public World(String fileName) {
        try {
            BufferedReader buf = new BufferedReader(new FileReader(fileName));
            String s = buf.readLine();
            s = buf.readLine();
            while (s != null) {
                s = s.replaceAll("\"", "");
                //Enleve les guillemets qui séparent les champs de donn´ees GPS.
                String fields[] = s.split(",");
                // Une bonne id´ee : placer un point d'arrêt ici pour du debuggage.
                if (fields[1].equals("large_airport")) {
                    String Name = fields[2];
                    String country = fields[5];
                    String iata = fields[9];
                    double latitude = Double.parseDouble(fields[12]);
                    double longitude = Double.parseDouble(fields[11]);
                    Aeroport aeroport = new Aeroport(iata, Name, country, latitude, longitude);
                    aeroportList.add(aeroport);
                }
                s = buf.readLine();
            }
        } catch (Exception e) {
            System.out.println("Maybe the file isn't there ?");
            System.out.println(aeroportList.get(aeroportList.size() - 1));
            e.printStackTrace();
        }
    }


    public Aeroport findNearest(double latitude, double longitude) {
        Aeroport nearestAeroport = null;
        double minDistance = Double.MAX_VALUE; // Distance minimale initialisée à un maximum

        for (Aeroport aeroport : aeroportList) {
            double distance = calculateDistance(latitude, longitude, aeroport.getLatitude(), aeroport.getLongitude());
            if (distance < minDistance) {
                minDistance = distance;
                nearestAeroport = aeroport;
            }
        }
        return nearestAeroport; // Retourne l'aéroport le plus proche ou null si la liste est vide
    }

    // Méthode pour calculer la distance entre deux points géographiques
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double deltaLat = lat2-lat1;
        double deltaLon = (lon2-lon1)*Math.cos((lat2+lat1)/2);
        double a=(deltaLon*deltaLon) + (deltaLat*deltaLat);
        return a;
    }

    public Aeroport findByCode(String IATA){
        for(Aeroport aeroport : aeroportList){
            if (aeroport.getIATA().equals(IATA)){
                return aeroport;
            }
        }
        return null;
    }

}
