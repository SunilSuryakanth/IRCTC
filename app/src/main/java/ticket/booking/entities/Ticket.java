package ticket.booking.entities;

import java.util.Date;

public class Ticket {
    private String ticketId;
    private String userId;
    private String source;
    private String destination;
    private Date dateOfTravel;
    private Train train;

    public Ticket(String ticketId, String userId, String source, String destination, Date dateOfTravel, Train train) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.source = source;
        this.destination = destination;
        this.dateOfTravel = dateOfTravel;
        this.train = train;
    }
    public void setTicketId(String ticketId){
        this.ticketId = ticketId;
    }
    public String getTicketId(){
        return ticketId;
    }
    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getUserId(){
        return userId;
    }
    public void setSource(String source){
        this.source = source;
    }
    public String getSource(){
        return source;
    }
    public void setDestination(String destination){
        this.destination = destination;
    }
    public String getDestination(){
        return destination;
    }
    public void setDateOfTravel(Date dateOfTravel){
        this.dateOfTravel = dateOfTravel;
    }
    public Date getDateOfTravel(){
        return dateOfTravel;
    }
    public void setTrain(Train train){
        this.train = train;
    }
    public Train getTrain(){
        return train;
    }

    public String getTicketInfo() {
        return "Ticket Details:\n" +
                "Ticket ID: " + ticketId + "\n" +
                "User ID: " + userId + "\n" +
                "Source: " + source + "\n" +
                "Destination: " + destination + "\n" +
                "Date of Travel: " + dateOfTravel + "\n" +
                "Train: " + (train != null ? train.toString() : "No Train Assigned");
    }
}
