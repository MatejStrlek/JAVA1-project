/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.view.model;

import hr.algebra.model.Person;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author matej.galic
 */
public class PersonTableModel extends AbstractTableModel {
    
    private static final String[] COLUMN_NAMES = {
        "Id", "Full name", "Role in movie"
    };
    
    private List<Person> people;

    public PersonTableModel(List<Person> people) {
        this.people = people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return people.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return people.get(rowIndex).getId();
            case 1:
                return people.get(rowIndex).getName();
            case 2:
                return people.get(rowIndex).getRole().name();
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
        }
        return super.getColumnClass(columnIndex); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
  
}
