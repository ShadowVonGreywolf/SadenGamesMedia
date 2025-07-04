/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sadengamesmedia;


import components.ImagePanel;
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
public class LoginMenu extends JFrame{
    
    
    public LoginMenu() throws IOException{
        
        setTitle("Saden Games & Media");
        setSize(955, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        
        imagePanel.setVisible(true);               //Image Panel
        imagePanel.setBounds(420, 0, 535, 500);
        imagePanel.setLayout(null);
        add(imagePanel, Integer.valueOf(0));
        imagePanel.repaint();
        
        menuPanel.setBounds(0, 0, 420, 500);       //Menu Panel
        menuPanel.setVisible(true);
        menuPanel.setLayout(null);
        menuPanel.setBackground(new Color(0,0,0,255));
        add(menuPanel, Integer.valueOf(1));
        menuPanel.repaint();
     
        titleLabel.setBounds(130, 0, 300, 100);    //Title Label
        titleLabel.setVisible(true);
        titleLabel.setFont(new Font("Courier New", Font.BOLD ,60));      
        titleLabel.setForeground(Color.WHITE);
        menuPanel.add(titleLabel);
        
        emailLabel.setBounds(60, 120, 120, 50); //Email Label
        emailLabel.setVisible(true);
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(new Font("Courier New", Font.ITALIC, 25));
        menuPanel.add(emailLabel);
        
        emailText.setOpaque(false);              //Email Text
        emailText.setBounds(185, 130, 170, 30);
        emailText.setBackground(new Color(50,74,74, 1));
        emailText.setVisible(true);
        menuPanel.add(emailText);
        emailText.repaint();
        emailText.addMouseListener(new MouseAdapter(){
            
            @Override
            public void mouseClicked(MouseEvent e){
                emailText.setText("");
            }
        });
        
        passwordLabel.setBounds(40, 170, 170, 50);    //Password Label
        passwordLabel.setVisible(true);
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Courier New", Font.ITALIC, 25));
        menuPanel.add(passwordLabel);
        passwordLabel.repaint();
          
        passwordText.setOpaque(false);              //Password Text
        passwordText.setBackground(new Color(50,74,74, 1));
        passwordText.setBounds(210, 180, 170, 30);
        passwordText.setVisible(true);
        menuPanel.add(passwordText);
        passwordText.repaint();
        passwordText.addMouseListener(new MouseAdapter(){
            
            @Override
            public void mouseClicked(MouseEvent e){
                passwordText.setText("");
            }
        });
        
        logInButton.setBounds(200, 225, 100, 50);           //Log in Button
        logInButton.setFont(new Font("Courier New", Font.ITALIC, 15));
        logInButton.setVisible(true);
        menuPanel.add(logInButton);
        logInButton.repaint();
        logInButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                new MainMenu();
                dispose();
            }
        });
        
        signUpLabel.setBounds(40, 280, 400, 100);           // Sign up Label
        signUpLabel.setVisible(true);
        signUpLabel.setFont(new Font("Courier New", Font.ITALIC, 25));
        signUpLabel.setForeground(Color.WHITE);
        menuPanel.add(signUpLabel);
        signUpLabel.repaint();
        
        signUpButton.setBounds(260, 340, 100, 50);      // Sign up Button 
        signUpButton.setVisible(true);
        menuPanel.add(signUpButton);
        signUpButton.repaint();
        signUpButton.addActionListener(new ActionListener(){ 
            @Override
            public void actionPerformed(ActionEvent e){
                new SignUp();
                dispose();
            }
        });

    }
    
    private final JPanel menuPanel= new JPanel();
    private final ImagePanel imagePanel = new ImagePanel("C:\\Users\\denia\\Documents\\NetBeansProjects\\SadenGamesMedia\\src\\main\\java\\com\\mycompany\\sadengamesmedia\\loginImage.jpg");
    private final JLabel titleLabel = new JLabel("LOGIN");
    private final JLabel emailLabel = new JLabel("Email : ");
    private  JTextField emailText = new JTextField("email address");
    private final JLabel passwordLabel = new JLabel("Password : ");
    private  JTextField passwordText = new JTextField("password");
    private final JButton logInButton = new JButton("Log in");
    private final JLabel signUpLabel = new JLabel("<html>Don't have an account ? Sign up now !</html>");
    private final JButton signUpButton = new JButton("Sign up");
}
