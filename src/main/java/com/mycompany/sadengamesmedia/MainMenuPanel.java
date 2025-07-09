/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sadengamesmedia;
import com.mycompany.components.GradientPanel;
import static com.mycompany.components.GradientPanel.GradientType.*;
import com.mycompany.components.IconButton;
import com.mycompany.sadengamesmedia.model.ProductItem;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
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
    
    public MainMenuPanel(SadenGamesMedia sgm) throws SQLException{
        this.sgm = sgm;
        initComponents();
    }

    private void initComponents() throws SQLException {
        setLayout(null);

        sideMenu.setBackground(Color.DARK_GRAY);
        sideMenu.setLayout(new BoxLayout(sideMenu, BoxLayout.Y_AXIS));
        sideMenu.setBounds(0, 0, sideMenuWidth, getHeight());
        add(sideMenu);
        
        mainContent.setBackground(Color.RED);
        mainContent.setBounds(0, 50, 1200, 750);
        mainContent.setLayout(null);
        mainContent.setVisible(true);
        mainContent.setBackground(new Color(34, 34, 34));
        add(mainContent);
        
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
        
        allProducts = ProductItem.getAllProducts();
        productsPanel = new ProductsListPanel(allProducts);
        productsPanel.setBounds(200, 100, 940, 600);
        productsPanel.setVisible(true);
        
        mainContent.add(productsPanel);
        
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
    private GradientPanel topPanel = new GradientPanel(new Color(10, 15, 30), new Color(35, 40, 60), RADIAL);
    private IconButton sideMenuButton = new IconButton("images/sideMenuButtonIcon.png", 48, 48);
    private JButton sideMenuCloseButton = new JButton("Close");
    private JButton accountButton = new JButton("Account");
    private Timer animationTimer;
    private int sideMenuWidth = 0;
    private boolean sideMenuOpen = false;
    private final int maxSideMenuWidth = 150;
    private List<ProductItem> allProducts;
    private ProductsListPanel productsPanel;
    
    
}
