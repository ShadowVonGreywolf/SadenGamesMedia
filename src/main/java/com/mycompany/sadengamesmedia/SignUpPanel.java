/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sadengamesmedia;

import com.mycompany.components.IconButton;
import com.mycompany.components.ImagePanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author denia
 */
public class SignUpPanel extends JPanel{
    private SadenGamesMedia sgm;
    
    public SignUpPanel(SadenGamesMedia sgm){
        this.sgm = sgm;
        initComponents();        
    }
    
    private void initComponents(){
        setLayout(null);
        ImagePanel backgroundPanel = null;
        try {
            backgroundPanel = new ImagePanel("images/signUpBackground.png");
        } catch (IOException ex) {
            System.out.println("nu merge imaginea");
        } 
        backgroundPanel.setVisible(true);
        backgroundPanel.setBounds(0,0,1200,800);
        add(backgroundPanel);
        
        
        formPanel.setPreferredSize(new Dimension(500, 600));
        formPanel.setLayout(null); 
        formPanel.setVisible(true);
        formPanel.setBackground(new Color(0,0,0,255));
        backgroundPanel.add(formPanel);
        
        titleLabel.setBounds(70, 50, 400, 110);            //Title
        titleLabel.setVisible(true);
        titleLabel.setFont(new Font("Courier New", Font.BOLD ,60));
        titleLabel.setForeground(Color.WHITE);
        formPanel.add(titleLabel);
        
        usernameLabel.setBounds(30, 190, 200, 50);             //Username Label
        usernameLabel.setVisible(true);
        usernameLabel.setFont(new Font("Courier New", Font.ITALIC ,30));
        usernameLabel.setForeground(Color.WHITE);
        formPanel.add(usernameLabel);
        
        usernameText.setOpaque(false);                         //Username Text
        usernameText.setBounds(250, 190, 200, 40); 
        usernameText.setBackground(new Color(50,74,74, 1));
        usernameText.setFont(new Font("Courier New", Font.ITALIC, 20));
        usernameText.setBorder(BorderFactory.createCompoundBorder(
                    usernameText.getBorder(), 
                    new EmptyBorder(5, 10, 5, 10)  
                ));
        usernameText.setVisible(true);
        usernameText.addFocusListener(textFieldEmptier(usernameText));
        formPanel.add(usernameText);
        
        emailLabel.setBounds(30, 250, 160, 50);                     //Email Label
        emailLabel.setVisible(true);
        emailLabel.setFont(new Font("Courier New", Font.ITALIC ,30));
        emailLabel.setForeground(Color.WHITE);
        formPanel.add(emailLabel);
        
        emailText.setOpaque(false);                         //Email Text 
        emailText.setBounds(250, 250, 200, 40);
        emailText.setBackground(new Color(50,74,74, 1));
        emailText.setFont(new Font("Courier New", Font.ITALIC, 20));
        emailText.setBorder(BorderFactory.createCompoundBorder(
                    emailText.getBorder(), 
                    new EmptyBorder(5, 10, 5, 10)  
                ));
        emailText.setVisible(true);
        emailText.addFocusListener(textFieldEmptier(emailText));
        formPanel.add(emailText);
        
        passwordLabel.setBounds(30, 310, 210, 50);              //Password Label
        passwordLabel.setVisible(true);
        passwordLabel.setFont(new Font("Courier New", Font.ITALIC ,30));
        passwordLabel.setForeground(Color.WHITE);
        formPanel.add(passwordLabel);
        
        passwordText.setOpaque(false);                        //Password Text 
        passwordText.setBounds(250, 310, 200, 40);
        passwordText.setBackground(new Color(50,74,74, 1));
        passwordText.setFont(new Font("Courier New", Font.ITALIC, 20));
        passwordText.setBorder(BorderFactory.createCompoundBorder(
                    passwordText.getBorder(), 
                    new EmptyBorder(5, 10, 5, 10)  
                ));
        passwordText.setVisible(true);
        passwordText.addFocusListener(textFieldEmptier(passwordText));
        formPanel.add(passwordText);
        
        
        confirmPasswordLabel.setBounds(30, 370, 210, 50);              
        confirmPasswordLabel.setVisible(true);
        confirmPasswordLabel.setFont(new Font("Courier New", Font.ITALIC ,30));
        confirmPasswordLabel.setForeground(Color.WHITE);
        formPanel.add(confirmPasswordLabel);
        
        
        confirmPasswordText.setOpaque(false);                         
        confirmPasswordText.setBounds(250, 370, 200, 40);
        confirmPasswordText.setBackground(new Color(50,74,74, 1));
        confirmPasswordText.setFont(new Font("Courier New", Font.ITALIC, 20));
        confirmPasswordText.setBorder(BorderFactory.createCompoundBorder(
                    confirmPasswordText.getBorder(), 
                    new EmptyBorder(5, 10, 5, 10)  
                ));
        confirmPasswordText.setVisible(true);
        confirmPasswordText.addFocusListener(textFieldEmptier(confirmPasswordText));
        formPanel.add(confirmPasswordText);
        
        signUpButton.setBounds(340, 500, 120, 50);                  // Sign up Button
        signUpButton.setVisible(true);
        signUpButton.setFont(new Font("Courier New", Font.BOLD, 18));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setBackground(new Color(0, 180, 102));
        signUpButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        signUpButton.setFocusPainted(false);
        signUpButton.addMouseListener(signUpButtonClick());
        formPanel.add(signUpButton);
        
        backButton.setBounds(30, 500, 100, 50);
        backButton.setVisible(true);                                // Back Button
        backButton.setFont(new Font("Courier New", Font.BOLD, 18));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(255, 50, 50));
        backButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        backButton.setFocusPainted(false);
        backButton.addMouseListener(backButtonAction());
        formPanel.add(backButton);
        
