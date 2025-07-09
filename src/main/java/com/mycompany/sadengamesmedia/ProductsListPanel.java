/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sadengamesmedia;

import com.mycompany.components.ProductCardPanel;
import com.mycompany.sadengamesmedia.model.ProductItem;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author denia
 */
public class ProductsListPanel extends JPanel{
    
    public ProductsListPanel(List<ProductItem> productList) {
        setLayout(new BorderLayout());

        JPanel productsContainer = new JPanel();
        productsContainer.setLayout(new GridLayout(0, 3, 3, 3)); 
        productsContainer.setBackground(new Color(10,15,30));

        for (ProductItem p : productList) { 
            ProductCardPanel card = new ProductCardPanel(p.getTitle(), p.getRating(), p.getPrice(), p.getImagePath());
            productsContainer.add(card);
        }

        JScrollPane scrollPane = new JScrollPane(productsContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBackground(Color.BLACK);
        add(scrollPane, BorderLayout.CENTER);
    }
}
