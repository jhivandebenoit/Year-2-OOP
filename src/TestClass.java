import java.util.Scanner;

public class TestClass {
    public static void main(String[] args) {
        F1ChampionshipManager F1C = new F1ChampionshipManager();
        Formula1Driver d = new Formula1Driver("d","d","d",1,1,"d",1,1,1,1,1);
        F1C.addToChampionship(d);
        F1C.displayChampionshipStatistics();
    }
}
