/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sadengamesmedia;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author denia
 */
public class SignUp extends JFrame{
    
    private MouseAdapter signUpButtonClick(){
        return new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                String username = usernameText.getText();
                String email = emailText.getText();
                String password = passwordText.getText();
                try{  
                    Connection conn = DatabaseManager.getConnection();
                    PreparedStatement stmt = conn.prepareStatement("INSERT INTO accounts (username, email, password) VALUES (?, ?, ?)");
                    stmt.setString(1, username);
                    stmt.setString(2, email);
                    stmt.setString(3, password);
                    int rowsInserted = stmt.executeUpdate();
                    if (rowsInserted > 0) {
                        System.out.println("Successful creation of account !");
                        JOptionPane.showMessageDialog(null,"Successful registration!","Well done",JOptionPane.INFORMATION_MESSAGE);
                    }
                    }catch(SQLException ex){
                        JOptionPane.showMessageDialog(null,"Error! Account already in use!","Account error",JOptionPane.INFORMATION_MESSAGE);
                    }  
            }
        };
    }
    
    public SignUp(){
        
        //Connection conn = DatabaseManager.getConnection();
        setTitle("Sign up");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        
        signUpPanel.setBounds(0, 0, 600, 600);              //Sign up Panel
        signUpPanel.setVisible(true);
        signUpPanel.setLayout(null);
        signUpPanel.setBackground(new Color(0,0,0,255));
        add(signUpPanel, Integer.valueOf(1));
        signUpPanel.repaint();

        titleLabel.setBounds(100, 50, 400, 110);            //Title
        titleLabel.setVisible(true);
        titleLabel.setFont(new Font("Courier New", Font.BOLD ,60));
        titleLabel.setForeground(Color.WHITE);
        signUpPanel.add(titleLabel);
        titleLabel.repaint();
        
        usernameLabel.setBounds(30, 190, 200, 50);             //Username Label
        usernameLabel.setVisible(true);
        usernameLabel.setFont(new Font("Courier New", Font.BOLD ,25));
        usernameLabel.setForeground(Color.WHITE);
        signUpPanel.add(usernameLabel);
        usernameLabel.repaint();
        
        usernameText.setOpaque(false);                         //Username Text
        usernameText.setBounds(250, 200, 170, 30); 
        usernameText.setBackground(new Color(50,74,74, 1));
        usernameText.setVisible(true);
        signUpPanel.add(usernameText);
        usernameText.repaint();
        usernameText.addMouseListener(new MouseAdapter(){
            
            @Override
            public void mouseClicked(MouseEvent e){
                if(usernameText.getText().equals("username"))
                    usernameText.setText("");
            }
        });
        
        
        emailLabel.setBounds(30, 240, 120, 50);                     //Email Label
        emailLabel.setVisible(true);
        emailLabel.setFont(new Font("Courier New", Font.BOLD ,25));
        emailLabel.setForeground(Color.WHITE);
        signUpPanel.add(emailLabel);
        emailLabel.repaint();
        
        emailText.setOpaque(false);                         //Email Text 
        emailText.setBounds(250, 250, 170, 30);
        emailText.setBackground(new Color(50,74,74, 1));
        emailText.setVisible(true);
        signUpPanel.add(emailText);
        emailText.repaint();
        emailText.addMouseListener(new MouseAdapter(){
            
            @Override
            public void mouseClicked(MouseEvent e){
                if(emailText.getText().equals("email address"))
                    emailText.setText("");
            }
        });

        passwordLabel.setBounds(30, 290, 180, 50);              //Password Label
        passwordLabel.setVisible(true);
        passwordLabel.setFont(new Font("Courier New", Font.BOLD ,25));
        passwordLabel.setForeground(Color.WHITE);
        signUpPanel.add(passwordLabel);
        passwordLabel.repaint();

        passwordText.setText("password");
        passwordText.setOpaque(false);                        //Password Text 
        passwordText.setBounds(250, 300, 170, 30);
        passwordText.setBackground(new Color(50,74,74, 1));
        passwordText.setVisible(true);
        signUpPanel.add(passwordText);
        passwordText.repaint();
        passwordText.addMouseListener(new MouseAdapter(){
            
            @Override
            public void mouseClicked(MouseEvent e){
                if(passwordText.getText().equals("password"))
                    passwordText.setText("");
            }
        });

        subnoteLabel.setBounds(60, 350, 350, 50);                  // subnote Label
        subnoteLabel.setVisible(true);
        subnoteLabel.setFont(new Font("Arial", Font.BOLD ,15));
        subnoteLabel.setForeground(Color.RED);
        signUpPanel.add(subnoteLabel);
        subnoteLabel.repaint();

        signUpButton.setBounds(370, 355, 120, 40);                  // Sign up Button
        signUpButton.setVisible(true);
        signUpButton.setFont(new Font("Arial", Font.BOLD ,20));
        signUpPanel.add(signUpButton);
        signUpButton.repaint();
        signUpButton.addMouseListener(signUpButtonClick());

        backButton.setBounds(30, 500, 100, 40);
        backButton.setVisible(true);                                // Back Button
        backButton.setFont(new Font("Arial", Font.BOLD ,20));
        signUpPanel.add(backButton);
        backButton.repaint();
        backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    new LoginMenu();
                } catch (IOException ex) {
                    System.getLogger(SignUp.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
                    dispose();
            }
        });
        
    }
    private final JPanel signUpPanel = new JPanel();
    private final JLabel titleLabel = new JLabel("<html><div style='text-align: center;'>" + "Welcome to<br>" +  "Saden" +  "</div></html>");
    private final JLabel usernameLabel = new JLabel("Username : ");
    private JTextField usernameText = new JTextField("username");
    private final JLabel emailLabel = new JLabel("Email : ");
    private JTextField emailText = new JTextField("email address");
    private final JLabel passwordLabel = new JLabel("Password : ");
    private JTextField passwordText = new JTextField("password");
    private final JLabel subnoteLabel = new JLabel("Please fill out all required fields!");
    private final JButton signUpButton = new JButton("Sign up");
    private final JButton backButton = new JButton("Back");
}
