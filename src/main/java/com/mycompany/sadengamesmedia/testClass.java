/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sadengamesmedia;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;

public class testClass extends JFrame {
    private final JPanel sideMenu = new JPanel();
    private final JPanel mainContent = new JPanel();
    private final JButton openButton = new JButton("Menu");
    private final JButton closeButton = new JButton("Close");

    private boolean isDrawerOpen = false;
    private Timer animationTimer;
    private int drawerWidth = 0;
    private final int maxDrawerWidth = 200;

    public testClass() {
        setTitle("Drawer Example");
        setSize(800, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setupDrawer();
        setupMainContent();
        setupOpenButton();

        setVisible(true);
        
//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setDialogTitle("Select Profile Picture");
//        fileChooser.setBounds(100, 100, 100, 100);
//        fileChooser.setVisible(true);
//        add(fileChooser);
        
        FileDialog fileDialog = new FileDialog((Frame) null, "Select Profile Picture", FileDialog.LOAD);
        fileDialog.setVisible(true);
        fileDialog.setBounds(100, 100, 100, 100);
        add(fileDialog);

        String selectedFile = fileDialog.getFile();
        String selectedDir = fileDialog.getDirectory();

        if (selectedFile != null) {
            File file = new File(selectedDir, selectedFile);
            ///storeImageInDatabase(file); // 🖼️ Your method to handle DB insert/update
        }


       
        
    }

    private void setupDrawer() {
        sideMenu.setBackground(Color.DARK_GRAY);
        sideMenu.setLayout(new BoxLayout(sideMenu, BoxLayout.Y_AXIS));
        sideMenu.setBounds(0, 0, drawerWidth, getHeight());
        add(sideMenu);

        // Align and size the close button properly
        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        closeButton.setMaximumSize(new Dimension(100, 40));
        closeButton.setFocusable(false);
        closeButton.addActionListener(e -> toggleDrawer());

        sideMenu.add(Box.createVerticalStrut(20)); // top spacing
        sideMenu.add(closeButton);
    }

    private void setupMainContent() {
        mainContent.setBackground(Color.WHITE);
        mainContent.setBounds(0, 0, getWidth(), getHeight());
        mainContent.setLayout(null);
        add(mainContent);
    }

    private void setupOpenButton() {
        openButton.setBounds(10, 10, 80, 30);
        openButton.setFocusable(false);
        openButton.addActionListener(e -> toggleDrawer());
        mainContent.add(openButton);
    }

    private void toggleDrawer() {
        if (animationTimer != null && animationTimer.isRunning()) return;

        isDrawerOpen = !isDrawerOpen;

        animationTimer = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int step = 10;
                if (isDrawerOpen) {
                    drawerWidth += step;
                    if (drawerWidth >= maxDrawerWidth) {
                        drawerWidth = maxDrawerWidth;
                        animationTimer.stop();
                    }
                } else {
                    drawerWidth -= step;
                    if (drawerWidth <= 0) {
                        drawerWidth = 0;
                        animationTimer.stop();
                    }
                }

                // Update drawer and content layout
                sideMenu.setBounds(0, 0, drawerWidth, getHeight());
                sideMenu.revalidate();
                sideMenu.repaint();
            }
        });

        animationTimer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(testClass::new);
    }
}
