/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.components;

/**
 *
 * @author denia
 */
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class GradientPanel extends JPanel {
    
    private Color color1;
    private Color color2;
    
    
    public enum GradientType {
        LINEAR,
        RADIAL
    }
    private GradientType gradientType = GradientType.LINEAR;
    
    public GradientPanel(Color color1 , Color color2, GradientType gradientType){
        this.color1 = color1;     
        this.color2 = color2;
        this.gradientType = gradientType;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();

        switch(gradientType){
            case RADIAL: 
                Point2D center = new Point2D.Float(width / 2f, height / 2f);
                float radius = Math.max(width, height);
                float[] dist = {0.0f, 1.0f};
                Color[] colors = {color2, color1}; // center to outer
                RadialGradientPaint radial = new RadialGradientPaint(center, radius * 0.5f, dist, colors);
                g2d.setPaint(radial);
                break;
                
            case LINEAR:
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
                g2d.setPaint(gp);
                break;
        
        }
        
        g2d.fillRect(0, 0, width, height);
    }
}
