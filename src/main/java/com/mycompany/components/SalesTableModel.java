/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.components;

import com.mycompany.sadengamesmedia.model.Sale;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author denia
 */
public class SalesTableModel extends AbstractTableModel{
    private  List<Sale> sales = new ArrayList<>();
    //private final List<User> users = new ArrayList<>();
    private final String[] columnNames = { "sale_id", "product_id", "account_id", "quantity", "price", "total_amount", "sale_timestamp" };
   // private final String[] accountsCollumNames = {"account_id", "username", "email", "password", "role", "created_at"};
    
    public SalesTableModel(List<Sale> sales) {
        this.sales = sales;
    }
    
    
    @Override
    public int getRowCount() {
        return sales.size();
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
        Sale sale = sales.get(rowIndex);
        switch (columnIndex) {
            case 0: return sale.getSaleId();
            case 1: return sale.getProductId();     
            case 2: return sale.getAccountId();
            case 3: return sale.getQuantity();  
            case 4: return sale.getPrice();
            case 5: return sale.getTotalAmount();
            case 6: return sale.getTimeStamp();
            default: return null;
        }
    }
}
