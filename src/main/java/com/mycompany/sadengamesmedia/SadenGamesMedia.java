/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sadengamesmedia;

import java.awt.CardLayout;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author denia
 */
public class SadenGamesMedia extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel;
    
    public SadenGamesMedia() throws SQLException{
    initComponents();
    }
    public void showPanel(String name) {
        cardLayout.show(cardPanel, name);
    }
    private void initComponents() throws SQLException{
        
        setTitle("Saden Games & Media");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(cardLayout);
        
        
        cardPanel = new JPanel(cardLayout);
        LoginPanel loginPanel = new LoginPanel(this);
        SignUpPanel signUpPanel = new SignUpPanel(this);
        MainMenuPanel mainMenuPanel = new MainMenuPanel(this);
        cardPanel.add(loginPanel, "login");
        cardPanel.add(signUpPanel, "signUp");
        cardPanel.add(mainMenuPanel, "mainMenu");
        add(cardPanel);
        
        cardLayout.show(cardPanel, "login");
        
    }
    public static void main(String[] args){  
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new SadenGamesMedia().setVisible(true);
            } catch (SQLException ex) {
                System.getLogger(SadenGamesMedia.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        });
    }
    
    
    

}
