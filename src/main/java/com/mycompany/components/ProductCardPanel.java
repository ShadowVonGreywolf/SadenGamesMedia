/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author denia
 */
public class ProductCardPanel extends JPanel{
    
    public ProductCardPanel(String title, float rating, double price, String imagePath){
        setPreferredSize(new Dimension(270, 320));
        setMaximumSize(new Dimension(270, 320));
        setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2 )); 
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
       
        
        labelImage = new LabelImage(imagePath, 180, 200 );
        labelImage.setPreferredSize(new Dimension(180, 200));
        labelImage.setMaximumSize(new Dimension(180, 200));
        labelImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JTextPane titlePane = new JTextPane();
        titlePane.setText(title);
        titlePane.setEditable(false);
        titlePane.setFocusable(false);
        titlePane.setOpaque(false);
        titlePane.setForeground(Color.WHITE);
        titlePane.setFont(new Font("Courier New", Font.BOLD, 18));
        titlePane.setBorder(null);
        titlePane.setAlignmentX(Component.CENTER_ALIGNMENT);
        

        StyledDocument doc = titlePane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        
        ratingLabel = new JLabel("Rating : " + rating + "/5");
        ratingLabel.setHorizontalAlignment(JLabel.CENTER);
        ratingLabel.setForeground(Color.YELLOW);
        ratingLabel.setFont(new Font("Courier New", Font.BOLD, 14));
        ratingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        priceLabel = new JLabel("Price: " + price + "$");
        priceLabel.setHorizontalAlignment(JLabel.CENTER);
        priceLabel.setForeground(new Color(255, 114, 118));
        priceLabel.setFont(new Font("Courier New", Font.BOLD, 16));
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        

        add(labelImage);
        add(Box.createVerticalStrut(10));
        add(titlePane);
        add(Box.createVerticalStrut(10));
        add(priceLabel);
        add(Box.createVerticalStrut(10));
        add(ratingLabel);
        revalidate();
        repaint();
        
    }
    
    public enum GradientType {
        LINEAR,
        RADIAL
    }
    private GradientType gradientType = GradientType.RADIAL;
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();
        
        Color color1 = new Color(10,15,30);  
        Color color2 = new Color(30,40,80);  
        switch (gradientType) {
            case RADIAL:
                Point2D center = new Point2D.Float(width / 2f, height / 2f);
                float radius = Math.max(width, height);
                float[] dist = {0.0f, 1.0f};
                Color[] colors = {color2, color1}; 
                RadialGradientPaint radial = new RadialGradientPaint(center, radius* 0.5f, dist, colors);
                g2d.setPaint(radial);
                break;

            case LINEAR:
            default:
                GradientPaint linear = new GradientPaint(0, 0, color1, 0, height, color2);
                g2d.setPaint(linear);
                break;
        }
        g2d.fillRect(0, 0, width, height);
    }
    
    public String insertLineBreaks(String text, int maxLineLength) {
        StringBuilder sb = new StringBuilder("<html>");
        int count = 0;
        for (String word : text.split(" ")) {
            if (count + word.length() > maxLineLength) {
                sb.append("<br>");
                count = 0;
            }
            sb.append(word).append(" ");
            count += word.length() + 1;
        }
        sb.append("</html>");
        return sb.toString();
    }
    
    
    private LabelImage labelImage;
    private JLabel priceLabel;
    private JLabel ratingLabel;
}
