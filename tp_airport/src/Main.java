public class Main {

    public static void main(String[] args) {
        Aeroport test = new Aeroport("111","test", "France", 1010, 1111);
        System.out.println(test.toString());
        World list = new World("data/airport-codes_no_comma.csv");

    }

}

