/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sadengamesmedia;


import com.mycompany.components.ImagePanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author denia
 */
public class LoginMenu extends JFrame{
    
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
                        validCredentials();
                        new MainMenu();
                        dispose();
                    } else {
                        System.out.println("Invalid credentials.");
                        invalidCredentials();
                    }

                    }catch(Exception ex){
                        ex.printStackTrace();
                    }   
            }
        };
    }
    private MouseAdapter textFieldEmptier(JTextField a){//empties textfields on which the mouse clicks 
        return new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if(a.getText().equals("password")||a.getText().equals("email address"))
                    a.setText("");
            }
        };
    }
    private MouseAdapter signUpButtonClick(){//redirects the new user to the sign up page
        return new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                new SignUp();
                dispose();
            }
        };
    }
    private void validCredentials(){
        emailLabel.setBounds(60, 120, 120, 50);
        emailText.setBounds(185, 130, 170, 30);
        passwordLabel.setBounds(40, 170, 170, 50);
        passwordText.setBounds(210, 180, 170, 30);
        loginButton.setBounds(240, 225, 100, 50);
        errorLabel.setVisible(false);
        
    }
    private void invalidCredentials(){
        Border border = emailText.getBorder();
        emailText.setBorder(new LineBorder(Color.RED, 2));
        passwordText.setBorder(new LineBorder(Color.RED, 2));
        emailLabel.setBounds(60, 100, 120, 50);
        emailText.setBounds(185, 110, 170, 30);
        passwordLabel.setBounds(40, 150, 170, 50);
        passwordText.setBounds(210, 160, 170, 30);
        loginButton.setBounds(220, 235, 100, 50);
        
        
        errorLabel.setBounds(60, 190, 300, 50); //Error Label
        errorLabel.setVisible(true);
        errorLabel.setFont(new Font("Courier New", Font.ITALIC, 15));
        errorLabel.setForeground(Color.RED);
        menuPanel.add(errorLabel);
        JOptionPane.showMessageDialog(null,"Invalid email or password!","Login Error",JOptionPane.ERROR_MESSAGE);  
        emailText.setBorder(border);
        passwordText.setBorder(border);
    }
    private void initComponents(){
        setTitle("Saden Games & Media");
        setSize(955, 495);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        
        imagePanel.setVisible(true);               //Image Panel
        imagePanel.setBounds(420, 0, 535, 495);
        imagePanel.setLayout(null);
        add(imagePanel, Integer.valueOf(0));
        imagePanel.repaint();
        
        menuPanel.setBounds(0, 0, 420, 495);       //Menu Panel
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
        emailText.addMouseListener(textFieldEmptier(emailText));

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
        passwordText.addMouseListener(textFieldEmptier(passwordText));
        
        loginButton.setBounds(240, 225, 100, 50);           //Log in Button
        loginButton.setFont(new Font("Courier New", Font.ITALIC, 15));
        loginButton.setVisible(true);
        menuPanel.add(loginButton);
        loginButton.repaint();
        loginButton.addMouseListener(loginButtonClick());
        
        signUpLabel.setBounds(40, 335, 400, 100);           // Sign up Label
        signUpLabel.setVisible(true);
        signUpLabel.setFont(new Font("Courier New", Font.ITALIC, 25));
        signUpLabel.setForeground(Color.WHITE);
        menuPanel.add(signUpLabel);
        signUpLabel.repaint();
        
        signUpButton.setBounds(260, 390, 100, 50);      // Sign up Button 
        signUpButton.setVisible(true);
        menuPanel.add(signUpButton);
        signUpButton.repaint();
        signUpButton.addMouseListener(signUpButtonClick());
    }
    public LoginMenu() throws IOException{
        initComponents();
    }
    
    private final JPanel menuPanel= new JPanel();
    private final ImagePanel imagePanel = new ImagePanel("images/loginImage.jpg");
    private final JLabel titleLabel = new JLabel("LOGIN");
    private final JLabel emailLabel = new JLabel("Email : ");
    private  JTextField emailText = new JTextField("email address");
    private final JLabel passwordLabel = new JLabel("Password : ");
    private  JTextField passwordText = new JTextField("password");
    private final JButton loginButton = new JButton("Log in");
    private final JLabel signUpLabel = new JLabel("<html>Don't have an account ? Sign up now !</html>");
    private final JButton signUpButton = new JButton("Sign up");
    private final JLabel errorLabel = new JLabel("Incorrect email or password!");
}