        showPasswordButton.setBounds(457, 317, 25, 25);
        showPasswordButton.setVisible(true);
        showPasswordButton.setOpaque(false);                     
        showPasswordButton.setContentAreaFilled(false);           
        showPasswordButton.setBorderPainted(false);
        showPasswordButton.setFocusPainted(false); 
        showPasswordButton.addMouseListener(showPasswordAction());
        formPanel.add(showPasswordButton);
        
        showConfirmedPasswordButton.setBounds(457, 377, 25, 25);
        showConfirmedPasswordButton.setVisible(true);
        showConfirmedPasswordButton.setOpaque(false);                     
        showConfirmedPasswordButton.setContentAreaFilled(false);           
        showConfirmedPasswordButton.setBorderPainted(false);
        showConfirmedPasswordButton.setFocusPainted(false); 
        showConfirmedPasswordButton.addMouseListener(showConfirmedPasswordAction());
        formPanel.add(showConfirmedPasswordButton);
    }
    
    private MouseAdapter signUpButtonClick(){
        return new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                String username = usernameText.getText();
                String email = emailText.getText();
                String password = passwordText.getText();
                
                if(email.toLowerCase().contains("@gmail.com") || email.toLowerCase().contains("@yahoo.com"))
                    if(!confirmPasswordText.getText().equals(password)){
                        passwordText.setBorder(new LineBorder(Color.RED, 2));
                        confirmPasswordText.setBorder(new LineBorder(Color.RED, 2));
                        JOptionPane.showMessageDialog(null,"Error! Please enter the same password!","Account error",JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        try{  
                            emailText.setBorder(new LineBorder(Color.WHITE, 2));
                            passwordText.setBorder(new LineBorder(Color.WHITE, 2));
                            usernameText.setBorder(new LineBorder(Color.WHITE, 2));
                            Connection conn = DatabaseManager.getConnection();
                            PreparedStatement stmt = conn.prepareStatement("INSERT INTO accounts (username, email, password) VALUES (?, ?, ?)");
                            stmt.setString(1, username);
                            stmt.setString(2, email);
                            stmt.setString(3, password);
                            int rowsInserted = stmt.executeUpdate();
                            if (rowsInserted > 0) {
                                System.out.println("Successful creation of account !");
                                JOptionPane.showMessageDialog(null,"Successful registration!","Well done",JOptionPane.INFORMATION_MESSAGE);
                                sgm.showPanel("mainMenu");
                            }
                            }catch(SQLException ex){
                                JOptionPane.showMessageDialog(null,"Error! Account already in use!","Account error",JOptionPane.ERROR_MESSAGE);
                            }        
                        }
                else
                    JOptionPane.showMessageDialog(null,"Error! Invalid email credentials!","Account error",JOptionPane.ERROR_MESSAGE);
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
                   showPasswordButton.setNewIcon("images/visibilityOn.png");
                }else{
                    passwordText.setEchoChar('*');
                    showPasswordButton.setNewIcon("images/visibilityOff.png");
                }
                
                
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
                   showConfirmedPasswordButton.setNewIcon("images/visibilityOn.png");
                }else{
                    confirmPasswordText.setEchoChar('*');
                    showConfirmedPasswordButton.setNewIcon("images/visibilityOff.png");
                }
                
                
            }
        };
    }
    
    private FocusListener textFieldEmptier(JTextField a){
        return new FocusListener(){
            String string ;
        @Override
        public void focusGained(FocusEvent e) {
            if (a.getText().equals("email address") || a.getText().equals("password")||a.getText().equals("username")) {
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
    private MouseAdapter backButtonAction(){
        return new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                sgm.showPanel("login");
                emailText.setBorder(new LineBorder(Color.WHITE, 2));
                passwordText.setBorder(new LineBorder(Color.WHITE, 2));
                usernameText.setBorder(new LineBorder(Color.WHITE, 2));
                
            }
        };
    }
    
    private final JPanel formPanel = new JPanel();
    private final JLabel titleLabel = new JLabel("<html><div style='text-align: center;'>" + "Welcome to<br>" +  "Saden" +  "</div></html>");
    private final JLabel usernameLabel = new JLabel("Username : ");
    private JTextField usernameText = new JTextField("username");
    private final JLabel emailLabel = new JLabel("Email : ");
    private JTextField emailText = new JTextField("email address");
    private final JLabel passwordLabel = new JLabel("Password : ");
    private JPasswordField passwordText = new JPasswordField("password");
    private final JButton signUpButton = new JButton("Sign up");
    private final JButton backButton = new JButton("Back");
    private JPasswordField confirmPasswordText = new JPasswordField("password");
    private JLabel confirmPasswordLabel = new JLabel("Confirm :");
    private IconButton showPasswordButton = new IconButton("images/visibilityOff.png", 25, 25);
    private IconButton showConfirmedPasswordButton = new IconButton("images/visibilityOff.png", 25, 25);
}
