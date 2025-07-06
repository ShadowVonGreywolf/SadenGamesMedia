/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sadengamesmedia;
import javax.swing.*;
import java.awt.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
/**
 *
 * @author denia
 */
public class testClass {
 public static void main(String[] args) {
        JTextArea textArea = new JTextArea(20, 30);
        for (int i = 0; i < 100; i++) textArea.append("Line " + i + "\n");

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.getVerticalScrollBar().setUI(new SlimScrollBarUI());

        JFrame frame = new JFrame("Modern Scrollbar");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(scrollPane);
        frame.pack();
        frame.setVisible(true);
    }

    static class SlimScrollBarUI extends BasicScrollBarUI {
        private final Dimension d = new Dimension();

        @Override
        protected JButton createDecreaseButton(int orientation) {
            return createZeroButton();
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return createZeroButton();
        }

        private JButton createZeroButton() {
            JButton button = new JButton();
            button.setPreferredSize(d);
            button.setMinimumSize(d);
            button.setMaximumSize(d);
            return button;
        }

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setPaint(new Color(100, 100, 255, 180)); // translucent blue
            g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 10, 10);
            g2.dispose();
        }

        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            // Optional: leave blank for a clean look
        }

        @Override
        protected Dimension getMaximumThumbSize() {
            return new Dimension(8, Integer.MAX_VALUE);
        }

        @Override
        protected Dimension getMinimumThumbSize() {
            return new Dimension(8, 30);
        }
}}

    

