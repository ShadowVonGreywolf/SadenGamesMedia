/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sadengamesmedia;

import com.mycompany.components.LabelImage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
public class AddProductPanel extends JPanel{
    
    public AddProductPanel(){
        initComponents();
    }
    
    private void initComponents(){
        setLayout(null);
        setBackground(new Color(10,15,20));
        
        
        productImage = new LabelImage("images/myAccountIcon.png",250, 250);
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
        choosePhoto.addMouseListener(selectProductImageAction());
        add(choosePhoto);
        
        
        descriptionLabel.setVisible(true);
        descriptionLabel.setBounds(50, 380, 200, 50);
        descriptionLabel.setFont(new Font("Courier New", Font.BOLD, 30));
        descriptionLabel.setForeground(new Color(102, 100, 204));
        add(descriptionLabel);
        
        
        descriptionArea.setBackground(new Color(10,25,40));
        descriptionArea.setForeground(Color.CYAN);
        descriptionArea.setFont(new Font("Courier New", Font.ITALIC, 20));
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        descriptionArea.setVisible(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.addFocusListener(textAreaEmptier(descriptionArea));
        
        
        
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
        
        
        titleText.setBounds(540, 50, 230, 50);
        titleText.setBackground(new Color(10,25,40));
        titleText.setForeground(Color.CYAN);
        titleText.setFont(new Font("Courier New", Font.ITALIC, 20));
        titleText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        titleText.setVisible(true);
        titleText.addFocusListener(textFieldEmptier(titleText));
        add(titleText);
        
        
        genreLabel.setVisible(true);
        genreLabel.setBounds(340, 110, 200, 50);
        genreLabel.setFont(new Font("Courier New", Font.BOLD, 30));
        genreLabel.setForeground(new Color(102, 100, 204));
        add(genreLabel);
        
        
        genreText.setBounds(540, 110, 230, 50);
        genreText.setBackground(new Color(10,25,40));
        genreText.setForeground(Color.CYAN);
        genreText.setFont(new Font("Courier New", Font.ITALIC, 20));
        genreText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        genreText.setVisible(true);
        genreText.addFocusListener(textFieldEmptier(genreText));
        add(genreText);
        
        
        stockLabel.setVisible(true);
        stockLabel.setBounds(810, 110, 150, 50);
        stockLabel.setFont(new Font("Courier New", Font.BOLD, 30));
        stockLabel.setForeground(new Color(102, 100, 204));
        add(stockLabel);
        
        
        stockText.setBounds(810, 170, 80, 50);
        stockText.setBackground(new Color(10,25,40));
        stockText.setForeground(Color.CYAN);
        stockText.setFont(new Font("Courier New", Font.ITALIC, 20));
        stockText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        stockText.setVisible(true);
        stockText.addFocusListener(textFieldEmptier(stockText));
        add(stockText);
        
        
        ratingLabel.setVisible(true);
        ratingLabel.setBounds(340, 170, 200, 50);
        ratingLabel.setFont(new Font("Courier New", Font.BOLD, 30));
        ratingLabel.setForeground(new Color(102, 100, 204));
        add(ratingLabel);
        
        
        ratingText.setBounds(540, 170, 230, 50);
        ratingText.setBackground(new Color(10,25,40));
        ratingText.setForeground(Color.CYAN);
        ratingText.setFont(new Font("Courier New", Font.ITALIC, 20));
        ratingText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        ratingText.setVisible(true);
        ratingText.addFocusListener(textFieldEmptier(ratingText));
        add(ratingText);
        
        
        priceLabel.setVisible(true);
        priceLabel.setBounds(340, 230, 200, 50);
        priceLabel.setFont(new Font("Courier New", Font.BOLD, 30));
        priceLabel.setForeground(new Color(102, 100, 204));
        add(priceLabel);
        
        
        priceText.setBounds(540, 230, 230, 50);
        priceText.setBackground(new Color(10,25,40));
        priceText.setForeground(Color.CYAN);
        priceText.setFont(new Font("Courier New", Font.ITALIC, 20));
        priceText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        priceText.setVisible(true);
        priceText.addFocusListener(textFieldEmptier(priceText));
        add(priceText);
        
        
        typeLabel.setVisible(true);
        typeLabel.setBounds(340, 290, 200, 50);
        typeLabel.setFont(new Font("Courier New", Font.BOLD, 30));
        typeLabel.setForeground(new Color(102, 100, 204));
        add(typeLabel);
        
        
        productType.setBounds(540, 290, 230, 50);
        productType.setVisible(true);
        productType.setFont(new Font("Courier New", Font.ITALIC ,20));
        productType.setBackground(new Color(10,25,40));
        productType.setForeground(Color.CYAN);
        productType.addActionListener(typeComboBox());
        add(productType);    
        
        
        platformLabel.setVisible(false);
        platformLabel.setBounds(500, 350, 200, 50);
        platformLabel.setFont(new Font("Courier New", Font.BOLD, 30));
        platformLabel.setForeground(new Color(102, 100, 204));
        add(platformLabel);
        
        
        platformText.setBounds(710, 350, 230, 50);
        platformText.setBackground(new Color(10,25,40));
        platformText.setForeground(Color.CYAN);
        platformText.setFont(new Font("Courier New", Font.ITALIC, 20));
        platformText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        platformText.setVisible(false);
        platformText.addFocusListener(textFieldEmptier(platformText));
        add(platformText);
        
        
        studioLabel.setVisible(false);
        studioLabel.setBounds(500, 410, 200, 50);
        studioLabel.setFont(new Font("Courier New", Font.BOLD, 30));
        studioLabel.setForeground(new Color(102, 100, 204));
        add(studioLabel);
        
        
        studioText.setBounds(710, 410, 230, 50);
        studioText.setBackground(new Color(10,25,40));
        studioText.setForeground(Color.CYAN);
        studioText.setFont(new Font("Courier New", Font.ITALIC, 20));
        studioText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        studioText.setVisible(false);
        studioText.addFocusListener(textFieldEmptier(studioText));
        add(studioText);
        
        
        directorLabel.setVisible(false);
        directorLabel.setBounds(500, 350, 200, 50);
        directorLabel.setFont(new Font("Courier New", Font.BOLD, 30));
        directorLabel.setForeground(new Color(102, 100, 204));
        add(directorLabel);
        
        
        directorText.setBounds(710, 350, 230, 50);
        directorText.setBackground(new Color(10,25,40));
        directorText.setForeground(Color.CYAN);
        directorText.setFont(new Font("Courier New", Font.ITALIC, 20));
        directorText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        directorText.setVisible(false);
        directorText.addFocusListener(textFieldEmptier(directorText));
        add(directorText);
        
        
        durationLabel.setVisible(false);
        durationLabel.setBounds(500, 410, 200, 50);
        durationLabel.setFont(new Font("Courier New", Font.BOLD, 30));
        durationLabel.setForeground(new Color(102, 100, 204));
        add(durationLabel);
        
        
        durationText.setBounds(710, 410, 230, 50);
        durationText.setBackground(new Color(10,25,40));
        durationText.setForeground(Color.CYAN);
        durationText.setFont(new Font("Courier New", Font.ITALIC, 20));
        durationText.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        durationText.setVisible(false);
        durationText.addFocusListener(textFieldEmptier(durationText));
        add(durationText);
        
        
        addItem.setVisible(false);
        addItem.setForeground(Color.CYAN);
        addItem.setFont(new Font("Arial", Font.BOLD, 15));
        addItem.setBackground(new Color(10,60,40));
        addItem.setOpaque(true);
        addItem.setContentAreaFilled(true);           
        addItem.setBorderPainted(false);
        addItem.setFocusPainted(false);
        addItem.setBounds(740, 500, 180, 50);
        addItem.addMouseListener(addItemAction());
        add(addItem);
        
        
        cancelItem.setVisible(false);
        cancelItem.setForeground(Color.CYAN);
        cancelItem.setFont(new Font("Arial", Font.BOLD, 15));
        cancelItem.setBackground(new Color(90,25,40));
        cancelItem.setOpaque(true);
        cancelItem.setContentAreaFilled(true);           
        cancelItem.setBorderPainted(false);
        cancelItem.setFocusPainted(false);
        cancelItem.setBounds(550, 500, 180, 50);
        cancelItem.addMouseListener(cancelAction() );
        add(cancelItem);
        
    }
    
     private MouseAdapter cancelAction() {
    return new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            // Reset text fields
            titleText.setText("Your title");
            genreText.setText("Your genre");
            ratingText.setText("Your rating");
            priceText.setText("Your price");
            platformText.setText("Your platform");
            studioText.setText("Your studio");
            directorText.setText("Your director");
            durationText.setText("Your duration");
            descriptionArea.setText("Description");
            stockText.setText("Stock");

            productType.setSelectedIndex(0);

            productImage.setNewImage("images/myAccountIcon.png", 250, 250);
            selectedImageFile = null;

            platformLabel.setVisible(false);
            platformText.setVisible(false);
            studioLabel.setVisible(false);
            studioText.setVisible(false);
            directorLabel.setVisible(false);
            directorText.setVisible(false);
            durationLabel.setVisible(false);
            durationText.setVisible(false);

            }
        };
    }
    public void cancelAddAndChangePhotoTrue(){
        cancelItem.setVisible(true);
        addItem.setVisible(true);
        choosePhoto.setVisible(true);
    }
    
    public void cancelAddAndChangePhotoFalse(){
        cancelItem.setVisible(false);
        addItem.setVisible(false);
        choosePhoto.setVisible(false);
    }
    
    private FocusListener textAreaEmptier(JTextArea a){
        return new FocusListener(){
            String string;
        @Override
        public void focusGained(FocusEvent e) {
                if (a.getText().equalsIgnoreCase("Description")) { 
                    string = a.getText();
                    a.setText("");
                }
            
        }
            @Override
            public void focusLost(FocusEvent e) {
                if (a.getText().trim().isEmpty()) {
                    a.setText(string);
                }
            }
        };
    } 
    private FocusListener textFieldEmptier(JTextField a){
        return new FocusListener(){
            String string;
        @Override
        public void focusGained(FocusEvent e) {
            for (String field : fields) {
                if (field.equalsIgnoreCase(a.getText())) { 
                    string = a.getText();
                    a.setText("");
                }
            }
        }
            @Override
            public void focusLost(FocusEvent e) {
                if (a.getText().trim().isEmpty()) {
                    a.setText(string);
                }
            }
    };
    } 
    
    private ActionListener typeComboBox() {
    return new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            String type = (String) productType.getSelectedItem();
            if(type.equals("None")){
                
                platformLabel.setVisible(false);
                platformText.setVisible(false);
                studioLabel.setVisible(false);
                studioText.setVisible(false);
                
                directorLabel.setVisible(false);
                directorText.setVisible(false);
                durationLabel.setVisible(false);
                durationText.setVisible(false);
                
            }else if(type.equals("Videogame")){
                
                directorLabel.setVisible(false);
                directorText.setVisible(false);
                durationLabel.setVisible(false);
                durationText.setVisible(false);
                
                platformLabel.setVisible(true);
                platformText.setVisible(true);
                studioLabel.setVisible(true);
                studioText.setVisible(true);
                
            }else{
                
                platformLabel.setVisible(false);
                platformText.setVisible(false);
                studioLabel.setVisible(false);
                studioText.setVisible(false);
                
                directorLabel.setVisible(true);
                directorText.setVisible(true);
                durationLabel.setVisible(true);
                durationText.setVisible(true);
            }
        }
    };
    }
    private MouseAdapter selectProductImageAction() {
        return new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            FileDialog fileDialog = new FileDialog((Frame) SwingUtilities.getWindowAncestor(AddProductPanel.this), "Select Product Image", FileDialog.LOAD);
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

    private MouseAdapter addItemAction(){
        return new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                String title = titleText.getText().trim();
                String genre = genreText.getText().trim();
                String rating = ratingText.getText().trim();
                String description = descriptionArea.getText().trim();
                String price = priceText.getText().trim();
                String type = (String) productType.getSelectedItem();
                String stock = stockText.getText().trim();

                if (title.isEmpty() || genre.isEmpty() || rating.isEmpty() || description.isEmpty() || price.isEmpty() || type == null || stock.isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Please fill out all fields!","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                float ratingF; double priceD; int stockI;
                try {
                    ratingF = Float.parseFloat(rating);
                    priceD = Double.parseDouble(price);
                    stockI = Integer.parseInt(stock);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,"Invalid price format!","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                File selectedImage = selectedImageFile;
                if (selectedImage == null || !selectedImage.exists()) {
                    JOptionPane.showMessageDialog(null,"Please select a valid image.","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String imageDir = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "product_images").toString();
                String imageName = "product_" + System.currentTimeMillis() + ".png";
                String imagePathInProject = "product_images/" + imageName;

                try {
                    Files.createDirectories(Paths.get(imageDir));
                    Files.copy(selectedImage.toPath(), Paths.get(imageDir, imageName), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException ioEx) {
                    ioEx.printStackTrace();
                    JOptionPane.showMessageDialog(null,"Error saving image.","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try (Connection conn = DatabaseManager.getConnection()) {
                    String insertProductSQL = "INSERT INTO products (title, genre, rating, description, price, image_path, product_type, stock) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement productStmt = conn.prepareStatement(insertProductSQL, Statement.RETURN_GENERATED_KEYS);
                    productStmt.setString(1, title);
                    productStmt.setString(2, genre);
                    productStmt.setFloat(3, ratingF);
                    productStmt.setString(4, description);
                    productStmt.setDouble(5, priceD);
                    productStmt.setString(6, imagePathInProject);
                    productStmt.setString(7, type);
                    productStmt.setInt(8, stockI);
                    productStmt.executeUpdate();

                    ResultSet generatedKeys = productStmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int productId = generatedKeys.getInt(1);

                        if ("Videogame".equalsIgnoreCase(type)) {
                            String platform = platformText.getText().trim();
                            String studio = studioText.getText().trim();
                            if (!platform.isEmpty() && !studio.isEmpty()) {
                                PreparedStatement gameStmt = conn.prepareStatement("INSERT INTO games (games_id, platform, studio) VALUES (?, ?, ?)");
                                gameStmt.setInt(1, productId);
                                gameStmt.setString(2, platform);
                                gameStmt.setString(3, studio);
                                gameStmt.executeUpdate();
                            }
                        } else if ("Movie".equalsIgnoreCase(type)) {
                            String director = directorText.getText().trim();
                            String duration = durationText.getText().trim();
                            if (!director.isEmpty() && !duration.isEmpty()) {
                                PreparedStatement movieStmt = conn.prepareStatement("INSERT INTO movies (movie_id, director, duration) VALUES (?, ?, ?)");
                                movieStmt.setInt(1, productId);
                                movieStmt.setString(2, director);
                                movieStmt.setString(3, duration);
                                movieStmt.executeUpdate();
                            }
                        }
                        
                        JOptionPane.showMessageDialog(null,"Product added successfully!","Well done",JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                     JOptionPane.showMessageDialog(null,"Error! Database error.","Account error",JOptionPane.ERROR_MESSAGE);
                }
                        }
                    };
    }
    
    
    
   private LabelImage productImage;
   private JButton choosePhoto = new JButton("Choose a photo");
   private JLabel descriptionLabel = new JLabel("Description");
   private JTextArea descriptionArea = new JTextArea ("Description");
   private JScrollPane descriptionScroll = new JScrollPane(descriptionArea);
   private JLabel titleLabel = new JLabel("Title :");
   private JTextField titleText = new JTextField("Your title");
   private JLabel genreLabel = new JLabel("Genre :");
   private JTextField genreText = new JTextField("Your genre");
   private JLabel ratingLabel = new JLabel("Rating :");
   private JTextField ratingText = new JTextField("Your rating");
   private JLabel priceLabel = new JLabel("Price :");
   private JTextField priceText = new JTextField("Your price");
   private JLabel typeLabel = new JLabel("Type :");
   String[] productTypeString = {"None", "Videogame", "Movie" };
   private JComboBox<String> productType = new JComboBox<>(productTypeString);
   private JLabel platformLabel = new JLabel("Platform :");
   private JTextField platformText = new JTextField("Your platform");
   private JLabel studioLabel = new JLabel("Studio :");
   private JTextField studioText = new JTextField("Your studio");
   private JLabel directorLabel = new JLabel("Director :");
   private JTextField directorText = new JTextField("Your director");
   private JLabel durationLabel = new JLabel("Duration :");
   private JTextField durationText = new JTextField("Your duration");
   String[] fields = {"Your title","Your genre","Your rating","Your price","Your platform","Your studio","Your director","Your duration"};
   private File selectedImageFile;
   private JButton cancelItem = new JButton("Cancel");
   private JButton addItem = new JButton("Add");
   private JLabel stockLabel = new JLabel("Stock :");
   private JTextField stockText = new JTextField("Stock");
}


