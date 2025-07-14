/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sadengamesmedia;

import com.mycompany.components.IconButton;
import com.mycompany.components.SalesTableModel;
import com.mycompany.components.UserTableModel;
import com.mycompany.sadengamesmedia.model.Sale;
import com.mycompany.sadengamesmedia.model.User;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
public class UsersPanel extends JPanel{
    
    public UsersPanel(){
        initComponents();
    }
    
    private void initComponents(){
        setLayout(null);
        
        setBackground(new Color(10,15,20));
        
        dataLoader();
        userModel = new UserTableModel(userRowData);
        
        usersTable = new JTable(userModel);
        usersTable.setBackground(new Color(20,15,40));
        usersTable.setFont(new Font("Segoe UI", Font.PLAIN, 16)); 
        usersTable.setRowHeight(24);
        usersTable.setGridColor(new Color(50, 50, 70));
        
        JTableHeader header = usersTable.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 16));           
        header.setForeground(Color.YELLOW);                             
        header.setBackground(new Color(60, 60, 120));
        
        //Columns width
        TableColumnModel columnModel = usersTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(150); 
        columnModel.getColumn(1).setPreferredWidth(200);
        columnModel.getColumn(2).setPreferredWidth(230);
        columnModel.getColumn(3).setPreferredWidth(180);
        columnModel.getColumn(4).setPreferredWidth(150);
        columnModel.getColumn(5).setPreferredWidth(250);
        
        //Columns foreground 
        DefaultTableCellRenderer colorRenderer = new DefaultTableCellRenderer();
        colorRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        colorRenderer.setForeground(Color.CYAN);
        for (int i = 0; i < usersTable.getColumnCount(); i++) {
            usersTable.getColumnModel().getColumn(i).setCellRenderer(colorRenderer);
            columnModel.getColumn(i).setResizable(false);
        }
        usersTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        tableScroll = new JScrollPane(usersTable);
        tableScroll.getViewport().setBackground(new Color(20, 15, 40));
        tableScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tableScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        tableScroll.getVerticalScrollBar().setUnitIncrement(16);
        tableScroll.setBorder(null);
        tableScroll.setVisible(true);
        tableScroll.setBounds(10, 280, 1160, 300);
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
        
        userSearchLabel.setBounds(800, 50, 150, 30);                     
        userSearchLabel.setVisible(true);
        userSearchLabel.setForeground(new Color(102, 100, 204));
        userSearchLabel.setFont(new Font("Courier New", Font.BOLD, 25));
        add(userSearchLabel);
        
        
        userSearchText.setBounds(950, 50, 100, 30);
        userSearchText.setBackground(new Color(10,25,40));
        userSearchText.setForeground(Color.CYAN);
        userSearchText.setFont(new Font("Courier New", Font.ITALIC, 15));
        userSearchText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        userSearchText.setVisible(true);
        userSearchText.addFocusListener(searchBarFocusListener(userSearchText));
        add(userSearchText);
        
        
        userSearchButton.setBounds(1050, 50, 30, 30);
        userSearchButton.setVisible(true);
        userSearchButton.setOpaque(true);                     
        userSearchButton.setContentAreaFilled(true);           
        userSearchButton.setBorderPainted(false);
        userSearchButton.setFocusPainted(false);
        userSearchButton.setBackground(new Color(10,25,40));
        userSearchButton.addMouseListener(userFilter());
        add(userSearchButton);
        
        dateLabel.setBounds(750, 150, 200, 30);                     
        dateLabel.setVisible(true);
        dateLabel.setForeground(new Color(102, 100, 204));
        dateLabel.setFont(new Font("Courier New", Font.BOLD, 25));
        add(dateLabel);
        
        
        sortByDate.setBounds(950,150,120,30);
        sortByDate.setVisible(true);
        sortByDate.setFont(new Font("Arial", Font.BOLD ,15));
        sortByDate.setBackground(new Color(30, 35, 60));
        sortByDate.setForeground(Color.WHITE);
        sortByDate.addActionListener(dateSort());
        add(sortByDate);
        
        
        rangeLabel.setBounds(100, 60, 100, 30);                     
        rangeLabel.setVisible(true);
        rangeLabel.setForeground(new Color(102, 100, 204));
        rangeLabel.setFont(new Font("Courier New", Font.BOLD, 30));
        add(rangeLabel);
        
        
        afterDateLabel.setBounds(20, 140, 250, 30);                     
        afterDateLabel.setVisible(true);
        afterDateLabel.setForeground(new Color(102, 100, 204));
        afterDateLabel.setFont(new Font("Courier New", Font.BOLD, 25));
        add(afterDateLabel);
        
        
        beforeDateLabel.setBounds(20, 190, 250, 30);                     
        beforeDateLabel.setVisible(true);
        beforeDateLabel.setForeground(new Color(102, 100, 204));
        beforeDateLabel.setFont(new Font("Courier New", Font.BOLD, 25));
        add(beforeDateLabel);
        
        
        afterDateText.setBounds(250, 140, 130, 30);
        afterDateText.setBackground(new Color(10,25,40));
        afterDateText.setForeground(Color.CYAN);
        afterDateText.setFont(new Font("Courier New", Font.ITALIC, 20));
        afterDateText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        afterDateText.setVisible(true);
        add(afterDateText);
        
        
        beforeDateText.setBounds(250, 190, 130, 30);
        beforeDateText.setBackground(new Color(10,25,40));
        beforeDateText.setForeground(Color.CYAN);
        beforeDateText.setFont(new Font("Courier New", Font.ITALIC, 20));
        beforeDateText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        beforeDateText.setVisible(true);
        add(beforeDateText);
        
        
        applyRangeButton.setVisible(true);
        applyRangeButton.setForeground(Color.CYAN);
        applyRangeButton.setFont(new Font("Arial", Font.BOLD, 15));
        applyRangeButton.setBackground(new Color(10,25,40));
        applyRangeButton.setOpaque(true);
        applyRangeButton.setContentAreaFilled(true);           
        applyRangeButton.setBorderPainted(false);
        applyRangeButton.setFocusPainted(false);
        applyRangeButton.setBounds(400, 160, 150, 40);
        applyRangeButton.addMouseListener(dateRangeFilter());
        add(applyRangeButton);
        
        
        
        
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
        
        sorter = new TableRowSorter<>(usersTable.getModel());
        usersTable.setRowSorter(sorter);
        
        for (int i = 0; i < usersTable.getColumnCount(); i++) {
            sorter.setSortable(i, false);
        }
        
    }
    
    
    private MouseAdapter deleteAction() {
        return new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = usersTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a row to delete.");
                    return;
                }
                int modelRow = usersTable.convertRowIndexToModel(selectedRow);
                User userToDelete = userRowData.get(modelRow);

                int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete sale with ID " + userToDelete.getUserId() + "?",
                        "Confirm Delete", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        Connection conn = DatabaseManager.getConnection();
                        PreparedStatement saleStmt = conn.prepareStatement("DELETE FROM sales WHERE account_id = ?");
                        saleStmt.setInt(1, userToDelete.getUserId());
                        saleStmt.executeUpdate();
                        PreparedStatement stmt = conn.prepareStatement("DELETE FROM accounts WHERE account_id = ?");
                        stmt.setInt(1, userToDelete.getUserId());
                        int affectedRows = stmt.executeUpdate();

                        if (affectedRows > 0) {
                            userRowData.remove(modelRow);
                            userModel.fireTableRowsDeleted(modelRow, modelRow);
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
    
    
    
    private MouseAdapter refreshAction(){
        return new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                userRowData.clear();
                dataLoader();
                userModel.fireTableDataChanged();
            }
        };
    }
    
    private MouseAdapter dateRangeFilter() {
    return new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            String afterDateStr = afterDateText.getText().trim();
            String beforeDateStr = beforeDateText.getText().trim();

            if (afterDateStr.isEmpty() && beforeDateStr.isEmpty()) {
                sorter.setRowFilter(null);
                return;
            }

            LocalDate afterDate = null;
            LocalDate beforeDate = null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            try {
                if (!afterDateStr.isEmpty()) {
                    afterDate = LocalDate.parse(afterDateStr, formatter);
                }
                if (!beforeDateStr.isEmpty()) {
                    beforeDate = LocalDate.parse(beforeDateStr, formatter);
                }
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(null, "Invalid date format. Use dd-MM-yyyy.");
                return;
            }
            LocalDate finalAfterDate = afterDate;
            LocalDate finalBeforeDate = beforeDate;
            sorter.setRowFilter(new RowFilter<TableModel, Integer>() {
                @Override
                public boolean include(Entry<? extends TableModel, ? extends Integer> entry) {
                    try {
                        String timestampStr = entry.getStringValue(5);
                        LocalDate entryDate = LocalDateTime.parse(timestampStr).toLocalDate();

                        if (finalAfterDate != null && entryDate.isBefore(finalAfterDate)) return false;
                        if (finalBeforeDate != null && entryDate.isAfter(finalBeforeDate)) return false;

                        return true;
                    } catch (Exception ex) {
                        return false;
                    }
                }
            });
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
                            sorter.setSortKeys(List.of(new RowSorter.SortKey(5, SortOrder.DESCENDING)));
                            break;
                        case "Oldest":
                            sorter.setSortKeys(List.of(new RowSorter.SortKey(5, SortOrder.ASCENDING)));
                            break;
                        default:
                            sorter.setSortKeys(null);
                    }
                }
            }
        };
    }
    
    private MouseAdapter userFilter(){
        return new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                
                if(userSearchText.getText().isEmpty() || userSearchText.getText().toLowerCase().equals("user id")){
                    sorter.setRowFilter(null);
                }else{
                    applyIDFilter(userSearchText.getText(),0);
                    userSearchText.setText("User id");
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
    
    private FocusListener searchBarFocusListener(JTextField a){
        return new FocusListener(){
            String string;
        @Override
            public void focusGained(FocusEvent e) {
                if (a.getText().toLowerCase().equals("user id")) {
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
    
    private void dataLoader(){
        userRowData.clear();
        try {
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement userStmt = conn.prepareStatement("SELECT * FROM accounts");
            ResultSet rs = userStmt.executeQuery();
            while(rs.next()){
                
                int userId = rs.getInt("account_id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String role = rs.getString("role");
                Timestamp ts = rs.getTimestamp("created_at") ;
                LocalDateTime timestamp = ts.toLocalDateTime();
                
                
                User user = new User(userId, username, email, password, role, timestamp);
                userRowData.add(user);
            }
        } catch (SQLException ex) {
            System.getLogger(FinancePanel.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    private JTable usersTable;
    private JScrollPane tableScroll;
    private List<User> userRowData = new ArrayList<>();
    private UserTableModel userModel;
    private JLabel userSearchLabel = new JLabel("User ID :");
    private JTextField userSearchText = new JTextField("User ID");
    private IconButton userSearchButton = new IconButton("images/searchBarIcon.png", 30, 30);
    private JLabel dateLabel = new JLabel("Sort by date ");
    private String[] dateSort = { "None", "Most recent" , "Oldest"};
    private JComboBox<String> sortByDate = new JComboBox<>(dateSort);
    private JLabel rangeLabel = new JLabel("Range");
    private JLabel afterDateLabel = new JLabel("After date :");
    private JLabel beforeDateLabel = new JLabel("Before date :");
    private JTextField afterDateText = new JTextField();
    private JTextField beforeDateText = new JTextField();
    private JButton deleteButton = new JButton("Delete");
    private JButton refreshButton = new JButton("Refresh");
    private JButton applyRangeButton = new JButton("Apply filter");
    private TableRowSorter<TableModel> sorter;
}
