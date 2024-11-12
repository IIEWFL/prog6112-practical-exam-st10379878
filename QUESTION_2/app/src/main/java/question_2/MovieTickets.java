package question_2;

public class MovieTickets implements IMovieTickets {
    private String movieName;
    private int numberOfTickets;
    private double ticketPrice;

    // Constructor
    public MovieTickets(String movieName, int numberOfTickets, double ticketPrice) {
        this.movieName = movieName;
        this.ticketPrice = ticketPrice;
        this.numberOfTickets = numberOfTickets;

    }

    
    public double CalculateTotalTicketPrice(int numberOfTickets, double ticketPrice) {
        double total = ticketPrice * numberOfTickets * 1.14;
        return Math.round(total * 10) / 10.0;
    }

    // New method to validate all the required fields
    public boolean ValidateData() {
        // Validate movie
        if (movieName == null || movieName.trim().isEmpty()) {
            return false;
        }

        // Validate property price
        if (ticketPrice <= 0) {
            return false;
        }

        // Validate commission percentage
        if (numberOfTickets <= 0) {
            return false;
        }

        // All validations passed
        return true;
    }

    // Getters
    public String getMovie() {
        return movieName;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public double getNumberOfTickets() {
        return numberOfTickets;
    }
}
