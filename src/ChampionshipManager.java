public interface ChampionshipManager {

    void addToChampionship(Formula1Driver driver);
    void removeFromChampionship(String driverName);
    void changeDriver(Formula1Driver oldDriver,Formula1Driver newDriver);
    boolean displayStatisticsOfDriver(String driverName);
    void displayChampionshipStatistics();

    }
