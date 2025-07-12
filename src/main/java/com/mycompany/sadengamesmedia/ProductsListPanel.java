/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sadengamesmedia;

import com.mycompany.components.ProductCardPanel;
import com.mycompany.components.WrapLayout;
import com.mycompany.sadengamesmedia.model.Movie;
import com.mycompany.sadengamesmedia.model.ProductItem;
import com.mycompany.sadengamesmedia.model.Videogame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 *
 * @author denia
 */
public class ProductsListPanel extends JPanel{
    
    private JPanel productsContainer = new JPanel();
    private JScrollPane scrollPane;
    private List<ProductItem> gettingList = new ArrayList<>();
    
    
    public void checkBoxFilter(boolean xBox, boolean ps, List<ProductItem> list){
        productsContainer.removeAll(); 
        
        if(xBox == true && ps == true){
            for (ProductItem p : list) {
                    if (p instanceof Videogame) {
                        Videogame vg = (Videogame) p;

                        if (vg.getPlatform().toLowerCase().contains("ps") || vg.getPlatform().toLowerCase().contains("xbox")) {
                            ProductCardPanel card = new ProductCardPanel(vg.getTitle(), vg.getRating(), vg.getPrice(), vg.getImagePath());
                            card.addMouseListener(isClicked(p));
                            productsContainer.add(card);
                        }
                    }
                }
        }else if(xBox == false && ps == true){
                for (ProductItem p : list) {
                    if (p instanceof Videogame) {
                        Videogame vg = (Videogame) p;

                        if (vg.getPlatform().toLowerCase().contains("ps")) {
                            ProductCardPanel card = new ProductCardPanel(vg.getTitle(), vg.getRating(), vg.getPrice(), vg.getImagePath());
                            card.addMouseListener(isClicked(p));
                            productsContainer.add(card);
                        }
                    }
                }
            }else if(xBox == true && ps == false){
                    for (ProductItem p : list) {
                        if (p instanceof Videogame) {
                            Videogame vg = (Videogame) p;

                            if (vg.getPlatform().toLowerCase().contains("xbox")) {
                                ProductCardPanel card = new ProductCardPanel(vg.getTitle(), vg.getRating(), vg.getPrice(), vg.getImagePath());
                                card.addMouseListener(isClicked(p));
                                productsContainer.add(card);
                            }
                        }
                    }    
                }else if(xBox == false && ps == false){
                    for(ProductItem p : list){
                        ProductCardPanel card = new ProductCardPanel(p.getTitle(), p.getRating(), p.getPrice(), p.getImagePath());
                        card.addMouseListener(isClicked(p));
                        productsContainer.add(card);
                    }
            }
    }
    
    public void searchProductList(String searchString , List<ProductItem> productList){
        productsContainer.removeAll(); 

        for (ProductItem p : productList) {
            if (p.getTitle().toLowerCase().contains(searchString)) {
                ProductCardPanel card = new ProductCardPanel(p.getTitle(), p.getRating(), p.getPrice(), p.getImagePath());
                card.addMouseListener(isClicked(p));
                productsContainer.add(card);
            }
        }
        if(searchString.equals("Search"))
            for (ProductItem p : productList) {
                ProductCardPanel card = new ProductCardPanel(p.getTitle(), p.getRating(), p.getPrice(), p.getImagePath());
                card.addMouseListener(isClicked(p));
                productsContainer.add(card);
            }
        productsContainer.revalidate();
        productsContainer.repaint();
        
        }
    
