package ticket.booking.services;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserBookingService {
    private User user;
    private List<User> userList;

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String USERS_PATH= "app/src/main/java/ticket/booking/localDb/users.json";


    public UserBookingService() throws IOException{
        loadUser();

    }

    public List<User> loadUser() throws IOException{
        File users = new File(USERS_PATH);
        return  userList = objectMapper.readValue(users, new TypeReference<List<User>>() {});
    }

    public UserBookingService(User user1) throws IOException {
        this.user = user1;
        loadUser();
    }
    public Boolean loginUser(){
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassWord(),
                    user1.getHashPassword());
        }).findFirst();
        return foundUser.isPresent();
    }

    public Boolean signUp(User user1){
        try{
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        }catch (IOException ex) {
            return Boolean.FALSE;
        }
    }
    public void saveUserListToFile() throws IOException {
        File userFile = new File(USERS_PATH);
        objectMapper.writeValue(userFile, userList);
    }
    public void fetchBooking() {
        user.printTickets();
    }

    public boolean cancleBooking() {

        if (user == null) {
            return false;
        }

        if (user.getTickets() == null || user.getTickets().isEmpty()) {
            System.out.println("No bookings found.");
            return false;
        }
        user.getTickets().remove(user.getTickets().size() - 1);

        try {
            saveUserListToFile();
            System.out.println("Booking cancelled successfully.");
            return true;
        } catch (IOException e) {
            System.out.println("Failed to save changes.");
            return false;
        }
    }
    public List<Train> getTrains(String source, String destination) {
        try {
            TrainService trainService = new TrainService();
            return trainService.searchTrains(source,destination);
        }catch (Exception e){
            return null;
        }
    }
    public boolean cancelBooking() {

        if (user == null) {
            return false;
        }
        if (user.getTickets() == null || user.getTickets().isEmpty()) {
            return false;
        }
        user.getTickets().remove(user.getTickets().size() - 1);
        try {
            saveUserListToFile();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public List<List<Integer>> fetchSeats(Train trainSelectedForBooking) {

        if (trainSelectedForBooking == null) {
            return null;
        }
        return trainSelectedForBooking.getSeats();
    }

    public Boolean bookTrainSeat(
            Train trainSelectedForBooking,
            int row,
            int column) {

        if (trainSelectedForBooking == null) {
            return Boolean.FALSE;
        }

        List<List<Integer>> seats =
                trainSelectedForBooking.getSeats();

        if (row < 0 || row >= seats.size()) {
            return Boolean.FALSE;
        }

        if (column < 0 || column >= seats.get(row).size()) {
            return Boolean.FALSE;
        }
        if (seats.get(row).get(column) == 1) {
            return Boolean.FALSE;
        }
        seats.get(row).set(column, 1);

        try {
            saveUserListToFile();
            return Boolean.TRUE;
        } catch (IOException e) {
            return Boolean.FALSE;
        }
    }
}
