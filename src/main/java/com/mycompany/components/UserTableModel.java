/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.components;

import com.mycompany.sadengamesmedia.model.Sale;
import com.mycompany.sadengamesmedia.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author denia
 */
public class UserTableModel extends AbstractTableModel{
    private  List<User> users = new ArrayList<>();
    private final String[] accountsCollumNames = {"account_id", "username", "email", "password", "role", "created_at"};
    
    public UserTableModel(List<User> users) {
        this.users = users;
    }
    
    
    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
         return accountsCollumNames.length;
    }
    
     @Override
    public String getColumnName(int column) {
        return accountsCollumNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = users.get(rowIndex);
        switch (columnIndex) {
            case 0: return user.getUserId();
            case 1: return user.getUsername();     
            case 2: return user.getEmail();
            case 3: return user.getPassword();  
            case 4: return user.getRole();
            case 5: return user.getTimestamp();
            default: return null;
        }
    }
}
