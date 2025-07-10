/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.components;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author denia
 */
public class LabelImage extends JLabel{
    
    public LabelImage (String imagePath, int width, int height){
        URL imgURL = getClass().getResource("/" + imagePath);
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(scaledImage));
        } else {
            setText("Image not found");
            setForeground(Color.RED);
        }
        
    }
    public void setNewImage(String imagePath, int width, int height){
        
        try {
        BufferedImage img = null;

        URL resource = getClass().getClassLoader().getResource(imagePath);
        if (resource != null) {
            img = ImageIO.read(resource);
        } else {
            File file = new File(imagePath);
            if (file.exists()) {
                img = ImageIO.read(file);
            } else {
                System.out.println(imagePath);
                throw new IOException("Image not found in classpath or file system: " + imagePath);
            }
        }

        Image scaled = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(scaled));
        revalidate();
        repaint();

    } catch (IOException e) {
        e.printStackTrace();
        setText("Image not found");
        setForeground(Color.RED);
    }
        
        
        
        
        
        
//        try {
//            BufferedImage img = ImageIO.read(new File(imagePath));
//            Image scaled = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
//            setIcon(new ImageIcon(scaled));
//            revalidate();
//            repaint();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    
}
