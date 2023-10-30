package StudiKasus;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class MyTableModel2 extends AbstractTableModel {
    private String[] columnNames = {"Nama", "Nomor HP", "Jenis Kelamin", "Alamat"};
    private ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

    public int getRowCount() {
        return data.size();
    }

 
    public int getColumnCount() {
        return columnNames.length;
    }


    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex).get(columnIndex);
    }

 
    public String getColumnName(int column) {
        return columnNames[column];
    }

    
    public void add(ArrayList<String> value) {
       
        data.add(value);
      
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

  public ArrayList<String> getRowData(int rowIndex) {
        return data.get(rowIndex);
    }

    public void removeRow(int rowIndex) {
        data.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

     public ArrayList<ArrayList<String>> getData() {
        return data;
    }

     public void remove(int row) {
        data.remove(row);
        fireTableRowsDeleted(row, row);
    }


}