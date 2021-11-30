import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Gui extends JFrame {
    public Gui(String title) {
        super(title);
        this.setSize(1200,800);
        this.setLocation(100,100);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Drivers code
        //Init

        JButton button1 = new JButton("Generate Race");
        JButton button2 = new JButton("Generate Race MOd");
        JButton button3 = new JButton("Search By Name");
        JTextField input = new JTextField();
        JTable mainTable;
        TableRowSorter<DefaultTableModel> sorter;
        DefaultTableModel tableModel;

        //          Table Config
        String[] columns = new String[]{"Name","Team Name","First Positions","Second Positions","Third Positions","Total Points","Total Races","Location","Height","Age","Country of Origin"};
//        Object[][] data = {{"John","Jeep","1","2","3","50","4","Sri lanka","180","20","Sri lanka"}};
        Object[][] data = {};

        tableModel = new DefaultTableModel(data,columns) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch(columnIndex) {
                    case 2:case 3:case 4:case 5:case 6: case 8: case 9:
                        return Integer.class;
                    default:
                        return String.class;

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

        tabbedPane.add("Driver's tab",mainContainer);
        add(tabbedPane);


        // Race data code
        ArrayList<Race> races = Main.F1C.getRaceList();
        JPanel racePanel = new JPanel();
        racePanel.setLayout(new FlowLayout());

//        // Race checkbox
//        String[] raceDateStrings = new String[races.size()];
//        for (int i=0;i<races.size();i++) {
//            raceDateStrings[i] = races.get(i).getRaceDate();
//        }
//        JLabel jLabel = new JLabel(raceDateStrings[0]);
//        racePanel.add(jLabel);





        tabbedPane.add("Race panel",racePanel);


//        Onclick
        button1.addActionListener(e -> {
            Main.F1C.addRace(newRace());
            tableModel.setRowCount(0);
            updateTable(tableModel);
        });

        button3.addActionListener(e -> newFilter(input,sorter));
//        input.addActionListener(e -> System.out.println("works"));


    }
    private void newFilter(JTextField TF1,TableRowSorter<DefaultTableModel> TRS) {
        RowFilter<DefaultTableModel, Object> rf = null;
        //If current string doesn't parse, it doesn't  update.
        try {
            rf = RowFilter.regexFilter(TF1.getText(),0);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        TRS.setRowFilter(rf);
    }
    public Race newRace() {

            ArrayList<Formula1Driver> drivers = Main.F1C.getDriverList();
        Race race = new Race(randomDate());
        ArrayList<String> repeatCheck = new ArrayList<>();
        for (int i = 0;i<drivers.size();i++) {
            repeatCheck.add(Integer.toString(i+1));
        }
        Collections.shuffle(repeatCheck);
        for (int j = 0; j<drivers.size();j++) {
//            this.addRacePositions(new String[] {drivers.get(j).getDriverName(),repeatCheck.get(j)});
            race.addRacePositions(new String[] {drivers.get(j).getDriverName(),repeatCheck.get(j)});

        }
        return race;
    }
    public void newGenRace() {

        ArrayList<Formula1Driver> drivers = new ArrayList<>(Main.F1C.getDriverList());
        Collections.shuffle(drivers);
        Race race = new Race(randomDate());
        ArrayList<Object[]> startingPositons = new ArrayList<>();

        for (int i = 0;i<drivers.size();i++) {
            startingPositons.add(new Object[]{(i+1),drivers.get(i).getDriverName()});
        }
        System.out.println(Arrays.toString(startingPositons.get(0)));


    }
    public  LocalDate randomDate() {

        return  LocalDate.of(rAndBetween(2000,2021),rAndBetween(1,12),rAndBetween(1,30));

    }

    public static int rAndBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

    public void updateTable(DefaultTableModel tableModel) {
        for (Formula1Driver f1 : Main.F1C.getDriverList()) {
            tableModel.addRow(new Object[] {
                    f1.getDriverName(),f1.getTeamName(),
                    f1.getFirstPositions(),
                    f1.getSecondPositions(),
                    f1.getThirdPositions(),
                    f1.getNumberOfPoints(),
                    f1.getNumberOfRaces(),
                    f1.getLocation(),
                    f1.getHeight(),
                    f1.getAge(),
                    f1.getCountryOfOrigin()
            });
        }
    }
    public double probabilityOnPosition(int position) {
        double num=0;
        switch(position) {
            case 1:
                num=0.4d;
                break;
            case 2:
                num=0.3d;
                break;
            case 3: case 4:
                num=0.1d;
                break;
            case 5:case 6:case 7:case 8:case 9:
                num=0.02d;
                break;
        }
        return num;
    }

//    public StringFor




}