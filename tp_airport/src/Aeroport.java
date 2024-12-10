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
        return Name+", situé en "+country+", en "+latitude+", "+longitude+", et le code IATA est :"+IATA;
    }

    public double calculDistance(Aeroport a) {

        double lat1 = Math.toRadians(this.latitude);
        double lon1 = Math.toRadians(this.longitude);
        double lat2 = Math.toRadians(a.getLatitude());
        double lon2 = Math.toRadians(a.getLongitude());

        // Différences entre les coordonnées
        double deltaLat = lat2 - lat1;
        double deltaLon = lon2 - lon1;

        // Calcul selon la formule
        double distance = (Math.pow(deltaLat, 2) + Math.pow(deltaLon * Math.cos((lat1 + lat2) / 2), 2))*6371;

        return distance;
    }

}
