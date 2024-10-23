import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class World {
    private List<Aeroport> aeroportList;

    public World(String fileName) {
        try {
            BufferedReader buf = new BufferedReader(new FileReader(fileName));
            String s = buf.readLine();
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

    public void findNearest(double latitude, double longitude){
        double nearest=0;
        for(int i = 0; i<(aeroportList.size()-1); i++){
            if (latitude != aeroportList.get(i).getLatitude()
                    && longitude != aeroportList.get(i).getLongitude()){
                double distance = aeroportList.get(i).calculDistance()
            }
        }
    }
}