    public void filterProductListPanel(String type, String price, String rating , List<ProductItem> productList){
        productsContainer.removeAll();
        
        List<ProductItem> filteredList = new ArrayList<>();
        
        for (ProductItem p : productList){
                if (type.equals("All") || (type.equals("Videogames") && p.getType().equals("Videogame")) || (type.equals("Movies") && p.getType().equals("Movie")))
                    filteredList.add(p);
        }
        if (!price.equals("None") && !rating.equals("None")) {
            if (price.equals("Low to High") && rating.equals("Low to High")) {
                filteredList.sort(
                    Comparator.comparingDouble(ProductItem::getPrice)
                              .thenComparingDouble(ProductItem::getRating)
                );
            } else if (price.equals("Low to High") && rating.equals("High to Low")) {
                filteredList.sort(
                    Comparator.comparingDouble(ProductItem::getPrice)
                              .thenComparingDouble(ProductItem::getRating).reversed()
                );
            } else if (price.equals("High to Low") && rating.equals("Low to High")) {
                filteredList.sort(
                    Comparator.comparingDouble(ProductItem::getPrice).reversed()
                              .thenComparingDouble(ProductItem::getRating)
                );
            } else if (price.equals("High to Low") && rating.equals("High to Low")) {
                filteredList.sort(
                    Comparator.comparingDouble(ProductItem::getPrice).reversed()
                              .thenComparingDouble(ProductItem::getRating).reversed()
                );
            }
        } else if (!price.equals("None")) {
            if (price.equals("Low to High")) {
                filteredList.sort(Comparator.comparingDouble(ProductItem::getPrice));
            } else if (price.equals("High to Low")) {
                filteredList.sort(Comparator.comparingDouble(ProductItem::getPrice).reversed());
            }
        } else if (!rating.equals("None")) {
            if (rating.equals("Low to High")) {
                filteredList.sort(Comparator.comparingDouble(ProductItem::getRating));
            } else if (rating.equals("High to Low")) {
                filteredList.sort(Comparator.comparingDouble(ProductItem::getRating).reversed());
            }
        }
        
        for (ProductItem p : filteredList) { 
            ProductCardPanel card = new ProductCardPanel(p.getTitle(), p.getRating(), p.getPrice(), p.getImagePath());
            card.addMouseListener(isClicked(p));
            productsContainer.add(card);
        }
        
        productsContainer.revalidate();
        productsContainer.repaint();
        gettingList.clear();
        gettingList.addAll(filteredList);
    }

    public List<ProductItem> getGettingList() {
        return gettingList;
    }
 
    public void setGettingList(List<ProductItem> gettingList) {
        this.gettingList = gettingList;
    }
    public void rangeFilter(double minPrice, double maxPrice, float minRating, float maxRating, List<ProductItem> list){
        
        productsContainer.removeAll();
        
        if(minPrice == 0 && maxPrice == 0 && minRating == 0 && maxRating == 0){
            for (ProductItem p : list) {
                ProductCardPanel card = new ProductCardPanel(p.getTitle(), p.getRating(), p.getPrice(), p.getImagePath());
                card.addMouseListener(isClicked(p));
                productsContainer.add(card);
            }
            productsContainer.revalidate();
            productsContainer.repaint();
            return;
            
        }
    
    
    for (ProductItem p : list) {
        double price = p.getPrice();
        float rating = p.getRating();
        
        boolean matches = true;
        
        if (minPrice > 0 && price < minPrice) {
            matches = false;
        }
        
        if (maxPrice > 0 && price > maxPrice) {
            matches = false;
        }
       
        if (minRating > 0 && rating < minRating) {
            matches = false;
        }
        
        if (maxRating > 0 && rating > maxRating) {
            matches = false;
        }

        if (matches == true) {
            ProductCardPanel card = new ProductCardPanel(p.getTitle(), rating, price, p.getImagePath());
            card.addMouseListener(isClicked(p));
            productsContainer.add(card);
        }
    }

    productsContainer.revalidate();
    productsContainer.repaint();
    }
    
