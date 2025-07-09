/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sadengamesmedia;

import com.mycompany.components.IconButton;
import com.mycompany.components.LabelImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author denia
 */
public class AccountPanel extends JPanel {
        
    public AccountPanel(){
        initComponents();
    }
    
    private void initComponents(){
        setLayout(null);
        setBackground(new Color(10,15,20));
        
        
        
        accountImage.setBounds(50, 50, 300, 300);
        accountImage.setVisible(true);
        add(accountImage);
        
        
        yourAccountLabel.setVisible(true);
        yourAccountLabel.setBounds(60, 380, 300, 50);
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
        
        //JButton changePhoto = new JButton("Change profile photo");
        
        
        
        
        
    }
    private boolean passwordClicked = false;
    private MouseAdapter showPasswordAction(){
        return new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                passwordClicked = !passwordClicked;
                if(passwordClicked){
                   passwordText.setEchoChar((char) 0); 
                   passwordVisible.setNewIcon("images/visibilityOn.png");
                }else{
                    passwordText.setEchoChar('*');
                    passwordVisible.setNewIcon("images/visibilityOff.png");
                }
                
                
            }
        };
    }
    
    
    
    
    
    private LabelImage accountImage = new LabelImage("images/myAcountIcon.png",300, 300);
    private JLabel yourAccountLabel = new JLabel("Your Account");
    private JLabel usernameLabel = new JLabel("Username : ");
    private JTextField usernameText = new JTextField("username");
    private JLabel emailLabel = new JLabel("Email : ");
    private JTextField emailText = new JTextField("email address");
    private JLabel passwordLabel = new JLabel("Password : ");
    private JPasswordField passwordText = new JPasswordField("password");
    private IconButton passwordVisible = new IconButton("images/visibilityOff.png", 30, 30);
    private JLabel roleLabel = new JLabel("Role : ");
    private JTextField roleText = new JTextField("role");
    
}
