/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sadengamesmedia;


import components.ImagePanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
    
    public LoginMenu() throws IOException{
        setTitle("Login");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        setResizable(false);
        
        
        ImagePanel imagePanel = new ImagePanel("C:\\Users\\denia\\Documents\\NetBeansProjects\\SadenGamesMedia\\src\\main\\java\\com\\mycompany\\sadengamesmedia\\loginImage.jpg");
        imagePanel.setVisible(true);
        imagePanel.setBounds(0, 0, 1000, 500);
        imagePanel.setLayout(null);
        add(imagePanel, Integer.valueOf(0));
        imagePanel.repaint();
        

        JPanel menuPanel = new JPanel();
        menuPanel.setBounds(0, 0, 420, 500);
        menuPanel.setVisible(true);
        menuPanel.setLayout(null);
        menuPanel.setBackground(new Color(0,0,0,255));
        add(menuPanel, Integer.valueOf(1));
        menuPanel.repaint();
        
        JLabel titleLabel = new JLabel();
        titleLabel.setText("LOGIN");
        titleLabel.setBounds(130, 0, 300, 100);
        titleLabel.setVisible(true);
        titleLabel.setFont(new Font("Courier New", Font.BOLD ,60));
        titleLabel.setForeground(Color.WHITE);
        menuPanel.add(titleLabel);
        
        JLabel emailLabel = new JLabel();
        emailLabel.setText("Email : ");
        emailLabel.setBounds(60, 120, 120, 50);
        emailLabel.setVisible(true);
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(new Font("Courier New", Font.ITALIC, 25));
        menuPanel.add(emailLabel);
        
        JTextField emailText = new JTextField();
        emailText.setText("email address");
        emailText.setOpaque(false);
        emailText.setBounds(185, 130, 170, 30);
        emailText.setBackground(new Color(50,74,74, 1));
        emailText.setVisible(true);
        menuPanel.add(emailText);
        emailText.repaint();
        
        
        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password : ");
        passwordLabel.setBounds(40, 170, 170, 50);
        passwordLabel.setVisible(true);
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Courier New", Font.ITALIC, 25));
        menuPanel.add(passwordLabel);
        passwordLabel.repaint();
        
        JTextField passwordText = new JTextField();
        passwordText.setText("password");
        passwordText.setOpaque(false);
        passwordText.setBackground(new Color(50,74,74, 1));
        passwordText.setBounds(210, 180, 170, 30);
        passwordText.setVisible(true);
        menuPanel.add(passwordText);
        passwordText.repaint();
        
        JButton logInButton = new JButton();
        logInButton.setText("Log in");
        logInButton.setBounds(200, 225, 100, 50);
        logInButton.setFont(new Font("Courier New", Font.ITALIC, 15));
        logInButton.setVisible(true);
        menuPanel.add(logInButton);
        logInButton.repaint();
        
        
        JLabel signUpLabel = new JLabel();
        signUpLabel.setText("<html>Don't have an account ? Sign up now !</html>");
        signUpLabel.setBounds(40, 280, 400, 100);
        signUpLabel.setVisible(true);
        signUpLabel.setFont(new Font("Courier New", Font.ITALIC, 25));
        signUpLabel.setForeground(Color.WHITE);
        menuPanel.add(signUpLabel);
        signUpLabel.repaint();
        
        JButton signUpButton = new JButton();
        signUpButton.setText("Sign up");
        signUpButton.setBounds(260, 340, 100, 50);
        signUpButton.setVisible(true);
        menuPanel.add(signUpButton);
        signUpButton.repaint();
        


        
    }
    
    /*private void createBlurImage(Graphics2D g){
        int x = 20;
        int y = 20;
        int width = 250;
        int height = 250;
        int shadow = 8;
        if(width > 0 && height > 0){
            BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = img.createGraphics();
            g2.drawImage(ImageUtil.blur(bufferedImage.getSubimage(x,y,width,height), 10f),0, 0, null);
        }
    }*/
}
