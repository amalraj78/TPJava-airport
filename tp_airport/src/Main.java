import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) {
//        World w = new World ("tp_airport/data/airport-codes_no_comma.csv");
//        Aeroport paris = w.findNearest(48.866, 2.316);
//        Aeroport cdg = w.findByCode("CDG");
//        System.out.println(paris);
//        System.out.println(cdg);
//        System.out.println("Nombre d'aéroports : " + w.aeroportList.size());
//
//    }
        try {
            World w = new World ("tp_airport/data/airport-codes_no_comma.csv");
            BufferedReader br = new BufferedReader(new FileReader("tp_airport/data/JsonOrly.txt"));
            String jsonString = br.readLine();
            JsonFlightFiller jSonFlightFiller = new JsonFlightFiller(jsonString, w);
            System.out.println("ca marche ");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ca marche pas");
        }
    }
}

