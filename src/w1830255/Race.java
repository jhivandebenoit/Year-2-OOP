package w1830255;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Race implements Serializable {
    private LocalDate raceDate;
    private ArrayList<Formula1Driver> racePositions;
    private static final long serialVersionUID = 4L;

    public Race(LocalDate raceDate) {
        this.raceDate = raceDate;
        this.racePositions = new ArrayList<>();


    }

    public LocalDate getRaceDate() {
        return raceDate;
    }


    public ArrayList<Formula1Driver> getRacePositions() {
        return this.racePositions;
    }

    public void addRacePositions(Formula1Driver racePosition) {
        this.racePositions.add(racePosition);

    }

}
