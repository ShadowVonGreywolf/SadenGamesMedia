/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sadengamesmedia;

import com.mycompany.components.IconButton;
import com.mycompany.components.SalesTableModel;
import com.mycompany.sadengamesmedia.model.Sale;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author denia
 */
public class FinancePanel extends JPanel{
    
    
    public FinancePanel(){
        initComponents();
    }
    
    private void initComponents(){
        setLayout(null);
        setBackground(new Color(10,15,20));
        
        dataLoader();
        salesModel = new SalesTableModel(salesRowData);
        
        financeTable = new JTable(salesModel);
        financeTable.setBackground(new Color(20,15,40));
        financeTable.setFont(new Font("Segoe UI", Font.PLAIN, 16)); 
        financeTable.setRowHeight(24);
        financeTable.setGridColor(new Color(50, 50, 70));
        
        JTableHeader header = financeTable.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 16));           
        header.setForeground(Color.YELLOW);                             
        header.setBackground(new Color(60, 60, 120));
        
        //Columns width
        TableColumnModel columnModel = financeTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(150); 
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(150);
        columnModel.getColumn(4).setPreferredWidth(150);
        columnModel.getColumn(5).setPreferredWidth(160);
        columnModel.getColumn(6).setPreferredWidth(250);
        
        //Columns foreground 
        DefaultTableCellRenderer colorRenderer = new DefaultTableCellRenderer();
        colorRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        colorRenderer.setForeground(Color.CYAN);
        for (int i = 0; i < financeTable.getColumnCount(); i++) {
            financeTable.getColumnModel().getColumn(i).setCellRenderer(colorRenderer);
            columnModel.getColumn(i).setResizable(false);
        }
        financeTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        tableScroll = new JScrollPane(financeTable);
        tableScroll.getViewport().setBackground(new Color(20, 15, 40));
        tableScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tableScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        tableScroll.getVerticalScrollBar().setUnitIncrement(16);
        tableScroll.setBorder(null);
        tableScroll.setVisible(true);
        tableScroll.setBounds(10, 330, 1160, 300);
        tableScroll.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(0,0,130);
            }
        });
        tableScroll.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(80, 90, 130);      
                this.trackColor = new Color(30, 35, 60);        
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }
            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }
            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setVisible(false);
                return button;
            }
        });
        add(tableScroll);
        
        
        productSearchLabel.setBounds(700, 50, 150, 30);                     
        productSearchLabel.setVisible(true);
        productSearchLabel.setForeground(new Color(102, 100, 204));
        productSearchLabel.setFont(new Font("Courier New", Font.BOLD, 25));
        add(productSearchLabel);
        
        
        productSearchText.setBounds(850, 50, 110, 30);
        productSearchText.setBackground(new Color(10,25,40));
        productSearchText.setForeground(Color.CYAN);
        productSearchText.setFont(new Font("Courier New", Font.ITALIC, 15));
        productSearchText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        productSearchText.setVisible(true);
        productSearchText.addFocusListener(searchBarFocusListener(productSearchText));
        add(productSearchText);
        
        
        productSearchButton.setBounds(960, 50, 30, 30);
        productSearchButton.setVisible(true);
        productSearchButton.setOpaque(true);                     
        productSearchButton.setContentAreaFilled(true);           
        productSearchButton.setBorderPainted(false);
        productSearchButton.setFocusPainted(false);
        productSearchButton.setBackground(new Color(10,25,40));
        productSearchButton.addMouseListener(productFilter());
        add(productSearchButton);
        
        
        userSearchLabel.setBounds(400, 50, 150, 30);                     
        userSearchLabel.setVisible(true);
        userSearchLabel.setForeground(new Color(102, 100, 204));
        userSearchLabel.setFont(new Font("Courier New", Font.BOLD, 25));
        add(userSearchLabel);
        
        
        userSearchText.setBounds(550, 50, 100, 30);
        userSearchText.setBackground(new Color(10,25,40));
        userSearchText.setForeground(Color.CYAN);
        userSearchText.setFont(new Font("Courier New", Font.ITALIC, 15));
        userSearchText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        userSearchText.setVisible(true);
        userSearchText.addFocusListener(searchBarFocusListener(userSearchText));
        add(userSearchText);
        
        
        userSearchButton.setBounds(650, 50, 30, 30);
        userSearchButton.setVisible(true);
        userSearchButton.setOpaque(true);                     
        userSearchButton.setContentAreaFilled(true);           
        userSearchButton.setBorderPainted(false);
        userSearchButton.setFocusPainted(false);
        userSearchButton.setBackground(new Color(10,25,40));
        userSearchButton.addMouseListener(userFilter());
        add(userSearchButton);
        
        
        quantityLabel.setBounds(50, 50, 150, 30);                     
        quantityLabel.setVisible(true);
        quantityLabel.setForeground(new Color(102, 100, 204));
        quantityLabel.setFont(new Font("Courier New", Font.BOLD, 25));
        add(quantityLabel);
        
        
        sortByQuantity.setBounds(200,50,120,30);
        sortByQuantity.setVisible(true);
        sortByQuantity.setFont(new Font("Arial", Font.BOLD ,15));
        sortByQuantity.setBackground(new Color(30, 35, 60));
        sortByQuantity.setForeground(Color.WHITE);
        sortByQuantity.addActionListener(quantitySort());
        add(sortByQuantity);
        
        
        salesLabel.setBounds(50, 100, 150, 30);                     
        salesLabel.setVisible(true);
        salesLabel.setForeground(new Color(102, 100, 204));
        salesLabel.setFont(new Font("Courier New", Font.BOLD, 25));
        add(salesLabel);
        
        
        sortByDate.setBounds(200,100,120,30);
        sortByDate.setVisible(true);
        sortByDate.setFont(new Font("Arial", Font.BOLD ,15));
        sortByDate.setBackground(new Color(30, 35, 60));
        sortByDate.setForeground(Color.WHITE);
        sortByDate.addActionListener(dateSort());
        add(sortByDate);
        
        
        rangeLabel.setBounds(100, 150, 100, 30);                     
        rangeLabel.setVisible(true);
        rangeLabel.setForeground(new Color(102, 100, 204));
        rangeLabel.setFont(new Font("Courier New", Font.BOLD, 23));
        add(rangeLabel);
        
        
        totalAmountMinLabel.setBounds(20, 190, 250, 30);                     
        totalAmountMinLabel.setVisible(true);
        totalAmountMinLabel.setForeground(new Color(102, 100, 204));
        totalAmountMinLabel.setFont(new Font("Courier New", Font.BOLD, 15));
        add(totalAmountMinLabel);
        
        
        totalAmountMaxLabel.setBounds(20, 230, 250, 30);                     
        totalAmountMaxLabel.setVisible(true);
        totalAmountMaxLabel.setForeground(new Color(102, 100, 204));
        totalAmountMaxLabel.setFont(new Font("Courier New", Font.BOLD, 15));
        add(totalAmountMaxLabel);
        
        
        totalAmountMinText.setBounds(250, 190, 70, 30);
        totalAmountMinText.setBackground(new Color(10,25,40));
        totalAmountMinText.setForeground(Color.CYAN);
        totalAmountMinText.setFont(new Font("Courier New", Font.ITALIC, 15));
        totalAmountMinText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        totalAmountMinText.setVisible(true);
        add(totalAmountMinText);
        
        
        totalAmountMaxText.setBounds(250, 230, 70, 30);
        totalAmountMaxText.setBackground(new Color(10,25,40));
        totalAmountMaxText.setForeground(Color.CYAN);
        totalAmountMaxText.setFont(new Font("Courier New", Font.ITALIC, 15));
        totalAmountMaxText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        totalAmountMaxText.setVisible(true);
        add(totalAmountMaxText);
        
        
        applyRangeButton.setVisible(true);
        applyRangeButton.setForeground(Color.CYAN);
        applyRangeButton.setFont(new Font("Arial", Font.BOLD, 15));
        applyRangeButton.setBackground(new Color(10,25,40));
        applyRangeButton.setOpaque(true);
        applyRangeButton.setContentAreaFilled(true);           
        applyRangeButton.setBorderPainted(false);
        applyRangeButton.setFocusPainted(false);
        applyRangeButton.setBounds(100, 270, 150, 40);
        applyRangeButton.addMouseListener(rangeFilter());
        add(applyRangeButton);
        
        userIDLabel.setBounds(400, 110, 150, 30);                     
        userIDLabel.setVisible(true);
        userIDLabel.setForeground(new Color(102, 100, 204));
        userIDLabel.setFont(new Font("Courier New", Font.BOLD, 20));
        add(userIDLabel);
        
        
        userIDText.setBounds(530, 110, 100, 30);
        userIDText.setBackground(new Color(10,25,40));
        userIDText.setForeground(Color.CYAN);
        userIDText.setFont(new Font("Courier New", Font.ITALIC, 15));
        userIDText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        userIDText.setVisible(true);
        userIDText.setEditable(false);
        add(userIDText);
        
        
        usernameLabel.setBounds(400, 160, 150, 30);                     
        usernameLabel.setVisible(true);
        usernameLabel.setForeground(new Color(102, 100, 204));
        usernameLabel.setFont(new Font("Courier New", Font.BOLD, 20));
        add(usernameLabel);
        
        
        usernameText.setBounds(530, 160, 150, 30);
        usernameText.setBackground(new Color(10,25,40));
        usernameText.setForeground(Color.CYAN);
        usernameText.setFont(new Font("Courier New", Font.ITALIC, 15));
        usernameText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        usernameText.setVisible(true);
        usernameText.setEditable(false);
        add(usernameText);
        
        
        userEmailLabel.setBounds(400, 210, 150, 30);                     
        userEmailLabel.setVisible(true);
        userEmailLabel.setForeground(new Color(102, 100, 204));
        userEmailLabel.setFont(new Font("Courier New", Font.BOLD, 20));
        add(userEmailLabel);
        
        
        userEmailText.setBounds(530, 210, 150, 30);
        userEmailText.setBackground(new Color(10,25,40));
        userEmailText.setForeground(Color.CYAN);
        userEmailText.setFont(new Font("Courier New", Font.ITALIC, 15));
        userEmailText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        userEmailText.setVisible(true);
        userEmailText.setEditable(false);
        add(userEmailText);
        
        
        productIDLabel.setBounds(710, 110, 150, 30);                     
        productIDLabel.setVisible(true);
        productIDLabel.setForeground(new Color(102, 100, 204));
        productIDLabel.setFont(new Font("Courier New", Font.BOLD, 20));
        add(productIDLabel);
        
        
        productIDText.setBounds(860, 110, 100, 30);
        productIDText.setBackground(new Color(10,25,40));
        productIDText.setForeground(Color.CYAN);
        productIDText.setFont(new Font("Courier New", Font.ITALIC, 15));
        productIDText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        productIDText.setVisible(true);
        productIDText.setEditable(false);
        add(productIDText);
        
        
        productTitleLabel.setBounds(710, 160, 150, 30);                     
        productTitleLabel.setVisible(true);
        productTitleLabel.setForeground(new Color(102, 100, 204));
        productTitleLabel.setFont(new Font("Courier New", Font.BOLD, 20));
        add(productTitleLabel);
        
        
        productTitleText.setBounds(860, 160, 230, 30);
        productTitleText.setBackground(new Color(10,25,40));
        productTitleText.setForeground(Color.CYAN);
        productTitleText.setFont(new Font("Courier New", Font.ITALIC, 15));
        productTitleText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        productTitleText.setVisible(true);
        productTitleText.setEditable(false);
        add(productTitleText);
        
        
        productTypeLabel.setBounds(710, 210, 150, 30);                     
        productTypeLabel.setVisible(true);
        productTypeLabel.setForeground(new Color(102, 100, 204));
        productTypeLabel.setFont(new Font("Courier New", Font.BOLD, 20));
        add(productTypeLabel);
        
       
        productTypeText.setBounds(860, 210, 150, 30);
        productTypeText.setBackground(new Color(10,25,40));
        productTypeText.setForeground(Color.CYAN);
        productTypeText.setFont(new Font("Courier New", Font.ITALIC, 15));
        productTypeText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        productTypeText.setVisible(true);
        productTypeText.setEditable(false);
        add(productTypeText);
        
        
        refreshButton.setVisible(true);
        refreshButton.setForeground(Color.CYAN);
        refreshButton.setFont(new Font("Arial", Font.BOLD, 17));
        refreshButton.setBackground(new Color(10,25,40));
        refreshButton.setOpaque(true);
        refreshButton.setContentAreaFilled(true);           
        refreshButton.setBorderPainted(false);
        refreshButton.setFocusPainted(false);
        refreshButton.setBounds(800, 640, 150, 50);
        refreshButton.addMouseListener(refreshAction());
        add(refreshButton);
        
        
        deleteButton.setVisible(true);
        deleteButton.setForeground(Color.CYAN);
        deleteButton.setFont(new Font("Arial", Font.BOLD, 15));
        deleteButton.setBackground(new Color(90,25,40));
        deleteButton.setOpaque(true);
        deleteButton.setContentAreaFilled(true);           
        deleteButton.setBorderPainted(false);
        deleteButton.setFocusPainted(false);
        deleteButton.setBounds(970, 640, 150, 50);
        deleteButton.addMouseListener(deleteAction());
        add(deleteButton);
        
        sorter = new TableRowSorter<>(financeTable.getModel());
        financeTable.setRowSorter(sorter);
        
        for (int i = 0; i < financeTable.getColumnCount(); i++) {
            sorter.setSortable(i, false);
        }

    }
    
    public void applyRangeButtonTrue(){
        applyRangeButton.setVisible(true);
    }
    
    public void applyRangeButtonFalse(){
        applyRangeButton.setVisible(false);
    }
    
    private MouseAdapter rangeFilter(){
        return new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if(totalAmountMinText.getText().isEmpty() && totalAmountMaxText.getText().isEmpty())
                    sorter.setRowFilter(null);
                else{
                    Double min = null; Double max = null;
                    try {
                        if (!totalAmountMinText.getText().isEmpty()) {
                             min = Double.parseDouble(totalAmountMinText.getText());
                        }
                        if (!totalAmountMaxText.getText().isEmpty()) {
                             max = Double.parseDouble(totalAmountMaxText.getText());
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid number format for amount.");
                        return;
                    }
                    Double finalMin = min;
                    Double finalMax = max;
                    sorter.setRowFilter(new RowFilter<TableModel, Integer>() {
                        @Override
                        public boolean include(Entry<? extends TableModel, ? extends Integer> entry) {
                            try {
                                double amount = Double.parseDouble(entry.getStringValue(5));
                                if (finalMin != null && amount < finalMin) return false;
                                if (finalMax != null && amount > finalMax) return false;
                                return true;
                            } catch (NumberFormatException e) {
                                return false;
                            }
                        }
                    });
                    
                }
            }
        };
    }
    
    
    private MouseAdapter userFilter(){
        return new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                
                if(userSearchText.getText().isEmpty() || userSearchText.getText().toLowerCase().equals("user id")){
                    userIDText.setText("");
                    usernameText.setText("");
                    userEmailText.setText("");
                    sorter.setRowFilter(null);
                }else{
                    int userId = Integer.parseInt(userSearchText.getText());
                    applyIDFilter(userSearchText.getText(),2);
                    try (Connection conn = DatabaseManager.getConnection()) {
                    String sql = "SELECT a.username, a.email FROM sales s JOIN accounts a ON s.account_id = a.account_id WHERE s.account_id = ?";
                    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                        stmt.setInt(1, userId);
                        try (ResultSet rs = stmt.executeQuery()) {
                            if (rs.next()) {
                                userIDText.setText(String.valueOf(userId));
                                usernameText.setText(rs.getString("username"));
                                userEmailText.setText(rs.getString("email"));
                                userSearchText.setText("User id");
                            } else {
                                userIDText.setText("Not found");
                                usernameText.setText("Not found");
                                userEmailText.setText("Not found");
                                userSearchText.setText("User id");
                            }
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                    
                }
            }
        };
    }
    
     private MouseAdapter productFilter(){
        return new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if(productSearchText.getText().isEmpty() || productSearchText.getText().toLowerCase().equals("product id")){
                    productIDText.setText("");
                    productTitleText.setText("");
                    productTypeText.setText("");
                    sorter.setRowFilter(null);
                }else{
                    int productId = Integer.parseInt(productSearchText.getText());
                    applyIDFilter(productSearchText.getText(),1);
                    try (Connection conn = DatabaseManager.getConnection()) {
                    String sql = "SELECT p.title, p.product_type FROM sales s JOIN products p ON s.product_id = p.id WHERE s.product_id = ?";
                    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                        stmt.setInt(1, productId);
                        try (ResultSet rs = stmt.executeQuery()) {
                            if (rs.next()) {
                                productIDText.setText(String.valueOf(productId));
                                productTitleText.setText(rs.getString("title"));
                                productTypeText.setText(rs.getString("product_type"));
                                productSearchText.setText("Product id");
                            } else {
                                productIDText.setText("Not found");
                                productTitleText.setText("Not found");
                                productTypeText.setText("Not found");
                                productSearchText.setText("Product id");
                            }
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                }
            }
        };
    }
    
    private void applyIDFilter(String searchText, int columnIndex) {
            try {
                sorter.setRowFilter(RowFilter.regexFilter("^" + Pattern.quote(searchText) + "$", columnIndex));
            } catch (PatternSyntaxException ex) {
                sorter.setRowFilter(null);
            }
        }
    
    private ActionListener quantitySort(){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String selection = (String) sortByQuantity.getSelectedItem();

                if (selection != null) {
                    switch (selection) {
                        case "High to Low":
                            sorter.setSortKeys(List.of(new RowSorter.SortKey(3, SortOrder.DESCENDING)));
                            break;
                        case "Low to High":
                            sorter.setSortKeys(List.of(new RowSorter.SortKey(3, SortOrder.ASCENDING)));
                            break;
                        default:
                            sorter.setSortKeys(null);
                    }
                }
            }
        };
    }
    
    private ActionListener dateSort(){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String selection = (String) sortByDate.getSelectedItem();

                if (selection != null) {
                    switch (selection) {
                        case "Most recent":
                            sorter.setSortKeys(List.of(new RowSorter.SortKey(6, SortOrder.DESCENDING)));
                            break;
                        case "Oldest":
                            sorter.setSortKeys(List.of(new RowSorter.SortKey(6, SortOrder.ASCENDING)));
                            break;
                        default:
                            sorter.setSortKeys(null);
                    }
                }
            }
        };
    }
    
    private FocusListener searchBarFocusListener(JTextField a){
        return new FocusListener(){
            String string;
        @Override
            public void focusGained(FocusEvent e) {
                if (a.getText().toLowerCase().equals("user id") || a.getText().toLowerCase().equals("product id")) {
                    string = a.getText();
                    a.setText("");
                }
            }

        @Override
            public void focusLost(FocusEvent e) {
                if (a.getText().trim().isEmpty()) {
                    a.setText(string);
                }
            }
        };
    }
    
    private MouseAdapter refreshAction(){
        return new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                salesRowData.clear();
                dataLoader();
                salesModel.fireTableDataChanged();
            }
        };
    }
    
    
    private MouseAdapter deleteAction() {
        return new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = financeTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a row to delete.");
                    return;
                }
                int modelRow = financeTable.convertRowIndexToModel(selectedRow);
                Sale saleToDelete = salesRowData.get(modelRow);

                int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete sale with ID " + saleToDelete.getSaleId() + "?",
                        "Confirm Delete", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        Connection conn = DatabaseManager.getConnection();
                        PreparedStatement stmt = conn.prepareStatement("DELETE FROM sales WHERE sale_id = ?");
                        stmt.setInt(1, saleToDelete.getSaleId());
                        int affectedRows = stmt.executeUpdate();

                        if (affectedRows > 0) {
                            salesRowData.remove(modelRow);
                            salesModel.fireTableRowsDeleted(modelRow, modelRow);
                            JOptionPane.showMessageDialog(null, "Sale deleted successfully.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Sale could not be deleted.");
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
                    }
                }
            }
        };
    }

    
    
    
    private void dataLoader(){
        salesRowData.clear();
        try {
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement saleStmt = conn.prepareStatement("SELECT * FROM sales");
            ResultSet rs = saleStmt.executeQuery();
            while(rs.next()){
                
                int saleId = rs.getInt("sale_id");
                int productId = rs.getInt("product_id");
                int accountId = rs.getInt("account_id");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                double totalAmount = rs.getDouble("total_amount");
                Timestamp ts = rs.getTimestamp("sale_timestamp") ;
                LocalDateTime timestamp = ts.toLocalDateTime();
                
                Sale sale = new Sale(saleId, productId, accountId, quantity, price, totalAmount, timestamp);
                salesRowData.add(sale);
            }
        } catch (SQLException ex) {
            System.getLogger(FinancePanel.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    private JTable financeTable;
    private JScrollPane tableScroll;
    List<Sale> salesRowData = new ArrayList<>();
    private SalesTableModel salesModel;
    private JLabel productSearchLabel = new JLabel("Product :");
    private JTextField productSearchText = new JTextField("Product id");
    private IconButton productSearchButton = new IconButton("images/searchBarIcon.png", 30, 30);
    private JLabel userSearchLabel = new JLabel("User ID :");
    private JTextField userSearchText = new JTextField("User ID");
    private IconButton userSearchButton = new IconButton("images/searchBarIcon.png", 30, 30);
    private JLabel quantityLabel = new JLabel("Quantity ");
    private String[] quantitySort = { "None", "Low to High" , "High to Low"};
    private JComboBox<String> sortByQuantity = new JComboBox<>(quantitySort);
    private JLabel salesLabel = new JLabel("Sales ");
    private String[] dateSort = { "None", "Most recent" , "Oldest"};
    private JComboBox<String> sortByDate = new JComboBox<>(dateSort);
    private JLabel rangeLabel = new JLabel("Range");
    private JLabel totalAmountMinLabel = new JLabel("Minimum total amount :");
    private JLabel totalAmountMaxLabel = new JLabel("Maximum total amount :");
    private JTextField totalAmountMinText = new JTextField();
    private JTextField totalAmountMaxText = new JTextField();
    private JLabel userIDLabel = new JLabel("User ID");
    private JTextField userIDText = new JTextField();
    private JLabel usernameLabel = new JLabel("Username");
    private JTextField usernameText = new JTextField();
    private JLabel userEmailLabel = new JLabel("User email");
    private JTextField userEmailText = new JTextField();
    private JLabel productIDLabel = new JLabel(" Product ID");
    private JTextField productIDText = new JTextField();
    private JLabel productTitleLabel = new JLabel("  Title");
    private JTextField productTitleText = new JTextField();
    private JLabel productTypeLabel = new JLabel("Product type");
    private JTextField productTypeText = new JTextField();
    private JButton deleteButton = new JButton("Delete");
    private JButton refreshButton = new JButton("Refresh");
    private JButton applyRangeButton = new JButton("Apply filter");
    private TableRowSorter<TableModel> sorter;
    
}
