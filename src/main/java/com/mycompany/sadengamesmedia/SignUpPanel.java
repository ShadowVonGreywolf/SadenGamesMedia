/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sadengamesmedia;

import com.mycompany.components.ImagePanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
        usernameText.addMouseListener(textFieldEmptier(usernameText));
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
        emailText.addMouseListener(textFieldEmptier(emailText));
        formPanel.add(emailText);
        
        passwordLabel.setBounds(30, 310, 210, 50);              //Password Label
        passwordLabel.setVisible(true);
        passwordLabel.setFont(new Font("Courier New", Font.ITALIC ,30));
        passwordLabel.setForeground(Color.WHITE);
        formPanel.add(passwordLabel);
        
        passwordText.setText("password");
        passwordText.setOpaque(false);                        //Password Text 
        passwordText.setBounds(250, 310, 200, 40);
        passwordText.setBackground(new Color(50,74,74, 1));
        passwordText.setFont(new Font("Courier New", Font.ITALIC, 20));
        passwordText.setBorder(BorderFactory.createCompoundBorder(
                    passwordText.getBorder(), 
                    new EmptyBorder(5, 10, 5, 10)  
                ));
        passwordText.setVisible(true);
        passwordText.addMouseListener(textFieldEmptier(passwordText));
        formPanel.add(passwordText);
        
        signUpButton.setBounds(340, 375, 120, 40);                  // Sign up Button
        signUpButton.setVisible(true);
        signUpButton.setFont(new Font("Arial", Font.BOLD ,20));
        signUpButton.addMouseListener(signUpButtonClick());
        formPanel.add(signUpButton);
        
        backButton.setBounds(30, 500, 100, 40);
        backButton.setVisible(true);                                // Back Button
        backButton.setFont(new Font("Arial", Font.BOLD ,20));
        backButton.addMouseListener(backButtonAction());
        formPanel.add(backButton);
    }
    
    private MouseAdapter signUpButtonClick(){
        return new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                String username = usernameText.getText();
                String email = emailText.getText();
                String password = passwordText.getText();
                if(usernameText.getText().equals("") || emailText.getText().equals("") || passwordText.getText().equals("")){
                    emailText.setBorder(new LineBorder(Color.RED, 2));
                    passwordText.setBorder(new LineBorder(Color.RED, 2));
                    usernameText.setBorder(new LineBorder(Color.RED, 2));
                    errorLabel.setBounds(60, 350, 350, 50);                  // error Label
                    errorLabel.setVisible(true);
                    errorLabel.setFont(new Font("Arial", Font.BOLD ,15));
                    errorLabel.setForeground(Color.RED);
                    formPanel.add(errorLabel);
                    errorLabel.repaint();
                }
                else {
                    try{  
                        emailText.setBorder(new LineBorder(Color.WHITE, 2));
                        passwordText.setBorder(new LineBorder(Color.WHITE, 2));
                        usernameText.setBorder(new LineBorder(Color.WHITE, 2));
                        errorLabel.setVisible(false);
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
            }
        };
    }
    
    private MouseAdapter textFieldEmptier(JTextField a){        //empties textfields on which the mouse clicks 
        return new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                if(a.getText().equals("password")||a.getText().equals("email address")||a.getText().equals("username"))
                    a.setText("");
            }
        };
    }
        
    private MouseAdapter backButtonAction(){
        return new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                sgm.showPanel("login");
                errorLabel.setVisible(false);
                emailText.setBorder(new LineBorder(Color.WHITE, 2));
                passwordText.setBorder(new LineBorder(Color.WHITE, 2));
                usernameText.setBorder(new LineBorder(Color.WHITE, 2));
                emailText.setText("");
                passwordText.setText("");
                usernameText.setText("");
                
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
    private final JLabel errorLabel = new JLabel("Please fill out all required fields!");
    private final JButton signUpButton = new JButton("Sign up");
    private final JButton backButton = new JButton("Back");
}
