import java.io.Serializable;
import java.util.Comparator;

public abstract class Driver implements  Serializable {
    private static final long serialVersionUID = 1L;
    private String driverName;
    private String location;
    private String teamName;
    private int height;
    private int age;
    private String CountryOfOrigin;

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getLocation() {
        return location;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getHeight() {
        return height;
    }

    public int getAge() {
        return age;
    }

    public String getCountryOfOrigin() {
        return CountryOfOrigin;
    }

    public Driver(String driverName, String location, String teamName, int height, int age, String countryOfOrigin) {
        this.driverName = driverName;
        this.location = location;
        this.teamName = teamName;
        this.height = height;
        this.age = age;
        CountryOfOrigin = countryOfOrigin;
    }


}
