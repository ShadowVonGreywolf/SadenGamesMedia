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
    private int width ;
    private int height;
    public LabelImage (String imagePath, int width, int height){
//        URL imgURL = getClass().getResource("/" + imagePath);
//        if (imgURL != null) {
//            ImageIcon icon = new ImageIcon(imgURL);
//            Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
//            setIcon(new ImageIcon(scaledImage));
//        } else {
//            setText("Image not found");
//            setForeground(Color.RED);
//        }
            this.width = width;
            this.height = height;
            setNewImage(imagePath, width, height);
        
    }
    public void setNewImage(String imagePath, int width, int height){
        
        BufferedImage img = null;

    try {
        System.out.println("Trying to load image from classpath: " + imagePath);
        // Try classpath resource (no leading slash!)
        URL resource = getClass().getClassLoader().getResource(imagePath);

        if (resource != null) {
            System.out.println("Found image in classpath: " + resource);
            img = ImageIO.read(resource);
        } else {
            System.out.println("Image not found in classpath, trying file system...");

            File file = new File(imagePath);
            System.out.println("File path to check: " + file.getAbsolutePath());

            if (file.exists()) {
                System.out.println("Found image in file system");
                img = ImageIO.read(file);
            } else {
                throw new IOException("Image not found in classpath or file system: " + imagePath);
            }
        }

        // Resize and set icon
        Image scaled = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(scaled));
        setText(null);
        revalidate();
        repaint();

    } catch (IOException e) {
        e.printStackTrace();
        setText("Image not found");
        setForeground(Color.RED);
    }
        
        
        
        
        
        
        
        
        
        
//        try {
//        BufferedImage img = null;
//
//        URL resource = getClass().getClassLoader().getResource(imagePath);
//        if (resource != null) {
//            img = ImageIO.read(resource);
//        } else {
//            File file = new File(imagePath);
//            if (file.exists()) {
//                img = ImageIO.read(file);
//            } else {
//                System.out.println(imagePath);
//                throw new IOException("Image not found in classpath or file system: " + imagePath);
//            }
//        }
//
//        Image scaled = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
//        setIcon(new ImageIcon(scaled));
//        revalidate();
//        repaint();
//
//    } catch (IOException e) {
//        e.printStackTrace();
//        setText("Image not found");
//        setForeground(Color.RED);
//    }
}
        public void loadPreviewFromFile(File imageFile, int width, int height) {
        try {
            if (imageFile != null && imageFile.exists()) {
                BufferedImage img = ImageIO.read(imageFile);
                Image scaled = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                setIcon(new ImageIcon(scaled));
                setText(null);
                setForeground(Color.BLACK);
                revalidate();
                repaint();
            } else {
                throw new IOException("Preview file doesn't exist: " + imageFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
            setText("Preview failed");
            setForeground(Color.RED);
        }
    }
    
}
