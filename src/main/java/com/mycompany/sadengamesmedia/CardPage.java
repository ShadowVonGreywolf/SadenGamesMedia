/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sadengamesmedia;

import com.mycompany.components.IconButton;
import com.mycompany.components.LabelImage;
import com.mycompany.sadengamesmedia.model.Movie;
import com.mycompany.sadengamesmedia.model.ProductItem;
import com.mycompany.sadengamesmedia.model.Session;
import com.mycompany.sadengamesmedia.model.Videogame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 *
 * @author denia
 */
public class CardPage extends JPanel{
    ProductsListPanel parentPanel;
    JScrollPane scrollPane;
    public CardPage(){
    
    }
    
    public CardPage(ProductItem item, ProductsListPanel parent , JScrollPane scrollpane){
    
    this.parentPanel = parent;
    this.scrollPane = scrollpane;
    initComponents(item, parentPanel);
    }
    private void initComponents(ProductItem p , ProductsListPanel parentP){
        setLayout(null);
        setBackground(new Color(10,15,20));
        
        productImage = new LabelImage(p.getImagePath(), 250, 250);
        productImage.setBounds(50, 50, 250, 250);
        productImage.setVisible(true);
        add(productImage);
        
        
        choosePhoto.setVisible(false);
        choosePhoto.setForeground(Color.CYAN);
        choosePhoto.setFont(new Font("Arial", Font.BOLD, 15));
        choosePhoto.setBackground(new Color(10,25,40));
        choosePhoto.setOpaque(true);
        choosePhoto.setContentAreaFilled(true);           
        choosePhoto.setBorderPainted(false);
        choosePhoto.setFocusPainted(false);
        choosePhoto.setBounds(50, 320, 250, 50);
        choosePhoto.addMouseListener(choosePhotoAction());
        add(choosePhoto);
        
        
        descriptionLabel.setVisible(true);
        descriptionLabel.setBounds(50, 380, 200, 50);
        descriptionLabel.setFont(new Font("Courier New", Font.BOLD, 30));
        descriptionLabel.setForeground(new Color(102, 100, 204));
        add(descriptionLabel);
        
        
        descriptionArea.setText(p.getDescription());
        descriptionArea.setBackground(new Color(10,25,40));
        descriptionArea.setForeground(Color.CYAN);
        descriptionArea.setFont(new Font("Courier New", Font.ITALIC, 15));
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        descriptionArea.setVisible(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        
        
        descriptionScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        descriptionScroll.getVerticalScrollBar().setUnitIncrement(16);
        descriptionScroll.setBorder(null);
        descriptionScroll.setVisible(true);
        descriptionScroll.setBounds(50, 430, 400, 150);
        descriptionScroll.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(0,0,130);
            }
        });
        descriptionScroll.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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
        add(descriptionScroll);
        
        
        titleLabel.setVisible(true);
        titleLabel.setBounds(340, 50, 200, 50);
        titleLabel.setFont(new Font("Courier New", Font.BOLD, 30));
        titleLabel.setForeground(new Color(102, 100, 204));
        add(titleLabel);
        
        
        titleText.setText(p.getTitle());
        titleText.setBounds(540, 50, 230, 50);
        titleText.setBackground(new Color(10,25,40));
        titleText.setForeground(Color.CYAN);
        titleText.setFont(new Font("Courier New", Font.ITALIC, 15));
        titleText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        titleText.setVisible(true);
        titleText.setEditable(false);
        add(titleText);
        
        
        genreLabel.setVisible(true);
        genreLabel.setBounds(340, 110, 200, 50);
        genreLabel.setFont(new Font("Courier New", Font.BOLD, 30));
        genreLabel.setForeground(new Color(102, 100, 204));
        add(genreLabel);
        
        
        genreText.setText(p.getGenre());
        genreText.setBounds(540, 110, 230, 50);
        genreText.setBackground(new Color(10,25,40));
        genreText.setForeground(Color.CYAN);
        genreText.setFont(new Font("Courier New", Font.ITALIC, 20));
        genreText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        genreText.setVisible(true);
        genreText.setEditable(false);
        add(genreText);
        
        
        ratingLabel.setVisible(true);
        ratingLabel.setBounds(340, 170, 200, 50);
        ratingLabel.setFont(new Font("Courier New", Font.BOLD, 30));
        ratingLabel.setForeground(new Color(102, 100, 204));
        add(ratingLabel);
        
        
        ratingText.setText(String.valueOf(p.getRating()));
        ratingText.setBounds(540, 170, 80, 50);
        ratingText.setBackground(new Color(10,25,40));
        ratingText.setForeground(Color.CYAN);
        ratingText.setFont(new Font("Courier New", Font.ITALIC, 20));
        ratingText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        ratingText.setVisible(true);
        ratingText.setEditable(false);
        add(ratingText);
        
        rt.setVisible(true);
        rt.setBounds(640, 170, 100, 50);
        rt.setFont(new Font("Courier New", Font.BOLD, 30));
        rt.setForeground(new Color(102, 100, 204));
        add(rt);
        
        priceLabel.setVisible(true);
        priceLabel.setBounds(340, 230, 200, 50);
        priceLabel.setFont(new Font("Courier New", Font.BOLD, 30));
        priceLabel.setForeground(new Color(102, 100, 204));
        add(priceLabel);
        
        priceText.setText(String.valueOf(p.getPrice()));
        priceText.setBounds(540, 230, 80, 50);
        priceText.setBackground(new Color(10,25,40));
        priceText.setForeground(Color.CYAN);
        priceText.setFont(new Font("Courier New", Font.ITALIC, 18));
        priceText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        priceText.setVisible(true);
        priceText.setEditable(false);
        add(priceText);
        
        
        pr.setVisible(true);
        pr.setBounds(640, 230, 50, 50);
        pr.setFont(new Font("Courier New", Font.BOLD, 30));
        pr.setForeground(new Color(102, 100, 204));
        add(pr);
        
        
        stockLabel.setVisible(true);
        stockLabel.setBounds(720, 230, 150, 50);
        stockLabel.setFont(new Font("Courier New", Font.BOLD, 25));
        stockLabel.setForeground(new Color(102, 100, 204));
        add(stockLabel);
        
        stockText.setText(String.valueOf(p.getStock()));
        stockText.setBounds(840, 230, 60, 50);
        stockText.setBackground(new Color(10,25,40));
        stockText.setForeground(Color.CYAN);
        stockText.setFont(new Font("Courier New", Font.ITALIC, 15));
        stockText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        stockText.setVisible(true);
        stockText.setEditable(false);
        add(stockText);
        
        typeLabel.setVisible(true);
        typeLabel.setBounds(340, 290, 200, 50);
        typeLabel.setFont(new Font("Courier New", Font.BOLD, 30));
        typeLabel.setForeground(new Color(102, 100, 204));
        add(typeLabel);
        
        
        typeText.setText(String.valueOf(p.getType()));
        typeText.setBounds(540, 290, 230, 50);
        typeText.setBackground(new Color(10,25,40));
        typeText.setForeground(Color.CYAN);
        typeText.setFont(new Font("Courier New", Font.ITALIC, 15));
        typeText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        typeText.setVisible(true);
        typeText.setEditable(false);
        add(typeText);
        
        if (p instanceof Videogame) {
            Videogame vg = (Videogame) p;
            
            platformLabel.setVisible(true);
            platformLabel.setBounds(500, 350, 200, 50);
            platformLabel.setFont(new Font("Courier New", Font.BOLD, 30));
            platformLabel.setForeground(new Color(102, 100, 204));
            add(platformLabel);

            
            platformText.setText(vg.getPlatform());
            platformText.setBounds(710, 350, 230, 50);
            platformText.setBackground(new Color(10,25,40));
            platformText.setForeground(Color.CYAN);
            platformText.setFont(new Font("Courier New", Font.ITALIC, 15));
            platformText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            platformText.setVisible(true);
            platformText.setEditable(false);
            add(platformText);

            
            studioLabel.setVisible(true);
            studioLabel.setBounds(500, 410, 200, 50);
            studioLabel.setFont(new Font("Courier New", Font.BOLD, 30));
            studioLabel.setForeground(new Color(102, 100, 204));
            add(studioLabel);

            
            studioText.setText(vg.getStudio());
            studioText.setBounds(710, 410, 230, 50);
            studioText.setBackground(new Color(10,25,40));
            studioText.setForeground(Color.CYAN);
            studioText.setFont(new Font("Courier New", Font.ITALIC, 15));
            studioText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            studioText.setVisible(true);
            studioText.setEditable(false);
            add(studioText);
        }else if(p instanceof Movie){
            Movie mv = (Movie) p;
            
            directorLabel.setVisible(true);
            directorLabel.setBounds(500, 350, 200, 50);
            directorLabel.setFont(new Font("Courier New", Font.BOLD, 30));
            directorLabel.setForeground(new Color(102, 100, 204));
            add(directorLabel);

            
            directorText.setText(mv.getDirector());
            directorText.setBounds(710, 350, 230, 50);
            directorText.setBackground(new Color(10,25,40));
            directorText.setForeground(Color.CYAN);
            directorText.setFont(new Font("Courier New", Font.ITALIC, 15));
            directorText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            directorText.setVisible(true);
            directorText.setEditable(false);
            add(directorText);

            
            durationLabel.setVisible(true);
            durationLabel.setBounds(500, 410, 200, 50);
            durationLabel.setFont(new Font("Courier New", Font.BOLD, 30));
            durationLabel.setForeground(new Color(102, 100, 204));
            add(durationLabel);

            
            durationText.setText(String.valueOf(mv.getDuration()));
            durationText.setBounds(710, 410, 80, 50);
            durationText.setBackground(new Color(10,25,40));
            durationText.setForeground(Color.CYAN);
            durationText.setFont(new Font("Courier New", Font.ITALIC, 18));
            durationText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            durationText.setVisible(true);
            durationText.setEditable(false);
            add(durationText);
            
            
            minutes.setVisible(true);
            minutes.setBounds(810, 410, 100, 50);
            minutes.setFont(new Font("Courier New", Font.BOLD, 20));
            minutes.setForeground(new Color(102, 100, 204));
            add(minutes);
        }
        
        
        deleteItem.setVisible(false);
        deleteItem.setForeground(Color.CYAN);
        deleteItem.setFont(new Font("Arial", Font.BOLD, 15));
        deleteItem.setBackground(new Color(90,25,40));
        deleteItem.setOpaque(true);
        deleteItem.setContentAreaFilled(true);           
        deleteItem.setBorderPainted(false);
        deleteItem.setFocusPainted(false);
        deleteItem.setBounds(740, 500, 180, 50);
        deleteItem.addMouseListener(deleteItemAction(p,parentP));
        add(deleteItem);
        
        
        modifyItem.setVisible(false);
        modifyItem.setForeground(Color.CYAN);
        modifyItem.setFont(new Font("Arial", Font.BOLD, 15));
        modifyItem.setBackground(new Color(90, 90, 20));
        modifyItem.setOpaque(true);
        modifyItem.setContentAreaFilled(true);           
        modifyItem.setBorderPainted(false);
        modifyItem.setFocusPainted(false);
        modifyItem.setBounds(550, 500, 180, 50);
        modifyItem.addMouseListener(modifyItemAction(p));
        add(modifyItem);
        
        
        backButton.setBounds(480, 500, 50, 50);
        backButton.setVisible(true);
        backButton.setOpaque(false);                     
        backButton.setContentAreaFilled(false);           
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.addMouseListener(backAction());
        add(backButton);
         
        
        saveItem.setVisible(false);
        saveItem.setForeground(Color.CYAN);
        saveItem.setFont(new Font("Arial", Font.BOLD, 15));
        saveItem.setBackground(new Color(10,80,40));
        saveItem.setOpaque(true);
        saveItem.setContentAreaFilled(true);           
        saveItem.setBorderPainted(false);
        saveItem.setFocusPainted(false);
        saveItem.setBounds(740, 500, 180, 50);
        saveItem.addMouseListener(saveItemAction(p));
        add(saveItem);
        
        
        buyItem.setVisible(false);
        buyItem.setForeground(Color.CYAN);
        buyItem.setFont(new Font("Arial", Font.BOLD, 15));
        buyItem.setBackground(new Color(10,80,40));
        buyItem.setOpaque(true);
        buyItem.setContentAreaFilled(true);           
        buyItem.setBorderPainted(false);
        buyItem.setFocusPainted(false);
        buyItem.setBounds(740, 500, 180, 50);
        buyItem.addMouseListener(buyItemAction(p));
        add(buyItem);
        
        
    }
    
    private void buyAction(int pId, int aId , double price, int quantity){
        try{
            Connection conn = DatabaseManager.getConnection();
            conn.setAutoCommit(false);
            String sql = "INSERT INTO sales (product_id, account_id, quantity, price) VALUES (?, ?, ?, ?)";
            PreparedStatement productStmt = conn.prepareStatement(sql);
            productStmt.setInt(1, pId);
            productStmt.setInt(2, aId);
            productStmt.setInt(3, quantity);
            productStmt.setDouble(4, price); 
            productStmt.executeUpdate();
            
            String updateStock = "UPDATE products SET stock = stock - ? WHERE id = ?";
            try (PreparedStatement stockStmt = conn.prepareStatement(updateStock)) {
                stockStmt.setInt(1, quantity);
                stockStmt.setInt(2, pId);
                stockStmt.executeUpdate();
            }
            conn.commit(); 
            conn.setAutoCommit(true);
        }catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"Error! Database error.","Account error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private MouseAdapter buyItemAction(ProductItem p){
        return new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                int productId = p.getId();
                int accountId = Session.getCurrentUser().getUserId();
                double price = Double.parseDouble(priceText.getText());
                
                String input = JOptionPane.showInputDialog(null, "How many units would you like to buy?", "Purchase", JOptionPane.QUESTION_MESSAGE);
                if (input != null) {
                    try {
                        int quantity = Integer.parseInt(input);
                        if (quantity <= 0) {
                            JOptionPane.showMessageDialog(null, "Quantity must be greater than zero!");
                            return;
                        }
                        int stock = Integer.parseInt(stockText.getText());
                        if(quantity > stock){
                            JOptionPane.showMessageDialog(null, "Insufficient stocks!");
                        }else{
                        buyAction(productId, accountId, price, quantity);
                        JOptionPane.showMessageDialog(null, "Purchase successful!");
                        stockText.setText(String.valueOf(stock - quantity));
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                    } catch (Exception exc) {
                        JOptionPane.showMessageDialog(null, "Purchase failed: " + exc.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Purchase canceled.");
                }
                        
            }
        };
    }
    
    

    
    
    public void setTextEditableTrue(ProductItem p){
        descriptionArea.setEditable(true);
        titleText.setEditable(true);
        genreText.setEditable(true);
        ratingText.setEditable(true);
        priceText.setEditable(true);
        typeText.setEditable(true);
        stockText.setEditable(true);
        if(p.getType().toLowerCase().equals("videogame")){
            platformText.setEditable(true);
            studioText.setEditable(true);
        }else if(p.getType().toLowerCase().equals("movie")){
            directorText.setEditable(true);
            durationText.setEditable(true);
        }
    }
    private void cancelSettings(ProductItem p){
        
        if(!descriptionArea.getText().toLowerCase().equals(p.getDescription().toLowerCase()))
            descriptionArea.setText(p.getDescription());
        if(!titleText.getText().toLowerCase().equals(p.getTitle().toLowerCase()))
            titleText.setText(p.getTitle());
        if(!genreText.getText().toLowerCase().equals(p.getGenre()))
            genreText.setText(p.getGenre());
        double price = Double.parseDouble(priceText.getText());
        float rating = Float.parseFloat(ratingText.getText());
        if(price != p.getPrice())
            priceText.setText(String.valueOf(p.getPrice()));
        if(rating != p.getRating())
            ratingText.setText(String.valueOf(p.getRating()));
        if(!typeText.getText().toLowerCase().equals(p.getType()))
            typeText.setText(p.getType());
        int stock = Integer.parseInt(stockText.getText());
        if(stock != p.getStock())
            stockText.setText(String.valueOf(p.getStock()));
        if(p.getType().toLowerCase().equals("videogame")){
            Videogame vg = (Videogame) p;
            if(!platformText.getText().toLowerCase().equals(vg.getPlatform()))
                platformText.setText(vg.getPlatform());
            if(!studioText.getText().toLowerCase().equals(vg.getStudio()))
                studioText.setText(vg.getStudio());
        }else if(p.getType().toLowerCase().equals("movie")){
            Movie mv = (Movie) p;
            if(!directorText.getText().toLowerCase().equals(mv.getDirector()))
                directorText.setText(mv.getDirector());
            int duration = Integer.parseInt(durationText.getText());
            if(duration != mv.getDuration())
                durationText.setText(String.valueOf(mv.getDuration()));
            
        }
    }
    
   private MouseAdapter saveItemAction(ProductItem p) {
    return new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            String title = titleText.getText().trim();
            String genre = genreText.getText().trim();
            String ratingStr = ratingText.getText().trim();
            String priceStr = priceText.getText().trim();
            String description = descriptionArea.getText().trim();
            String stockStr = stockText.getText().trim();
            float rating;
            double price;
            int stock;
            if (title.isEmpty() || genre.isEmpty() || ratingStr.isEmpty() || priceStr.isEmpty() || description.isEmpty() || stockStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill out all required fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                rating = Float.parseFloat(ratingStr);
                price = Double.parseDouble(priceStr);
                stock = Integer.parseInt(stockStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid rating or price format!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String imagePathInProject = p.getImagePath();

            if (selectedImageFile != null && selectedImageFile.exists()) {
                try {
                    String imageDir = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "product_images").toString();
                    Files.createDirectories(Paths.get(imageDir));

                    String imageName = "product_" + p.getId() + "_" + System.currentTimeMillis() + ".png";
                    Path newImagePath = Paths.get(imageDir, imageName);
                    Files.copy(selectedImageFile.toPath(), newImagePath, StandardCopyOption.REPLACE_EXISTING);

                    imagePathInProject = "product_images/" + imageName;

                    File oldImage = new File(System.getProperty("user.dir"), "src/main/resources/" + p.getImagePath());
                    if (oldImage.exists()) {
                        oldImage.delete();
                        System.out.println("Old image deleted: " + oldImage.getAbsolutePath());
                    }

                    System.out.println("New image saved: " + newImagePath.toString());

                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error saving new image!", "Image Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            try (Connection conn = DatabaseManager.getConnection()) {
                PreparedStatement updateProduct = conn.prepareStatement(
                        "UPDATE products SET title = ?, genre = ?, rating = ?, description = ?, price = ?, image_path = ?, stock = ? WHERE id = ?");
                updateProduct.setString(1, title);
                updateProduct.setString(2, genre);
                updateProduct.setFloat(3, rating);
                updateProduct.setString(4, description);
                updateProduct.setDouble(5, price);
                updateProduct.setString(6, imagePathInProject);
                updateProduct.setInt(7, stock);
                updateProduct.setInt(8, p.getId());
                updateProduct.executeUpdate();

                if ("Videogame".equalsIgnoreCase(typeText.getText().trim())) {
                    String platform = platformText.getText().trim();
                    String studio = studioText.getText().trim();
                    PreparedStatement updateGame = conn.prepareStatement(
                            "UPDATE games SET platform = ?, studio = ? WHERE games_id = ?");
                    updateGame.setString(1, platform);
                    updateGame.setString(2, studio);
                    updateGame.setInt(3, p.getId());
                    updateGame.executeUpdate();
                } else if ("Movie".equalsIgnoreCase(typeText.getText().trim())) {
                    String director = directorText.getText().trim();
                    int duration = Integer.parseInt(durationText.getText());
                    PreparedStatement updateMovie = conn.prepareStatement(
                            "UPDATE movies SET director = ?, duration = ? WHERE movie_id = ?");
                    updateMovie.setString(1, director);
                    updateMovie.setInt(2, duration);
                    updateMovie.setInt(3, p.getId());
                    updateMovie.executeUpdate();
                }

                p.setTitle(title);
                p.setGenre(genre);
                p.setRating(rating);
                p.setDescription(description);
                p.setPrice(price);
                p.setImagePath(imagePathInProject);
                p.setStock(stock);
                
                modifyItem.setText("Modify");
                modifyItem.setBackground(new Color(90, 90, 20));
                saveItem.setVisible(false);
                deleteItem.setVisible(true);
                
                JOptionPane.showMessageDialog(null, "Product updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database error while saving product!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    };
}

   private MouseAdapter deleteItemAction(ProductItem p, ProductsListPanel parentP) {
    return new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int confirm = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this product?","Confirm Deletion",JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }
            try (Connection conn = DatabaseManager.getConnection()) {
                if ("Videogame".equalsIgnoreCase(p.getType())) {
                    PreparedStatement deleteGame = conn.prepareStatement("DELETE FROM games WHERE games_id = ?");
                    deleteGame.setInt(1, p.getId());
                    deleteGame.executeUpdate();
                } else if ("Movie".equalsIgnoreCase(p.getType())) {
                    PreparedStatement deleteMovie = conn.prepareStatement("DELETE FROM movies WHERE movie_id = ?");
                    deleteMovie.setInt(1, p.getId());
                    deleteMovie.executeUpdate();
                }
                PreparedStatement deleteProduct = conn.prepareStatement("DELETE FROM products WHERE id = ?");
                deleteProduct.setInt(1, p.getId());
                deleteProduct.executeUpdate();
                File imageFile = new File(System.getProperty("user.dir"), "src/main/resources/" + p.getImagePath());
                if (imageFile.exists()) {
                    imageFile.delete();
                    System.out.println("Deleted image: " + imageFile.getAbsolutePath());
                }
                JOptionPane.showMessageDialog(null, "Product deleted successfully!", "Deleted", JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
                parentP.scrollPaneTrue();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error deleting product from database.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    };
}

   
   
   

    
    private boolean modifyItemClicked = false;
    private MouseAdapter modifyItemAction(ProductItem p){
        return new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                modifyItemClicked = !modifyItemClicked;
                if(modifyItemClicked){
                    choosePhoto.setVisible(true);
                    modifyItem.setText("Cancel");
                    modifyItem.setBackground(new Color(75, 25, 40));
                    deleteItem.setVisible(false);
                    saveItem.setVisible(true);
                    setTextEditableTrue(p);
                    
                }else{
                    cancelSettings(p);
                    choosePhoto.setVisible(false);
                    modifyItem.setText("Modify");
                    modifyItem.setBackground(new Color(90, 90, 20));
                    saveItem.setVisible(false);
                    deleteItem.setVisible(true);
                
                }
            }
        };
    }
    
    
    
    private MouseAdapter choosePhotoAction(){
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FileDialog fileDialog = new FileDialog((Frame) SwingUtilities.getWindowAncestor(CardPage.this), "Select Product Image", FileDialog.LOAD);
                fileDialog.setVisible(true);

                String directory = fileDialog.getDirectory();
                String selectedFile = fileDialog.getFile();

                if (directory != null && selectedFile != null &&
                    (selectedFile.endsWith(".jpg") || selectedFile.endsWith(".png") || selectedFile.endsWith(".jpeg"))) {

                    selectedImageFile = new File(directory, selectedFile);

                    productImage.loadPreviewFromFile(selectedImageFile, 250, 250);
                }
            }
        };
    }
    
    
    public void deleteModifyBackBuyTrue(){
        backButton.setVisible(true);
        
        if(Session.getCurrentUser().getRole().toLowerCase().equals("admin")){
            modifyItem.setVisible(true);
            deleteItem.setVisible(true);
            buyItem.setVisible(false);
        }else{
            buyItem.setVisible(true);
        }
    }
    public void deleteModifyBackBuyFalse(){
        backButton.setVisible(false);
        modifyItem.setVisible(false);
        deleteItem.setVisible(false);
        buyItem.setVisible(false);
    }
    private MouseAdapter backAction(){
        return new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                
                setVisible(false);
                deleteModifyBackBuyFalse();
                scrollPane.setVisible(true);
                parentPanel.revalidate();
                parentPanel.repaint();
            }
        };
    }
    
    private LabelImage productImage;
    private JButton choosePhoto = new JButton("Choose a new photo");
    private JLabel descriptionLabel = new JLabel("Description");
    private JTextArea descriptionArea = new JTextArea();
    private JScrollPane descriptionScroll = new JScrollPane(descriptionArea);
    private JLabel titleLabel = new JLabel("Title :");
    private JTextField titleText = new JTextField();
    private JLabel genreLabel = new JLabel("Genre :");
    private JTextField genreText = new JTextField();
    private JLabel ratingLabel = new JLabel("Rating :");
    private JTextField ratingText = new JTextField();
    private JLabel priceLabel = new JLabel("Price :");
    private JTextField priceText = new JTextField();
    private JLabel typeLabel = new JLabel("Type :");
    private JTextField typeText = new JTextField();
    private JLabel platformLabel = new JLabel("Platform :");
    private JTextField platformText = new JTextField();
    private JLabel studioLabel = new JLabel("Studio :");
    private JTextField studioText = new JTextField();
    private JLabel directorLabel = new JLabel("Director :");
    private JTextField directorText = new JTextField();
    private JLabel durationLabel = new JLabel("Duration :");
    private JTextField durationText = new JTextField();
    private JButton deleteItem = new JButton("Delete");
    private JButton modifyItem = new JButton("Modify");
    private IconButton backButton = new IconButton("images/backIcon.png",50, 50);
    private JButton saveItem = new JButton("Save");
    private File selectedImageFile;
    private JLabel rt = new JLabel("/10");
    private JLabel pr = new JLabel("$");
    private JLabel minutes = new JLabel("minutes");
    private JButton buyItem = new JButton("Buy");
    private JLabel stockLabel = new JLabel("Stock :");
    private JTextField stockText = new JTextField();
}
