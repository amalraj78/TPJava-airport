public class Aeroport {
    private String IATA;
    private String Name;
    private String country;
    private double latitude;
    private double longitude;

    public String getIATA() {
        return IATA;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Aeroport(String IATA, String name, String country, double latitude, double longitude) {
        this.IATA = IATA;
        this.Name = name;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "L'aéroport de "+Name+", situé en "+country+", en "+latitude+", "+longitude+", et le code IATA est :"+IATA;
    }
}
