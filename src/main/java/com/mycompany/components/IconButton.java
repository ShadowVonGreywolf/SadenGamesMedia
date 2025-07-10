package com.mycompany.components;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.awt.Image;
import java.io.File;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author denia
 */
public class IconButton extends JButton{
    private int width;
    private int height;
    
    public IconButton(String imagePath, int width, int height) {
        this.width = width;
        this.height = height;
        setNewIcon(imagePath, width, height);
    }

    public void setIcon(String imagePath, int width, int height) {
    File imageFile = new File(imagePath);
    if (imageFile.exists()) {
        ImageIcon icon = new ImageIcon(imageFile.getAbsolutePath());
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(scaledImage));
    } else {
        setText("Image not found");
    }
}

    public void setNewIcon(String imagePath, int width, int height) {
        ImageIcon icon = loadImage(imagePath);
        if (icon != null) {
            setIcon(icon);
        } else {
            System.err.println("Image not found: " + imagePath);
        }
    }

    private ImageIcon loadImage(String path) {
        Image image = null;

        File file = new File(path);
        if (file.exists()) {
            image = new ImageIcon(path).getImage();
        } else {
            URL resource = getClass().getClassLoader().getResource(path);
            if (resource != null) {
                image = new ImageIcon(resource).getImage();
            }
        }

        if (image != null) {
            return new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_SMOOTH));
        }

        return null;
    }
    
    
    
    
    
    
    
    
    
    
    
//    public IconButton(String ImagePath, int width, int height){
//        
//        URL iconUrl = getClass().getClassLoader().getResource(ImagePath);
//        this.width = width;
//        this.height= height;
//        if (iconUrl != null) {
//            ImageIcon originalIcon = new ImageIcon(iconUrl);
//            Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
//            setIcon(new ImageIcon(scaledImage));
//        }
//    }
//    public void setNewIcon(String ImagePath){
//        URL iconUrl = getClass().getClassLoader().getResource(ImagePath);
//        if (iconUrl != null) {
//            ImageIcon originalIcon = new ImageIcon(iconUrl);
//            Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
//            setIcon(new ImageIcon(scaledImage));
//        }
//    }
    
}
