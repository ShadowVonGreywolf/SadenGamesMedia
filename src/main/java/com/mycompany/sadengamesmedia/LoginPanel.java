/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sadengamesmedia;

import com.mycompany.components.ImagePanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class LoginPanel extends JPanel{
    private SadenGamesMedia sgm;
    
    public LoginPanel(SadenGamesMedia sgm){
        this.sgm = sgm;
        initComponents();        
    }
    
    private void initComponents(){
        setLayout(null);
        ImagePanel backgroundPanel = null;
        try {
            backgroundPanel = new ImagePanel("images/loginBackground.png");
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
        
        titleLabel.setBounds(130,40,400,60);                        //Title Label
        titleLabel.setVisible(true);
        titleLabel.setFont(new Font("Courier New", Font.BOLD ,80));      
        titleLabel.setForeground(Color.WHITE);
        formPanel.add(titleLabel);
        
        emailLabel.setBounds(40, 150, 200, 50);                     //Email Label
        emailLabel.setVisible(true);
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(new Font("Courier New", Font.ITALIC, 30));
        formPanel.add(emailLabel);
        
        emailText.setOpaque(false);                                 //Email Text
        emailText.setBounds(210, 155, 220, 40);
        emailText.setBackground(new Color(50,74,74, 1));
        emailText.setFont(new Font("Courier New", Font.ITALIC, 20));
        emailText.setBorder(BorderFactory.createCompoundBorder(
                    emailText.getBorder(), 
                    new EmptyBorder(5, 10, 5, 10)  
                ));
        emailText.setVisible(true);
        emailText.addMouseListener(textFieldEmptier(emailText));
        formPanel.add(emailText);
        
        passwordLabel.setBounds(25, 220, 200, 50);                  //Password Label
        passwordLabel.setVisible(true);
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Courier New", Font.ITALIC, 30));
        formPanel.add(passwordLabel);
        
        passwordText.setOpaque(false);                              //Password Text
        passwordText.setBackground(new Color(50,74,74, 1));
        passwordText.setBounds(225, 225, 220, 40);
        passwordText.setFont(new Font("Courier New", Font.ITALIC, 20));
        passwordText.setBorder(BorderFactory.createCompoundBorder(
                    emailText.getBorder(), 
                    new EmptyBorder(5, 10, 5, 10)  
                ));
        passwordText.setVisible(true);
        passwordText.addMouseListener(textFieldEmptier(passwordText));
        formPanel.add(passwordText);
        
        loginButton.setBounds(310, 300, 100, 50);                       //Login Button
        loginButton.setFont(new Font("Courier New", Font.ITALIC, 15));
        loginButton.setVisible(true);
        loginButton.addMouseListener(loginButtonClick());
        formPanel.add(loginButton);
        
        signUpLabel.setBounds(40, 435, 460, 100);           // Sign up Label
        signUpLabel.setVisible(true);
        signUpLabel.setFont(new Font("Courier New", Font.ITALIC, 30));
        signUpLabel.setForeground(Color.WHITE);
        formPanel.add(signUpLabel);
        
        signUpButton.setBounds(340, 500, 100, 50);      // Sign up Button 
        signUpButton.setVisible(true);
        signUpButton.addMouseListener(signUpButtonClick());
        formPanel.add(signUpButton);
    }
    
    private MouseAdapter textFieldEmptier(JTextField a){//empties textfields on which the mouse clicks 
        return new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                if(a.getText().equals("password")||a.getText().equals("email address"))
                    a.setText("");
            }
        };
    }
        
    private MouseAdapter loginButtonClick(){  //checks if the credentials (email and password) are valid
        return new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                String email = emailText.getText();
                String password = passwordText.getText();
                try{  
                    Connection conn = DatabaseManager.getConnection();
                    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM accounts WHERE email=? AND password=?");
                    stmt.setString(1, email);
                    stmt.setString(2, password);

                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        System.out.println("Login successful!");
                        errorLabel.setVisible(false);
                        sgm.showPanel("mainMenu");
                        
                    } else {
                        System.out.println("Invalid credentials.");
                        invalidCredentials();
                    }

                    }catch(SQLException ex){
                        ex.printStackTrace();
                    }   
            }
        };
    }
    
    private MouseAdapter signUpButtonClick(){//redirects the new user to the sign up page
        return new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                sgm.showPanel("signUp");
                errorLabel.setVisible(false);
            }
        };
    }
    
    private void invalidCredentials(){
        emailText.setBorder(new LineBorder(Color.RED, 2));
        passwordText.setBorder(new LineBorder(Color.RED, 2));
        loginButton.setBounds(340, 300, 100, 50);
        errorLabel.setBounds(30, 300, 300, 50); //Error Label
        errorLabel.setVisible(true);
        errorLabel.setFont(new Font("Courier New", Font.ITALIC, 17));
        errorLabel.setForeground(Color.RED);
        formPanel.add(errorLabel);
        errorLabel.repaint();
        JOptionPane.showMessageDialog(null,"Invalid email or password!","Login Error",JOptionPane.ERROR_MESSAGE);  
        emailText.setBorder(new LineBorder(Color.WHITE, 2));
        passwordText.setBorder(new LineBorder(Color.WHITE, 2));
    } 
    
    private final JPanel formPanel = new JPanel();
    private final JLabel titleLabel = new JLabel("LOGIN");
    private final JLabel emailLabel = new JLabel("Email : ");
    private  JTextField emailText = new JTextField("email address");
    private final JLabel passwordLabel = new JLabel("Password : ");
    private  JPasswordField passwordText = new JPasswordField("password");
    private final JButton loginButton = new JButton("Log in");
    private final JLabel signUpLabel = new JLabel("<html>Don't have an account ? Sign up now !</html>");
    private final JButton signUpButton = new JButton("Sign up");
    private final JLabel errorLabel = new JLabel("Incorrect email or password!");
}
