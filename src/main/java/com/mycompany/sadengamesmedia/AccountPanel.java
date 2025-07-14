/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sadengamesmedia;

import com.mycompany.components.IconButton;
import com.mycompany.components.LabelImage;
import com.mycompany.sadengamesmedia.model.Session;
import com.mycompany.sadengamesmedia.model.User;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author denia
 */
public class AccountPanel extends JPanel {
    SadenGamesMedia sgm;
    public AccountPanel(SadenGamesMedia sgm){
        this.sgm = sgm;
        initComponents();
    }
    
    private void initComponents(){
        setLayout(null);
        setBackground(new Color(10,15,20));
        
        
        accountImage= new LabelImage("images/myAccountIcon.png",300, 300);
        accountImage.setBounds(50, 50, 300, 300);
        accountImage.setVisible(true);
        add(accountImage);
        
        
        yourAccountLabel.setVisible(true);
        yourAccountLabel.setBounds(80, 380, 300, 50);
        yourAccountLabel.setFont(new Font("Courier New", Font.BOLD, 35));
        yourAccountLabel.setForeground(new Color(102, 100, 204));
        add(yourAccountLabel);
        
        
        usernameLabel.setVisible(true);
        usernameLabel.setBounds(400, 60, 200, 50);
        usernameLabel.setFont(new Font("Courier New", Font.BOLD, 30));
        usernameLabel.setForeground(new Color(102, 100, 204));
        add(usernameLabel);
        
        
        usernameText.setBounds(640, 60, 230, 50);
        usernameText.setBackground(new Color(10,25,40));
        usernameText.setForeground(Color.CYAN);
        usernameText.setFont(new Font("Courier New", Font.ITALIC, 20));
        usernameText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        usernameText.setVisible(true);
        usernameText.setEditable(false);
        add(usernameText);
        
        
        emailLabel.setVisible(true);
        emailLabel.setBounds(400, 140, 200, 50);
        emailLabel.setFont(new Font("Courier New", Font.BOLD, 30));
        emailLabel.setForeground(new Color(102, 100, 204));
        add(emailLabel);
        
        
        emailText.setBounds(640, 140, 230, 50);
        emailText.setBackground(new Color(10,25,40));
        emailText.setForeground(Color.CYAN);
        emailText.setFont(new Font("Courier New", Font.ITALIC, 20));
        emailText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        emailText.setVisible(true);
        emailText.setEditable(false);
        add(emailText);
        
        
        passwordLabel.setVisible(true);
        passwordLabel.setBounds(400, 220, 200, 50);
        passwordLabel.setFont(new Font("Courier New", Font.BOLD, 30));
        passwordLabel.setForeground(new Color(102, 100, 204));
        add(passwordLabel);
        
        
        passwordText.setBounds(640, 220, 230, 50);
        passwordText.setBackground(new Color(10,25,40));
        passwordText.setForeground(Color.CYAN);
        passwordText.setFont(new Font("Courier New", Font.ITALIC, 20));
        passwordText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        passwordText.setVisible(true);
        passwordText.setEditable(false);
        add(passwordText);
        
        
        passwordVisible.setVisible(true);
        passwordVisible.setBounds(885, 230, 30, 30);
        passwordVisible.setOpaque(false);                     
        passwordVisible.setContentAreaFilled(false);           
        passwordVisible.setBorderPainted(false);
        passwordVisible.setFocusPainted(false);
        passwordVisible.addMouseListener(showPasswordAction());
        add(passwordVisible);
        
        
        roleLabel.setVisible(true);
        roleLabel.setBounds(400, 300, 200, 50);
        roleLabel.setFont(new Font("Courier New", Font.BOLD, 30));
        roleLabel.setForeground(new Color(102, 100, 204));
        add(roleLabel);
        
        
        roleText.setBounds(640, 300, 230, 50);
        roleText.setBackground(new Color(10,25,40));
        roleText.setForeground(Color.CYAN);
        roleText.setFont(new Font("Courier New", Font.ITALIC, 20));
        roleText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        roleText.setVisible(true);
        roleText.setEditable(false);
        add(roleText);
        
        
        changePhoto.setVisible(true);
        changePhoto.setForeground(Color.CYAN);
        changePhoto.setFont(new Font("Arial", Font.BOLD, 15));
        changePhoto.setBackground(new Color(10,25,40));
        changePhoto.setOpaque(true);
        changePhoto.setContentAreaFilled(true);           
        changePhoto.setBorderPainted(false);
        changePhoto.setFocusPainted(false);
        changePhoto.setBounds(60, 450, 250, 50);
        changePhoto.addMouseListener(changePhotoAction());
        add(changePhoto);
        
        changePersonalData.setVisible(true);
        changePersonalData.setForeground(Color.CYAN);
        changePersonalData.setFont(new Font("Arial", Font.BOLD, 15));
        changePersonalData.setBackground(new Color(10,25,40));
        changePersonalData.setOpaque(true);
        changePersonalData.setContentAreaFilled(true);           
        changePersonalData.setBorderPainted(false);
        changePersonalData.setFocusPainted(false);
        changePersonalData.setBounds(900, 400, 250, 50);
        changePersonalData.addMouseListener(changePersonalDataAction());
        add(changePersonalData);
        
        saveChanges.setVisible(false);
        saveChanges.setForeground(Color.CYAN);
        saveChanges.setFont(new Font("Arial", Font.BOLD, 15));
        saveChanges.setBackground(new Color(10,80,40));
        saveChanges.setOpaque(true);
        saveChanges.setContentAreaFilled(true);           
        saveChanges.setBorderPainted(false);
        saveChanges.setFocusPainted(false);
        saveChanges.setBounds(1050, 470, 100, 50);
        saveChanges.addMouseListener(saveChangesAction());
        add(saveChanges);
        
        cancelChanges.setVisible(false);
        cancelChanges.setForeground(Color.CYAN);
        cancelChanges.setFont(new Font("Arial", Font.BOLD, 15));
        cancelChanges.setBackground(new Color(80,25,40));
        cancelChanges.setOpaque(true);
        cancelChanges.setContentAreaFilled(true);           
        cancelChanges.setBorderPainted(false);
        cancelChanges.setFocusPainted(false);
        cancelChanges.setBounds(900, 470, 100, 50);
        cancelChanges.addMouseListener(cancelChangesAction());
        add(cancelChanges);
        
        
        logoutButton.setVisible(true);
        logoutButton.setForeground(Color.CYAN);
        logoutButton.setFont(new Font("Arial", Font.BOLD, 20));
        logoutButton.setBackground(new Color(80,25,40));
        logoutButton.setOpaque(true);
        logoutButton.setContentAreaFilled(true);           
        logoutButton.setBorderPainted(false);
        logoutButton.setFocusPainted(false);
        logoutButton.setBounds(60, 610, 130, 50);
        logoutButton.addMouseListener(logoutAction());
        add(logoutButton);
        
    }
    public void setTextFieldsValues(){
        try{  
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM accounts WHERE account_id = ?");
            stmt.setInt(1, Session.getCurrentUser().getUserId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                usernameText.setText(rs.getString("username"));
                emailText.setText(rs.getString("email"));
                passwordText.setText(rs.getString("password"));
                roleText.setText(rs.getString("role"));
                } else {
                    System.out.println("problema cu datele");
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            } 

    }
    
    
    private MouseAdapter logoutAction(){
        return new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                sgm.showPanel("login");
                setVisible(false);
                Session.logout();
            }
        };
    }
    
    private MouseAdapter changePhotoAction(){
        return new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                FileDialog fileDialog = new FileDialog((Frame) null, "Select Profile Picture", FileDialog.LOAD);
                fileDialog.setVisible(true);
                String directory = fileDialog.getDirectory();
                String selectedFile = fileDialog.getFile();
                if (directory != null && selectedFile != null) 
                    if (selectedFile.endsWith(".jpg") || selectedFile.endsWith(".png") || selectedFile.endsWith(".jpeg")) {
                            File file = new File(directory, selectedFile);
                            try {
                                String imageDir = Paths.get(System.getProperty("user.dir"), "profile_images").toString();
                                String imageName = "user_" + Session.getCurrentUser().getUserId() + ".png";
                                String fullImagePath = Paths.get(imageDir, imageName).toString();
                                copyImageToFolder(file, imageDir, imageName);
                                
                                accountImage.setNewImage(fullImagePath, 300, 300);
                                accountImage.getParent().revalidate();
                                accountImage.getParent().repaint();
                                
                                String relativePath = "profile_images/" + imageName;
                                try (Connection conn = DatabaseManager.getConnection();
                                    PreparedStatement updateStmt = conn.prepareStatement("UPDATE accounts SET imagePath = ? WHERE account_id = ?")) {

                                    updateStmt.setString(1, relativePath);
                                    updateStmt.setInt(2, Session.getCurrentUser().getUserId());
                                    updateStmt.executeUpdate();
                                    Session.getCurrentUser().setImagePath(relativePath);
                                    
                                } catch (SQLException sqlEx) {
                                    sqlEx.printStackTrace();
                                }
                                
                            } catch (IOException ex) {
                                System.getLogger(AccountPanel.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                                ex.printStackTrace();
                            }
                    }
                }  
        };
    }
    public static void copyImageToFolder(File sourceImage, String destinationFolder, String newFileName) throws IOException {
        File destDir = new File(destinationFolder);
        if (!destDir.exists()) {
            destDir.mkdirs(); 
        }
        File destinationFile = new File(destDir, newFileName);
        Files.copy(sourceImage.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("imaginea a fost salvata ");
}
    
    
    
    private MouseAdapter saveChangesAction(){
        return new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                
                String username = usernameText.getText();
                String email = emailText.getText();
                String password = passwordText.getText();
                
                if(email.toLowerCase().contains("@gmail.com") || email.toLowerCase().contains("@yahoo.com")){
                    emailText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                    if(!confirmPasswordText.getText().equals(password)){
                        passwordText.setBorder(new LineBorder(Color.RED, 1));
                        confirmPasswordText.setBorder(new LineBorder(Color.RED, 1));
                        JOptionPane.showMessageDialog(null,"Error! Please enter the same password!","Account error",JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        try{  
                            Connection conn = DatabaseManager.getConnection();
                            PreparedStatement stmt = conn.prepareStatement("UPDATE accounts SET username = ?, email = ?, password = ? WHERE account_id = ?");
                            stmt.setString(1, username);
                            stmt.setString(2, email);
                            stmt.setString(3, password);
                            int userId = Session.getCurrentUser().getUserId();
                            stmt.setInt(4, userId);
                            String role = Session.getCurrentUser().getRole();
                            String imagePath = Session.getCurrentUser().getImagePath();
                            LocalDateTime timestamp = Session.getCurrentUser().getTimestamp();

                            int rowsUpdated = stmt.executeUpdate();
                            if (rowsUpdated > 0) {
                                System.out.println("Successful changing of data !");
                                passwordText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                                confirmPasswordText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                                JOptionPane.showMessageDialog(null,"Account updated!","Well done",JOptionPane.INFORMATION_MESSAGE);
                                User updatedUser = new User(userId, username, email, password, role, imagePath, timestamp);
                                Session.login(updatedUser);
                            }
                            }catch(SQLException ex){
                                passwordText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                                confirmPasswordText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                                JOptionPane.showMessageDialog(null,"Error! Account already in use!","Account error",JOptionPane.ERROR_MESSAGE);
                            }        
                        }
                }else{
                    emailText.setBorder(new LineBorder(Color.RED, 1));
                    JOptionPane.showMessageDialog(null,"Error! Invalid email credentials!","Account error",JOptionPane.ERROR_MESSAGE);
                }
                saveChanges.setVisible(false);
                cancelChanges.setVisible(false);
                roleText.setEditable(false);
                passwordText.setEditable(false);
                emailText.setEditable(false);
                usernameText.setEditable(false);
            }
        };
    }
    
    
    private MouseAdapter cancelChangesAction(){
        return new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if(!usernameText.getText().equals(Session.getCurrentUser().getUsername()))
                    usernameText.setText(Session.getCurrentUser().getUsername());
                if(!emailText.getText().equals(Session.getCurrentUser().getEmail()))
                    emailText.setText(Session.getCurrentUser().getEmail());
                if(!passwordText.getText().equals(Session.getCurrentUser().getPassword()))
                    passwordText.setText(Session.getCurrentUser().getPassword());
                
                saveChanges.setVisible(false);
                cancelChanges.setVisible(false);
                passwordText.setEditable(false);
                emailText.setEditable(false);
                usernameText.setEditable(false);
                confirmPasswordText.setVisible(false);
                confirmPasswordLabel.setVisible(false);
                confirmPasswordVisible.setVisible(false);
                roleLabel.setBounds(400, 300, 200, 50);
                roleText.setBounds(640, 300, 230, 50);
                
            }
        };
    }
    
    
    private MouseAdapter changePersonalDataAction(){
        return new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                saveChanges.setVisible(true);
                cancelChanges.setVisible(true);
                passwordText.setEditable(true);
                emailText.setEditable(true);
                usernameText.setEditable(true);
                
                confirmPasswordLabel.setVisible(true);
                confirmPasswordLabel.setBounds(400, 300, 200, 50);
                confirmPasswordLabel.setFont(new Font("Courier New", Font.BOLD, 30));
                confirmPasswordLabel.setForeground(new Color(102, 100, 204));
                add(confirmPasswordLabel);
                
                confirmPasswordText.setText(passwordText.getText());
                confirmPasswordText.setBounds(640, 300, 230, 50);
                confirmPasswordText.setBackground(new Color(10,25,40));
                confirmPasswordText.setForeground(Color.CYAN);
                confirmPasswordText.setFont(new Font("Courier New", Font.ITALIC, 20));
                confirmPasswordText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                confirmPasswordText.setVisible(true);
                add(confirmPasswordText);
                
                confirmPasswordVisible.setVisible(true);
                confirmPasswordVisible.setBounds(885, 310, 30, 30);
                confirmPasswordVisible.setOpaque(false);                     
                confirmPasswordVisible.setContentAreaFilled(false);           
                confirmPasswordVisible.setBorderPainted(false);
                confirmPasswordVisible.setFocusPainted(false);
                confirmPasswordVisible.addMouseListener(showConfirmedPasswordAction());
                add(confirmPasswordVisible);
                
                roleLabel.setBounds(400, 380, 200, 50);
                roleText.setBounds(640, 380, 230, 50);
                
            }
        };
    }
    private boolean confirmPasswordClicked = false;
    private MouseAdapter showConfirmedPasswordAction(){
        return new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                confirmPasswordClicked = !confirmPasswordClicked;
                if(confirmPasswordClicked){
                   confirmPasswordText.setEchoChar((char) 0); 
                   confirmPasswordVisible.setNewIcon("images/visibilityOn.png", 30, 30);
                }else{
                    confirmPasswordText.setEchoChar('*');
                    confirmPasswordVisible.setNewIcon("images/visibilityOff.png", 30, 30);
                }
                
                
            }
        };
    }
    
    private boolean passwordClicked = false;
    private MouseAdapter showPasswordAction(){
        return new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                passwordClicked = !passwordClicked;
                if(passwordClicked){
                   passwordText.setEchoChar((char) 0); 
                   passwordVisible.setNewIcon("images/visibilityOn.png", 30, 30);
                }else{
                    passwordText.setEchoChar('*');
                    passwordVisible.setNewIcon("images/visibilityOff.png", 30, 30);
                }
                
                
            }
        };
    }
    
    public void sideMenuOnSettings(){
        logoutButton.setVisible(false);
        changePhoto.setVisible(false);
    }
    
    public void sideMenuOffSettings(){
        logoutButton.setVisible(true);
        changePhoto.setVisible(true);
    }
    
    public void loadImageStartUp(String imagePath, int width , int height){
        accountImage.setNewImage(imagePath, width, height);
    }
    
    private JButton logoutButton = new JButton("Logout");
    private LabelImage accountImage;
    private JLabel yourAccountLabel = new JLabel("Your Account");
    private JLabel usernameLabel = new JLabel("Username : ");
    private JTextField usernameText = new JTextField("username");
    private JLabel emailLabel = new JLabel("Email : ");
    private JTextField emailText = new JTextField("email address");
    private JLabel passwordLabel = new JLabel("Password : ");
    private JPasswordField passwordText = new JPasswordField("password");
    private IconButton passwordVisible = new IconButton("images/visibilityOff.png", 30, 30);
    private IconButton confirmPasswordVisible = new IconButton("images/visibilityOff.png", 30, 30);
    private JLabel roleLabel = new JLabel("Role : ");
    private JTextField roleText = new JTextField("role");
    private JButton changePhoto = new JButton("Change profile photo");
    private JButton changePersonalData = new JButton("Change personal data");
    private JButton saveChanges = new JButton("Save");
    private JButton cancelChanges = new JButton("Cancel");
    private JLabel confirmPasswordLabel = new JLabel("Confirm : ");
    private JPasswordField confirmPasswordText = new JPasswordField("password");
    
}
