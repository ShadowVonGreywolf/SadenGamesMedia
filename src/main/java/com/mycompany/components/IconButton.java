package com.mycompany.sadengamesmedia;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author denia
 */
public class IconButton extends JButton{
    
    public IconButton(String ImagePath, int width, int height){
        URL iconUrl = getClass().getClassLoader().getResource(ImagePath);
        if (iconUrl != null) {
            ImageIcon originalIcon = new ImageIcon(iconUrl);
            Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(scaledImage));
        }
    }
    
}
