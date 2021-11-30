import java.io.Serializable;

public class Formula1Driver extends Driver implements Serializable {
    private static final long serialVersionUID = 2L;
    private int firstPositions;
    private int secondPositions;
    private int thirdPositions;
    private int numberOfPoints;
    private int numberOfRaces;
    public Formula1Driver(String driverName, String location, String teamName, int height, int age,
                          String countryOfOrigin, int firstPositions, int secondPositions, int thirdPositions, int numberOfPoints, int numberOfRaces) {
        super(driverName, location, teamName, height, age, countryOfOrigin);
        this.firstPositions = firstPositions;
        this.secondPositions = secondPositions;
        this.thirdPositions = thirdPositions;
        this.numberOfPoints = numberOfPoints;
        this.numberOfRaces = numberOfRaces;
    }


    public void setNumberOfPoints(int position) {
        int points=0;
        switch (position) {
            case 1:
                points = 25;
                this.firstPositions++;
                break;
            case 2:
                points = 18;
                this.secondPositions++;
                break;
            case 3:
                points = 15;
                thirdPositions++;
                break;
            case 4:
                points = 12;
                break;
            case 5:
                points = 10;
                break;
            case 6:
                points = 8;
                break;
            case 7:
                points = 6;
                break;
            case 8:
                points = 4;
                break;
            case 9:
                points = 2;
                break;
            case 10:
                points = 1;
                break;
        }
        this.numberOfPoints += points;
    }

    public int getFirstPositions() {
        return firstPositions;
    }

    public int getSecondPositions() {
        return secondPositions;
    }

    public int getThirdPositions() {
        return thirdPositions;
    }

    public int getNumberOfPoints() {
        return numberOfPoints;
    }

    public int getNumberOfRaces() {
        return numberOfRaces;
    }


}
