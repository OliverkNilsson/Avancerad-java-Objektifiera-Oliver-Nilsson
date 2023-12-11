import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class JSONLoader {
    private DefaultTableModel tableModel;

    public JSONLoader (DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }
    public void loadJSONFile() {
        // Resets column and row count when I load the file
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);

        // Creating a ArrayList to store all the content from the CSV-file
        ArrayList<String[]> aryL = new ArrayList<>();
        try {
            File json = new File("src/sample.json");
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(json);

            JsonNode columnsNode = rootNode.get(0);
            Iterator<JsonNode> columnIterator = columnsNode.elements();
            while (columnIterator.hasNext()) {
                tableModel.addColumn(columnIterator.next().asText());
            }

            for (int i = 0; i < rootNode.size(); i++) {
                JsonNode rowNode = rootNode.get(i);
                Iterator<JsonNode> rowIterator = rowNode.elements();
                ArrayList<String> rowData = new ArrayList<>();
                while (rowIterator.hasNext()) {
                    rowData.add(rowIterator.next().asText());
                }
                aryL.add(rowData.toArray(new String[0]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (!aryL.isEmpty()) {
            for (String[] rowData : aryL) {
                tableModel.addRow(rowData);
            }
    }
    }
}
