/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.step.demo.gui.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author sscerbatiuc
 */
public class HelloWorldTableModel extends AbstractTableModel {

    // definim coloanele
    String[] columns = new String[]{"id", "name" /*...*/};
    // unde pastram datele?
    List<Employee> employeeList = new ArrayList<>();

    public void add(Employee emp) {
        this.employeeList.add(emp);
//        this.fireTableRowsInserted(this.employeeList.size() - 1,this.employeeList.size() - 1);
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return this.employeeList.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return this.columns[columnIndex];
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Employee emp = this.employeeList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return emp.getId();
            case 1:
                return emp.getName();
            default:
                throw new IllegalArgumentException("Configurare gresita.");
        }
    }

}
