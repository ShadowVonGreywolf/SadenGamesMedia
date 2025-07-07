/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sadengamesmedia;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author denia
 */
public class MainMenuPanel extends JPanel {
    private SadenGamesMedia sgm;
    
    public MainMenuPanel(SadenGamesMedia sgm){
        this.sgm = sgm;
        initComponents();
    }

    private void initComponents() {
        setLayout(null);

        sideMenu.setBackground(Color.DARK_GRAY);
        sideMenu.setLayout(new BoxLayout(sideMenu, BoxLayout.Y_AXIS));
        sideMenu.setBounds(0, 0, sideMenuWidth, getHeight());
        add(sideMenu);
        
        mainContent.setBackground(Color.RED);
        mainContent.setBounds(0, 50, 1200, 750);
        mainContent.setLayout(null);
        mainContent.setVisible(true);
        add(mainContent);
        
        topPanel.setBackground(Color.CYAN);
        topPanel.setBounds(0, 0, 1200, 50);
        topPanel.setLayout(null);
        topPanel.setVisible(true);
        add(topPanel);
        
        
        sideMenuButton.setBounds(2, 0, 48, 48);
        sideMenuButton.setBorderPainted(false);
        sideMenuButton.setFocusPainted(false);
        sideMenuButton.setContentAreaFilled(false);
        topPanel.add(sideMenuButton);
        sideMenuButton.addActionListener(e -> sideMenuAnimation());
        
        accountButton.setPreferredSize(new Dimension(100, 50));
        accountButton.setMaximumSize(new Dimension(150, 50));
        accountButton.setVisible(true);
        sideMenu.add(accountButton);   


        sideMenuCloseButton.setPreferredSize(new Dimension(100, 50));
        sideMenuCloseButton.setMaximumSize(new Dimension(150, 50));
        sideMenuCloseButton.setVisible(true);
        sideMenu.add(sideMenuCloseButton);   
        sideMenuCloseButton.addActionListener(e -> sideMenuAnimation());
        
        productsList = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(productsList);
        scrollPane.setBounds(50, 50, 300, 200);
        scrollPane.setVisible(true);
        mainContent.add(scrollPane);

        
    }
    private void sideMenuAnimation() {
        
        if (animationTimer != null && animationTimer.isRunning()) return;
        sideMenuOpen = !sideMenuOpen;
        animationTimer = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int animationSpeed = 5;
                if (sideMenuOpen == true) {
                    sideMenuWidth += animationSpeed;
                    if(sideMenuWidth >= maxSideMenuWidth){
                        sideMenuWidth = maxSideMenuWidth;
                        animationTimer.stop();
                        sideMenuButton.setVisible(false);
                    }      
                }else{
                    sideMenuWidth -= animationSpeed;
                    if(sideMenuWidth <= 0){
                        sideMenuWidth = 0;
                        animationTimer.stop();
                        sideMenuButton.setVisible(true);
                        
                    }
                }
                sideMenu.setBounds(0, 0, sideMenuWidth, getHeight());
                sideMenu.revalidate();
                sideMenu.repaint();
            }
        });
        animationTimer.start();
    }
    
    private JPanel sideMenu = new JPanel();
    private JPanel mainContent = new JPanel();
    private JPanel topPanel = new JPanel();
    private IconButton sideMenuButton = new IconButton("images/sideMenuButtonIcon.png", 48, 48);
    private JButton sideMenuCloseButton = new JButton("Close");
    private JButton accountButton = new JButton("Account");
    private JTable productsList = new JTable();
    private DefaultTableModel tableModel = new DefaultTableModel();
    private Timer animationTimer;
    private int sideMenuWidth = 0;
    private boolean sideMenuOpen = false;
    private final int maxSideMenuWidth = 150;
    
    
}
