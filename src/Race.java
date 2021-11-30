import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Race implements Serializable {
    private LocalDate raceDate;
    private ArrayList<String[]> racePositions;
    private static final long serialVersionUID = 4L;

    public Race(LocalDate raceDate) {
        this.raceDate = raceDate;
        this.racePositions = new ArrayList<>();


    }
//    public Race(ArrayList<Formula1Driver> drivers) {
//        this.racePositions = new ArrayList<>();
//        generateRacePositions(drivers);
//    }

    public LocalDate getRaceDate() {
        return raceDate;
    }

    public void setRaceDate(LocalDate raceDate) {
        this.raceDate = raceDate;
    }

    public ArrayList<String[]> getRacePositions() {
        return racePositions;
    }

    public void addRacePositions(String[] racePosition) {
        this.racePositions.add(racePosition);

    }
//    public void generateRacePositions(ArrayList<Formula1Driver> drivers) {
//        ArrayList<String> repeatCheck = new ArrayList<>();
//        for (int i = 0;i<drivers.size();i++) {
//            repeatCheck.add(Integer.toString(i+1));
//        }
//        Collections.shuffle(repeatCheck);
//        for (int j = 0; j<drivers.size();j++) {
//            this.addRacePositions(new String[] {drivers.get(j).getDriverName(),repeatCheck.get(j)});
//
//        }
//
//
//    }
}
