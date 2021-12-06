package w1830255;

public interface ChampionshipManager {

    void addToChampionship(Driver driver);
    void removeFromChampionship(String driverName);
    void changeDriver(Driver oldDriver,Driver newDriver);
    boolean displayStatisticsOfDriver(String driverName);
    void displayChampionshipStatistics();

    }
