package w1830255;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static F1ChampionshipManager F1C = new F1ChampionshipManager();
    public static void main(String[] args)  {
        String input;
        readFromFile();
        boolean done = false;
        F1C.menu();

        while (!done) {
            System.out.println("Enter code: ");
            input = scanner.nextLine();
            if (input.isEmpty()) {
                System.out.println("Try again");
            }
            switch(input.toUpperCase()) {
                case "1": case "A":
                    F1C.addToChampionship(newF1Driver());
                    saveToFile(F1C);
                    break;
                case "2": case "B":
                    System.out.println("Enter name of driver to remove or [0] to exit");
                    String imp = scanner.nextLine();
                    if (imp.equals("0")) {
                        continue;
                    }
                    F1C.removeFromChampionship(imp);
                    saveToFile(F1C);
                    break;
                case "3": case "C":
                    F1C.displayDrivers();
                    System.out.println("Name of driver with you wish to replace.");
                    String driverName = scanner.nextLine();
                    Formula1Driver oldDriver = F1C.findDriver(driverName);
                    if (oldDriver != null) {
                        Formula1Driver newDriver = newF1Driver(oldDriver.getTeamName());
                        if (F1C.isDriver(newDriver.getDriverName())) {
                            F1C.changeDriver(oldDriver,newDriver);
                            saveToFile(F1C);
                            break;
                        }
                        System.out.println(newDriver.getDriverName() + " is already a driver");

                    }
                    else {
                        System.out.println("w1830255.Driver not in championship");
                    }
                    break;
                case "4": case "D":
                    F1C.displayDrivers();
                    System.out.println("Enter w1830255.Driver Name");
                    String name = scanner.nextLine();
                    boolean extra  = F1C.displayStatisticsOfDriver(name);
                    String b = "";
                    if (extra) {
                        System.out.println("Would you like to see their personal details? [Y,N]");
                        b = scanner.nextLine();
                    }
                    if (b.equalsIgnoreCase("Y")) {
                        F1C.displayPersonalDetails(name);
                    }
                    break;
                case "5": case "E":
                    F1C.displayChampionshipStatistics();
                    break;

                case "6": case "F":
                    Race newRace = newRace();
                    if (!(newRace==null)) {
                        F1C.addRace(newRace);
                    }
                    saveToFile(F1C);

                    break;
                case "7": case "G":
//                   SwingGui gui =  new SwingGui("Formula 1 Championship Manager", F1C);
                    F1C.displayRaces();
                    Gui gui =  new Gui("Formula 1 Championship Manager",F1C);
                   gui.setVisible(true);
                    break;
                case "8": case "h":

                    F1C.displayRaces();
                    break;
                case "Z": case "EXT":
                    saveToFile(F1C);
                    done = true;
                    break;
                case "M": case "MENU":
                    F1C.menu();
                default:
                    System.out.println("Enter a valid input");
            }
        }
    }

    private static Formula1Driver newF1Driver() {
        System.out.println("Enter w1830255.Driver Name");
        String driverName = scanner.nextLine();
        System.out.println("Enter Drivers location");
        String location = scanner.nextLine();
        System.out.println("Enter Drivers Team name");
        String teamName = scanner.nextLine();
        System.out.println("Enter drivers height");
        int height = intVal();
        System.out.println("Enter Drivers age");
        int age = intVal();
        System.out.println("Enter Country of Origin");
        String CoV = scanner.nextLine();
        return new Formula1Driver(driverName,location,
                teamName,height,age,CoV);



    }
    private static Formula1Driver newF1Driver(String teamName) {
        System.out.println("Enter w1830255.Driver Name");
        String driverName = scanner.nextLine();
        System.out.println("Enter Drivers location");
        String location = scanner.nextLine();
        System.out.println("Enter drivers height");
        int height = intVal();
        System.out.println("Enter Drivers age");
        int age = intVal();
        System.out.println("Enter Country of Origin");
        String CoV = scanner.nextLine();
        return new Formula1Driver(driverName,location,
                teamName,height,age,CoV);



    }

    private static Race newRace() {
        System.out.println("What is the day of race?");
        int day = dayVal();
        System.out.println("What is the month of race?");
        int month = monthVal();
        System.out.println("What is the year of race?");
        int year = scanner.nextInt();
        scanner.nextLine();

        LocalDate date = LocalDate.of(year,month,day);
        Race race = new Race(date);

        F1C.displayDrivers();
        for (int i=1;i<=F1C.getNumberOfDrivers();i++) {

            System.out.println("Enter drivers name at place: " + i);
            String name =scanner.nextLine();
            Formula1Driver driver = F1C.findDriver(name);
            if (!(driver==null)) {
                race.addRacePositions(driver);
            }
            else {
                System.out.println("Entered driver name isn't a valid driver, Exiting");
                return null;
            }

        }
        return race;

    }
    public static void  saveToFile(F1ChampionshipManager formula1) {
        String filename = "./src/w1830255/f1.ser";
        try {
//            Saving object in file
            FileOutputStream save = new FileOutputStream(filename);
            ObjectOutputStream outputStream = new ObjectOutputStream(save);
//            serializing the object
            outputStream.writeObject(formula1);

            save.close();
            outputStream.close();
            System.out.println("File written");

        } catch (IOException exception)
        {
            System.out.println("Io exception");
        }
    }
    public static void readFromFile()  {
//        w1830255.F1ChampionshipManager  = null;
        try {
            FileInputStream file = new FileInputStream("./src/w1830255/f1.ser");

            ObjectInputStream inputStream = new ObjectInputStream(file);
            F1C = (F1ChampionshipManager) inputStream.readObject();
            file.close();
            inputStream.close();
            System.out.println("File loaded");
        }
        catch (IOException | ClassNotFoundException exception) {
            System.out.println("No Save present");
        }

    }

    //    Number validation
    public static int intVal() {
        while (!scanner.hasNextInt()) {
            System.out.println("Enter an integer: ");
            scanner.next();
        }
        int num = scanner.nextInt();
        if (num < 0 ) {
            System.out.println("Enter number in range.");
        }
        scanner.nextLine();
        return num >= 0 ? num : intVal();
    }
    //day validation
    public static int dayVal() {
        while (!scanner.hasNextInt()) {
            System.out.println("Enter an integer: ");
            scanner.next();
        }
        int num = scanner.nextInt();
        if (num > 30 ) {
            System.out.println("Enter number in range.");
        }
        scanner.nextLine();
        return num >= 1 ? num : intVal();
    }
    //month validation
    public static int monthVal() {
        while (!scanner.hasNextInt()) {
            System.out.println("Enter an integer: ");
            scanner.next();
        }
        int num = scanner.nextInt();
        if (num >12 ) {
            System.out.println("Enter number in range.");
        }
        scanner.nextLine();
        return num >= 1 ? num : intVal();
    }



}
