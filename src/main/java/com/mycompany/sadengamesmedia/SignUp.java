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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author denia
 */
public class SignUp extends JFrame{
    
    public SignUp(){
    
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

        titleLabel.setBounds(100, 20, 400, 110);            //Title
        titleLabel.setVisible(true);
        titleLabel.setFont(new Font("Courier New", Font.BOLD ,60));
        titleLabel.setForeground(Color.WHITE);
        signUpPanel.add(titleLabel);
        titleLabel.repaint();
        
        firstNameLabel.setBounds(30, 140, 200, 50);             // FirstName Label
        firstNameLabel.setVisible(true);
        firstNameLabel.setFont(new Font("Courier New", Font.BOLD ,25));
        firstNameLabel.setForeground(Color.WHITE);
        signUpPanel.add(firstNameLabel);
        firstNameLabel.repaint();
        
        
        firstNameText.setOpaque(false);                      //FirstName Text 
        firstNameText.setBounds(250, 150, 170, 30);
        firstNameText.setBackground(new Color(50,74,74, 1));
        firstNameText.setVisible(true);
        signUpPanel.add(firstNameText);
        firstNameText.repaint();
        firstNameText.addMouseListener(new MouseAdapter(){
            
            @Override
            public void mouseClicked(MouseEvent e){
                if(firstNameText.getText().equals("first name"))
                    firstNameText.setText("");
            }
        });
        
        
        givenNameLabel.setBounds(30, 190, 200, 50);             //GivenName Label
        givenNameLabel.setVisible(true);
        givenNameLabel.setFont(new Font("Courier New", Font.BOLD ,25));
        givenNameLabel.setForeground(Color.WHITE);
        signUpPanel.add(givenNameLabel);
        givenNameLabel.repaint();
        
        givenNameText.setOpaque(false);                         //GivenName Text
        givenNameText.setBounds(250, 200, 170, 30); 
        givenNameText.setBackground(new Color(50,74,74, 1));
        givenNameText.setVisible(true);
        signUpPanel.add(givenNameText);
        givenNameText.repaint();
        givenNameText.addMouseListener(new MouseAdapter(){
            
            @Override
            public void mouseClicked(MouseEvent e){
                if(givenNameText.getText().equals("given name"))
                    givenNameText.setText("");
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
    private final JLabel firstNameLabel = new JLabel("First Name : ");
    private JTextField firstNameText = new JTextField("first name");
    private final JLabel givenNameLabel = new JLabel("Given Name : ");
    private JTextField givenNameText = new JTextField("given name");
    private final JLabel emailLabel = new JLabel("Email : ");
    private JTextField emailText = new JTextField("email address");
    private final JLabel passwordLabel = new JLabel("Password : ");
    private JTextField passwordText = new JTextField("password");
    private final JLabel subnoteLabel = new JLabel("Please fill out all required fields!");
    private final JButton signUpButton = new JButton("Sign up");
    private final JButton backButton = new JButton("Back");
}
