import java.time.LocalDateTime;

public class Flight {
    private Aeroport departure;
    private Aeroport arrival;
    private String airlineName;
    private String airlineCode;
    private int number;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;


    public Flight(Aeroport departure, Aeroport arrival, String airlineName, String airlineCode, int number, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.departure = departure;
        this.arrival = arrival;
        this.airlineName = airlineName;
        this.airlineCode = airlineCode;
        this.number = number;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
}
