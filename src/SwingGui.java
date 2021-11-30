import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.concurrent.ThreadLocalRandom;

public class SwingGui extends JFrame{
//    private SearchActionListener sal;
//    private JButton button7;
//    private JTextField textField1;



    public SwingGui(String Title,F1ChampionshipManager F1C) {
        super(Title);
        this.setSize(1200,800);
        this.setLocation(100,100);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        //Drivers code
//        Buttons

        JButton button3 = new JButton("Generate Race");
        JButton button4 = new JButton("Generate Race MOd");

        JButton button7 = new JButton("Search By Name");


        JTextField textField1 = new JTextField();


        // Calling table
        JTable mainTable;
        TableRowSorter<DefaultTableModel> sorter;
        DefaultTableModel tableModel;
        //          Table Config
        String[] columns = new String[]{"Name","Team Name","First Positions","Second Positions","Third Postions","Total Points","Total Races","Location","Height","Age","Country of Origin"};
//        Object[][] data = {{"John","Jeep","1","2","3","50","4","Sri lanka","180","20","Sri lanka"}};
        Object[][] data = {};

        tableModel = new DefaultTableModel(data,columns);

        for (Formula1Driver f1 : F1C.getDriverList()) {
//            tableModel.addRow(new Object[] {
//                    f1.getDriverName(),f1.getTeamName(),
//                    Integer.toString(f1.getFirstPositions()),
//                    Integer.toString(f1.getSecondPositions()),
//                    Integer.toString(f1.getThirdPositions()),
//                    Integer.toString(f1.getNumberOfPoints()),
//                    Integer.toString(f1.getNumberOfRaces()),
//                    f1.getLocation(),
//                    Integer.toString(f1.getHeight()),
//                    Integer.toString(f1.getAge()),
//                    f1.getCountryOfOrigin()
//            });
            tableModel.addRow(new Object[] {
                    f1.getDriverName(),
                    f1.getTeamName(),
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
        mainTable = new JTable(tableModel){
            @Override
            public Class getColumnClass(int column)
            {
                if (column == 3||column == 4||column == 5||column == 6||column == 7)
                    return Integer.class;
                else
                    return String.class;
            }
        };
        sorter = new TableRowSorter<>(tableModel);
//        sorter.setComparator(0, (Comparator<Integer>) (o1, o2) -> {
//            int len1 = o1.toString().length();
//            int len2 = o2.toString().length();
//            if (len1==len2) {
//                return o1.compareTo(o2);
//            } else {
//                return len1-len2;
//            }
//        });
        mainTable.setRowSorter(sorter);
        sorter.setRowFilter(new RowFilter() {
            @Override
            public boolean include(RowFilter.Entry entry) {
                Boolean value = (Boolean)entry.getValue(2);
                return value == null || value;
            }
        });
        sorter.setSortsOnUpdates(true);





        JTabbedPane tabbedPane = new JTabbedPane();




//        Container mainContainer = this.getContentPane();
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
        textField1.setPreferredSize(new Dimension( 550, 25 ));

        topPanel.add(textField1);
        topPanel.add(button7);



        //        Panel Middle
//        JPanel middlePanel = new JPanel();
//        middlePanel.setBorder(new LineBorder(Color.BLACK,3));
////        middlePanel.setLayout(new FlowLayout(4,4,4));
//        middlePanel.setBackground(Color.WHITE);
//        middlePanel.add(mainTable);
        mainContainer.add(new JScrollPane(mainTable),BorderLayout.CENTER);



        // Bottom Pane
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(new LineBorder(Color.BLACK,3));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setLayout(new FlowLayout(5));
        mainContainer.add(bottomPanel,BorderLayout.SOUTH);


        bottomPanel.add(button3);
        bottomPanel.add(button4);

        tabbedPane.add("Driver's tab",mainContainer);
        add(tabbedPane);

        // Race data code
        ArrayList<Race> races = F1C.getRaceList();
        JPanel racePanel = new JPanel();
        racePanel.setLayout(new FlowLayout());

//        // Race checkbox
//        String[] raceDateStrings = new String[races.size()];
//        for (int i=0;i<races.size();i++) {
//            raceDateStrings[i] = races.get(i).getRaceDate();
//        }
//        JComboBox<String> raceComboBox = new JComboBox<String>(raceDateStrings);
//        raceComboBox.setPreferredSize(new Dimension(100,20));
//        racePanel.add(raceComboBox);




//        //          Singular Race information
//        JPanel tablePanel = new JPanel();
//
//        String[] columnsR = new String[]{"Name","Position"};
////
//        Object[][] dataR = {};
//
//        DefaultTableModel tableModelR = new DefaultTableModel(data,columns);
//        JTable mainTableR = new JTable(tableModel);
//        for (Race race: F1C.getRaceList()) {
//            tableModel.addRow(new String[] {
//                    race.getRaceDate()
//            });
//        }
//        TableRowSorter<DefaultTableModel> sorterR = new TableRowSorter<>(tableModel);
//        mainTableR.setRowSorter(sorterR);
//        tablePanel.add(new JScrollPane(mainTableR));
//        racePanel.add(tablePanel);
//



//        JLabel jLabel = new JLabel(String.valueOf(raceDateStrings));
//        racePanel.add(jLabel);





        tabbedPane.add("Race panel",racePanel);
        button7.addActionListener(e -> newFilter(textField1,sorter));
        button3.addActionListener(e -> {
            mainContainer.remove(mainTable);
//            F1C.addRace(newRace(F1C.getDriverList()));
            updateTable(F1C);
            mainContainer.add(new JScrollPane(mainTable),BorderLayout.CENTER);

        });
    }
    private void updateTable(F1ChampionshipManager F1C) {

    }

    private void newFilter(JTextField TF1,TableRowSorter<DefaultTableModel> TRS) {
        RowFilter<DefaultTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(TF1.getText(),0);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        TRS.setRowFilter(rf);
    }
//    public Race newRace(ArrayList<Formula1Driver> drivers) {
//
//
////        Race race = new Race("Random");
//        ArrayList<String> repeatCheck = new ArrayList<>();
//        for (int i = 0;i<drivers.size();i++) {
//            repeatCheck.add(Integer.toString(i+1));
//        }
//        Collections.shuffle(repeatCheck);
//        for (int j = 0; j<drivers.size();j++) {
////            this.addRacePositions(new String[] {drivers.get(j).getDriverName(),repeatCheck.get(j)});
//            race.addRacePositions(new String[] {drivers.get(j).getDriverName(),repeatCheck.get(j)});
//
//        }
//        return race;
//    }
////    private class SearchActionListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent ae) {
//            newFilter(textField1,sorter);
//        }
//    }

//    public static void main(String[] args) {
//        SwingGui layout = new SwingGui("Formula 1 Championship");
//        layout.setVisible(true);
//    }



}


