package ticket.booking.entities;

import java.util.Collection;
import java.util.List;

public class User {
    private String name;
    private String passWord;
    private String hashPassword;

    private List<Ticket> ticketsBooked;
    private String userId;


    public User(String name, String passWord, String hashPassword, List<Ticket> ticketsBooked, String userId) {
        this.name = name;
        this.passWord = passWord;
        this.hashPassword = hashPassword;
        this.ticketsBooked = ticketsBooked;
        this.userId = userId;
    }

    public void User(){

    }
    public String getName(){
        return name;
    }
    public String getPassWord(){
        return passWord;
    }
    public String getHashPassword(){
        return hashPassword;
    }
    public List<Ticket> getTicketsBooked() {
        return ticketsBooked;
    }
    public String getUserId(){
        return userId;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setPassWord(String passWord){
        this.passWord = passWord;
    }
    public void setHashPassword(String hashPassword){
        this.hashPassword = hashPassword;
    }

    public void setTicketsBooked(List<Ticket> ticketsBooked){
        this.ticketsBooked = ticketsBooked;
    }
    public void setUserId(String userId){
        this.userId = userId;
    }
    public void printTickets(){
        for(int i=0; i<ticketsBooked.size(); i++){
            System.out.println(ticketsBooked.get(i).getTicketInfo());
        }
    }
    public List<Ticket> getTickets() {
        return ticketsBooked;
    }
}
