package w1830255;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.time.LocalDate;
import java.util.*;

public class Gui extends JFrame {
    private F1ChampionshipManager F1C;
    public Gui(String title,F1ChampionshipManager F1manager) {
        super(title);
        this.setSize(1200,800);
        this.setLocation(100,100);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Drivers code
        //Init
        F1C = F1manager;
        JButton button1 = new JButton("Generate w1830255.Race");
        JButton button2 = new JButton("Generate w1830255.Race With Initial positions");
        JButton button3 = new JButton("Search By Name");
        JTextField input = new JTextField();
        JTable mainTable;
        TableRowSorter<DefaultTableModel> sorter;
        DefaultTableModel tableModel;

        //          Table Config
        String[] columns = new String[]{"Name","Team Name","First Positions","Second Positions","Third Positions","Total Points","Total Races","Location","Height","Age","Country of Origin"};
        Object[][] data = {};

        tableModel = new DefaultTableModel(data,columns) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch(columnIndex) {
                    case 0:case 1:case 7: case 10:
                        return String.class;
                    default:
                        return Integer.class;

                }
            }
        };

        updateTable(tableModel);

        mainTable = new JTable(tableModel);
        sorter = new TableRowSorter<>(tableModel);
        mainTable.setRowSorter(sorter);
        sorter.setSortsOnUpdates(true);

//        Design
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BorderLayout(8,6));
        mainContainer.setBackground(Color.WHITE);
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4,4,4,4,Color.WHITE));


//        Panel TOP
        JPanel topPanel = new JPanel();
        topPanel.setBorder(new LineBorder(Color.BLACK,3));
        topPanel.setBackground(Color.WHITE);
        topPanel.setLayout(new FlowLayout());
        mainContainer.add(topPanel,BorderLayout.NORTH);
        input.setPreferredSize(new Dimension( 550, 25 ));

        topPanel.add(input);
        topPanel.add(button3);

        //middle
        mainContainer.add(new JScrollPane(mainTable),BorderLayout.CENTER);

        // Bottom Pane
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(new LineBorder(Color.BLACK,3));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setLayout(new FlowLayout(5));
        mainContainer.add(bottomPanel,BorderLayout.SOUTH);


        bottomPanel.add(button1);
        bottomPanel.add(button2);

        tabbedPane.add("w1830255.Driver's tab",mainContainer);
        add(tabbedPane);


        // w1830255.Race data code
        ArrayList<Race> races = F1C.getRaceList();
        JPanel racePanel = new JPanel();

        racePanel.setLayout(new BorderLayout());
        String[] raceColumn = new String[]{"w1830255.Race Date","1st place"};
        DefaultTableModel raceModel = new DefaultTableModel(raceColumn,0);
        JTable raceDateTable = new JTable(raceModel);
        updateRaceTable(raceModel);
        racePanel.add(new JScrollPane(raceDateTable),BorderLayout.CENTER);
        tabbedPane.add("w1830255.Race panel",racePanel);


//        Onclick
        button1.addActionListener(e -> {
            F1C.addRace(newRace());
            tableModel.setRowCount(0);
            raceModel.setRowCount(0);
            updateTable(tableModel);
            updateRaceTable(raceModel);
        });
        button2.addActionListener(e -> {
            F1C.addRace(newGenRace());
            tableModel.setRowCount(0);
            raceModel.setRowCount(0);
            updateTable(tableModel);
            updateRaceTable(raceModel);
        });

        button3.addActionListener(e -> newFilter(input,sorter));


    }
    private void newFilter(JTextField TF1,TableRowSorter<DefaultTableModel> TRS) {
        RowFilter<DefaultTableModel, Object> rf = null;
        //If current string doesn't parse, it doesn't  update.
        try {
            rf = RowFilter.regexFilter(TF1.getText().toLowerCase(),0);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        TRS.setRowFilter(rf);
    }
    public Race newRace() {

            ArrayList<Formula1Driver> drivers = F1C.getDriverList();
        Race race = new Race(randomDate());
        ArrayList<Integer> repeatCheck = new ArrayList<>();
        for (int i = 0;i<drivers.size();i++) {
            repeatCheck.add(i);
        }
        Collections.shuffle(repeatCheck);
        for (int j = 0; j<drivers.size();j++) {
            race.addRacePositions(drivers.get(repeatCheck.get(j)));

        }
        return race;
    }
    public Race newGenRace() {

        ArrayList<Formula1Driver> drivers = new ArrayList<>(F1C.getDriverList());
        Collections.shuffle(drivers);
        Race race = new Race(randomDate());
        int winningNum = rAndBetween(0,racePool());
        int winningIndex;
        if (winningNum<=40) {

            winningIndex = 0;
        }
        else if (winningNum <= 70) {
            winningIndex = 1;
        }
        else if (winningNum <= 80) {
            winningIndex = 2;
        }
        else if (winningNum <= 90) {
            winningIndex = 3;
        }
        else if (winningNum <= 92) {
            winningIndex = 4;
        }
        else if (winningNum <= 94) {
            winningIndex = 5;
        }
        else if (winningNum <= 96) {
            winningIndex = 6;
        }
        else if (winningNum <= 98) {
            winningIndex = 7;
        }
        else {
            winningIndex = 8;
        }

        race.addRacePositions(drivers.get(winningIndex));
        ArrayList<Integer> repeatCheck = new ArrayList<>();
        for (int i = 0;i<drivers.size();i++) {
            repeatCheck.add(i);
        }
        Collections.shuffle(repeatCheck);
        for (int j = 0; j<drivers.size();j++) {
            if (j==winningIndex) {
                continue;
            }
            race.addRacePositions(drivers.get(j));
        }

        return race;


    }
    public  LocalDate randomDate() {
        int year = rAndBetween(2000,2021);
        int month = rAndBetween(1,12);
        int day;
        if (month==2) {
            if (isLeapYear(year)) {
                day = rAndBetween(1,28);
            }
            else {
                day = rAndBetween(1,29);
            }
        }
        else {
            day = rAndBetween(1,30);
        }

        return  LocalDate.of(year,month,day);

    }

    public static int rAndBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
    public void updateRaceTable(DefaultTableModel tableModel) {
        F1C.sortRaces();
        for (Race race : F1C.getRaceList()) {
            tableModel.addRow(new Object[] {
                    race.getRaceDate(),
                    race.getRacePositions().get(0).getDriverName()
            });
        }
    }

    public void updateTable(DefaultTableModel tableModel) {
        F1C.sortDescPoints();
        for (Formula1Driver f1 : F1C.getDriverList()) {
            tableModel.addRow(f1.formatForDriver());
        }
    }
    public int racePool() {
        int num =F1C.getDriverList().size();
        int out;
        switch(num) {
            case 1:
                out = 40;
                break;
            case 2:
                out = 70;
                break;
            case 3:
                out = 80;
                break;
            case 4:
                out = 90;
                break;
            case 5:
                out = 92;
                break;
            case 6:
                out = 94;
                break;
            case 7:
                out = 96;
                break;
            case 8:
                out = 98;
                break;
            default:
                out = 100;
                break;

        }
        return out;
    }
    public static boolean isLeapYear(int year) {
        if (year < 1 || year > 9999) {
            return false;
        }
        else if (year % 4 != 0 ) {
            return false;
        }
        else if (year % 100 != 0) {
            return true;
        }
        else return year % 400 == 0;
    }





}