    public List<ProductItem> reloadAllProducts(List<ProductItem> list) {
        productsContainer.removeAll();
        gettingList.clear();

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM products");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String genre = rs.getString("genre");
                float rating = rs.getFloat("rating");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                String imagePath = rs.getString("image_path");
                String type = rs.getString("product_type");
                int stock = rs.getInt("stock");

                if ("Videogame".equalsIgnoreCase(type)) {
                    try (PreparedStatement vgStmt = conn.prepareStatement(
                            "SELECT platform, studio FROM games WHERE games_id = ?")) {
                        vgStmt.setInt(1, id);
                        try (ResultSet vgRs = vgStmt.executeQuery()) {
                            if (vgRs.next()) {
                                String platform = vgRs.getString("platform");
                                String studio = vgRs.getString("studio");
                                String fullImagePath = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", imagePath).toString();
                                Videogame vg = new Videogame(id, title, genre, rating, description, price, fullImagePath, type, stock, platform, studio);
                                gettingList.add(vg);
                                list.add(vg);
                                ProductItem p = new ProductItem(id, title, genre, rating, description, price, fullImagePath, type, stock);
                                ProductCardPanel card = new ProductCardPanel(vg.getTitle(), vg.getRating(), vg.getPrice(), fullImagePath);
                                card.addMouseListener(isClicked(p));
                                productsContainer.add(card);

                            } else {
                                System.out.println("No videogame data found for games_id " + id);
                            }
                        }
                    }
                } else if ("Movie".equalsIgnoreCase(type)) {
                    try (PreparedStatement mvStmt = conn.prepareStatement(
                            "SELECT director, duration FROM movies WHERE movie_id = ?")) {
                        mvStmt.setInt(1, id);
                        try (ResultSet mvRs = mvStmt.executeQuery()) {
                            if (mvRs.next()) {
                                String director = mvRs.getString("director");
                                int duration = mvRs.getInt("duration");
                                
                                String fullImagePath = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", imagePath).toString();
                                Movie mv = new Movie(id, title, genre, rating, description, price, fullImagePath, type, stock, director, duration);
                                gettingList.add(mv);
                                list.add(mv);
                                ProductItem p = new ProductItem(id, title, genre, rating, description, price, fullImagePath, type, stock);
                                ProductCardPanel card = new ProductCardPanel(mv.getTitle(), mv.getRating(), mv.getPrice(), mv.getImagePath()) ;
                                card.addMouseListener(isClicked(p));
                                productsContainer.add(card);
                            } else {
                                System.out.println("No movie data found for movie_id " + id);
                            }
                        }
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to load products from database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        productsContainer.revalidate();
        productsContainer.repaint();
        return list;
    }
    
    private MouseAdapter isClicked(ProductItem p){
        return new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                remove(cardPage); 
                cardPage = new CardPage(p, ProductsListPanel.this, scrollPane); 
                cardPage.setBounds(0, 0, 970, 600); 
                
                cardPage.deleteModifyBackBuyTrue();
                cardPage.setVisible(true);
                add(cardPage); 
                scrollPane.setVisible(false);

                revalidate();
                repaint();
                
            }
        };
    }
    
    public void scrollPaneTrue(){
        scrollPane.setVisible(true);
        revalidate();
        repaint();
    }
    
    
    public ProductsListPanel(List<ProductItem> productList) {
        setLayout(null);

        
        
        gettingList.clear();
        gettingList.addAll(productList);
        productsContainer.setLayout(new WrapLayout(FlowLayout.LEFT, 30, 20)); 
        productsContainer.setBackground(new Color(10,15,30));

        for (ProductItem p : productList) { 
            ProductCardPanel card = new ProductCardPanel(p.getTitle(), p.getRating(), p.getPrice(), p.getImagePath());
            card.addMouseListener(isClicked(p));
            productsContainer.add(card);
        }

        scrollPane = new JScrollPane(productsContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(0,0,130);
            }
        });
        scrollPane.setBackground(Color.BLACK);
        scrollPane.setBounds(0, 0, 970, 600);
        add(scrollPane, BorderLayout.CENTER);
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(80, 90, 130);      
                this.trackColor = new Color(30, 35, 60);        
            }

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
                button.setPreferredSize(new Dimension(0, 0));
                button.setVisible(false);
                return button;
            }
        });
        
        cardPage.setVisible(false);
        cardPage.setBounds(0, 0, 970, 600);
        
        add(cardPage);
        
        
        
        
        
    }
    private CardPage cardPage = new CardPage();;
}
