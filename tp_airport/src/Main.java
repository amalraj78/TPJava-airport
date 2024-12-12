public class Main {

    public static void main(String[] args){
        World w = new World ("tp_airport/data/airport-codes_no_comma.csv");
        Aeroport paris = w.findNearest(2.316,48.866);
        Aeroport cdg = w.findByCode("CDG");
        System.out.println(paris);
        System.out.println(cdg);
        System.out.println("Nombre d'aéroports : " + w.aeroportList.size());

    }
}

