package edu.step.demo.gui.model;


import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author sscerbatiuc
 */
public class EmployeeTableModel extends AbstractTableModel {
    
    private List<Employee> list;
    private String[] columnNames = {"id", "name"};

    public EmployeeTableModel() {
        this.list = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0: return this.list.get(rowIndex).getId();
            case 1: return this.list.get(rowIndex).getName();
            default:
                throw new IllegalArgumentException("Cannot find column " + columnIndex);
        }
    }
    
    public void add(Employee emp){
        this.list.add(emp);
        this.fireTableDataChanged();
    }
    
    public Employee get(int row){
        return this.list.get(row);
    }
    
    public void update (int row, Employee edited){
        this.list.set(row, edited);
        this.fireTableDataChanged();
    }

    public void delete(int selectedRow) {
        this.list.remove(selectedRow);
        this.fireTableDataChanged();
    }
    
}
