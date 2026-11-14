package ticket.booking.services;

import ticket.booking.entities.Train;

import java.util.List;
import java.util.stream.Collectors;

public class TrainService {

    TrainService(){}

    private List<Train> trainList;


    public TrainService(List<Train> trainList) {
        this.trainList = trainList;
    }

    public List<Train> searchTrains(String source, String destination) {
        return trainList.stream()
                .filter(train -> validTrain(train, source, destination))
                .collect(Collectors.toList());
    }

    private boolean validTrain(Train train, String source, String destination) {
        List<String> stations = train.getStations();

        int sIndex = stations.indexOf(source.toLowerCase());
        int dIndex = stations.indexOf(destination.toLowerCase());

        return sIndex != -1 && dIndex != -1 && sIndex < dIndex;
    }
}