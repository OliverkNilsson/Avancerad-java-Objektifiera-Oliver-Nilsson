import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private JPanel panel1;
    private JPanel bottomTop;
    private JPanel bottomBottom;
    private JTable table;
    private DefaultTableModel tableModel;

    GUI () {

        // Creating the frame and setting it to fullscreen
        setTitle("CSV & JSON viewer");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Creating the JTable to put the files into, and a JScrollPane so that if the files is large you will be able to scroll
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        add(panel1, BorderLayout.SOUTH);

        bottomTop = new JPanel();
        panel1.add(bottomTop, BorderLayout.SOUTH);

        bottomBottom = new JPanel();
        panel1.add(bottomBottom, BorderLayout.NORTH);

        // Creates a instans of the CSVLoader class
        CSVLoader csvLoader = new CSVLoader(tableModel);

        // Creates a instans if the JSONLoader class
        JSONLoader jsonLoader = new JSONLoader(tableModel);

        // Creating the button for loading the CSV-file
        JButton csvSelection = new JButton("Load CSV file");
        csvSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                csvLoader.loadCSVFile();
            }
        });
        bottomTop.add(csvSelection);

        // Doing the same for loading a JSON-file
        JButton jsonSelection = new JButton("Load JSON file");
        jsonSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jsonLoader.loadJSONFile();
            }
        });
        bottomBottom.add(jsonSelection);

        table.setAutoCreateRowSorter(true);

        setVisible(true);
    }
}
