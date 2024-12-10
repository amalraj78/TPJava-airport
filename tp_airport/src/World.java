import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class World {
    private List<Aeroport> aeroportList = new ArrayList<>();

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
                    double latitude = Double.parseDouble(fields[11]);
                    double longitude = Double.parseDouble(fields[12]);
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
        final double R = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // Distance en kilomètres
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
