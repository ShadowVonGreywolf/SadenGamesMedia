/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sadengamesmedia;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
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
    
    public LoginMenu(){
        setTitle("Login");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        setResizable(false);
        
        
        
        JPanel menuPanel = new JPanel();
        menuPanel.setBounds(0, 0, 500, 500);
        menuPanel.setVisible(true);
        menuPanel.setLayout(null);
        //menuPanel.setBackground(Color.red);
        add(menuPanel);
        
        JPanel imagePanel = new JPanel();
        imagePanel.setBounds(500, 0, 500, 500);
        imagePanel.setVisible(true);
        imagePanel.setBackground(Color.blue);
        add(imagePanel);
        

        JLabel titleLabel = new JLabel();
        titleLabel.setText("LOGIN");
        titleLabel.setBounds(150, 0, 300, 100);
        titleLabel.setVisible(true);
        titleLabel.setFont(new Font("Courier New", Font.BOLD ,60));
        menuPanel.add(titleLabel);
        
        JLabel emailLabel = new JLabel();
        emailLabel.setText("Email : ");
        emailLabel.setBounds(60, 120, 120, 50);
        emailLabel.setVisible(true);
        emailLabel.setFont(new Font("Courier New", Font.ITALIC, 25));
        menuPanel.add(emailLabel);
        
        JTextField emailText = new JTextField();
        emailText.setText("email address");
        emailText.setBounds(185, 130, 170, 30);
        //emailText.setBackground(Color.yellow);
        emailText.setVisible(true);
        menuPanel.add(emailText);
        emailText.repaint();
        
        
        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password : ");
        menuPanel.add(passwordLabel);
        passwordLabel.setBounds(60, 170, 170, 50);
        passwordLabel.setVisible(true);
        passwordLabel.setFont(new Font("Courier New", Font.ITALIC, 25));
        menuPanel.add(passwordLabel);
        
        JTextField passwordText = new JTextField();
        passwordText.setText("password");
        passwordText.setBounds(230, 180, 170, 30);
        passwordText.setVisible(true);
        menuPanel.add(passwordText);
        passwordText.repaint();
        
        JButton logInButton = new JButton();
        logInButton.setText("Log in");
        logInButton.setBounds(200, 225, 100, 50);
        logInButton.setVisible(true);
        menuPanel.add(logInButton);
        logInButton.repaint();
        
    }
}
