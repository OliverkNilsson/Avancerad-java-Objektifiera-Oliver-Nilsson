import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVLoader {
    private DefaultTableModel tableModel;

    public CSVLoader (DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }
    public void loadCSVFile() {
        // Resets column and row count when I load the file
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);

        // Creating a ArrayList to store all the content from the CSV-file
        ArrayList<String[]> aryL = new ArrayList<>();

        // Loading the file and placing it in the aryL array
        try {
            File f = new File("src/sample.csv");
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] array = line.split(",");   // Adding a new String to the array when a "," appears
                aryL.add(array);
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("ERROR" + e.toString());
        }

        if (!aryL.isEmpty()) {  // If aryL is not empty start adding the aryL to the table
            for (int i = 0; i < aryL.get(0).length; i++) {
                tableModel.addColumn(aryL.get(0)[i]);
            }
        }
        for (String[] rowData : aryL) {
            tableModel.addRow(rowData);
        }
    }
}

