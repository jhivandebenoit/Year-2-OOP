
import java.io.Serializable;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Comparator;


public class F1ChampionshipManager implements ChampionshipManager, Serializable {
    private static final long serialVersionUID = 3L;
    private ArrayList<Formula1Driver> driverList;
    private ArrayList<String> teamsList;
    private ArrayList<Race> races;

    public void addRace(Race race) {
        this.races.add(race);
        assignPoints(race);

    }

    public ArrayList<Formula1Driver> getDriverList() {
        return driverList;
    }

    public F1ChampionshipManager() {
        driverList = new ArrayList<>();
        teamsList = new ArrayList<>();
        races = new ArrayList<>();
    }

    public void menu() {
        System.out.println("A or 1 To add To Championship");
        System.out.println("B or 2 to remove from Championship");
        System.out.println("C or 3 to change driver ");
        System.out.println("D or 4 to display statistics for a driver");
        System.out.println("E or 5 to display information about championship");
        System.out.println("F or 6 to add race to championship");
        System.out.println("G or 7 to Show Gui");
        System.out.println("H or 8 to show races");
        System.out.println("Z or EXT to exit");

    }
    public void displayRaces() {
        for (Race r : this.getRaceList()) {
            System.out.println(r.getRacePositions());
        }
    }
    public void displayDrivers() {
        int count = 0;

        System.out.format("%-15s%-15s\n","Name","Count");
        for (Formula1Driver driver : driverList) {

            System.out.format("%-15s%-15s\n",driver.getDriverName(),count);

//            System.out.println("Name: " + driver.getDriverName() + "| " +count);
            count++;
        }
        System.out.println("Input number to select driver: ");
    }
    public void assignPoints(Race race) {
        for (String[] racer : race.getRacePositions()) {
            findDriver(racer[0]).setNumberOfPoints(Integer.parseInt(racer[1]));
        }
    }

    public void updateTeams() {
        teamsList.clear();
        for (Formula1Driver d : driverList) {
            if (!teamsList.contains(d.getTeamName())) {
                teamsList.add(d.getTeamName());
            }
        }
    }

    public Formula1Driver findDriver(String driverName) {
        for ( Formula1Driver d : driverList) {
            if (d.getDriverName().equalsIgnoreCase(driverName)) {
                return d;
            }

        }
        return null;
    }


    public int getNumberOfDrivers() {
        return driverList.size();
    }

    @Override
    public void addToChampionship(Driver driver) {
        Formula1Driver d = (Formula1Driver) driver;
        if (!teamsList.contains(d.getTeamName()) && !driverList.contains(d)) {
            driverList.add(d);
        }
        updateTeams();
//        driverList.sort(new sortByPoints());
    }

    @Override
    public void removeFromChampionship(String driverName) {
//        ListIterator<Formula1Driver> iterator = driverList.listIterator();
//        while (iterator.hasNext()) {
//            if (iterator.next().getDriverName().equals(name)) {
//                iterator.remove();
//            }
//        }


        driverList.removeIf(d -> d.getDriverName().equalsIgnoreCase(driverName));
        updateTeams();
    }
    @Override
    public void changeDriver(Driver oldDriver,Driver newDriver) {
        Formula1Driver od = (Formula1Driver) oldDriver;
        Formula1Driver nd = (Formula1Driver) newDriver;
        String teamName = oldDriver.getTeamName();
        removeFromChampionship(oldDriver.getDriverName());
        od.setTeamName(teamName);
        addToChampionship(nd);
    }

    @Override
    public boolean displayStatisticsOfDriver(String driverName) {

        Formula1Driver d = findDriver(driverName);
        if (!(d==null)) {
            System.out.format("%-30s%-25s%-25s%-25s%-25s\n","Driver Name","Team Name","First Positions","Second Positions", "Third Positions");
            System.out.format("%-30s%-25s%-25s%-25s%-25s\n",d.getDriverName(),d.getTeamName(),d.getFirstPositions(),d.getSecondPositions(),d.getThirdPositions());
            return true;
        }
        return false;

    }
    public void displayPersonalDetails(String driverName) {

        Formula1Driver d = findDriver(driverName);
        if (!(d==null)) {
            System.out.format("%-30s%-25s%-25s%-25s\n","Drivers Age","Drivers Height","Current Location","Country of Origin");
            System.out.format("%-30s%-25s%-25s%-25s\n",d.getAge(),d.getHeight(),d.getLocation(),d.getCountryOfOrigin());
        }
    }

    @Override
    public void displayChampionshipStatistics() {

        ArrayList<Formula1Driver> clone = new ArrayList<>(driverList);
        clone.sort(new sortByPoints());
        System.out.format("%-30s%-25s%-25s\n","Name","TeamName","NumberOfPoints");
        for (Formula1Driver d : clone) {

            System.out.format("%-30s%-25s%-25s\n",d.getDriverName(),d.getTeamName(),d.getNumberOfPoints());
//            System.out.println("Name: " + d.getDriverName() + "|TeamName: " + d.getTeamName() + "|NumberOfPoints:" + d.getNumberOfPoints());
        }
    }

    public ArrayList<Race> getRaceList() {
        return races;
    }

    static class sortByPoints implements Comparator<Formula1Driver> {
        public int compare(Formula1Driver a, Formula1Driver b) {
            return b.getNumberOfPoints()-a.getNumberOfPoints();
        }
    }
}
