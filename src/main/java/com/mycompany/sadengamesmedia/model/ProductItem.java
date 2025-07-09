/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sadengamesmedia.model;

import com.mycompany.sadengamesmedia.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author denia
 */
public abstract class ProductItem {
    
    protected int id;
    protected String title;
    protected String genre;
    protected String imagePath;
    protected double price;
    protected String description;
    protected float rating;
    protected String type;

    public ProductItem(int id, String title, String genre, float rating, String description, double price, String imagePath, String type ) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.imagePath = imagePath;
        this.price = price;
        this.description = description;
        this.type = type;
    }
    //public abstract String getType();
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
    
    public static List<ProductItem> getAllProducts() throws SQLException {
        List<ProductItem> result = new ArrayList<>();
        Connection conn = DatabaseManager.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM products");

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            String genre = rs.getString("genre");
            float rating = rs.getFloat("rating");
            
            String description = rs.getString("description");
            double price = rs.getDouble("price");
            String imagePath = rs.getString("image_path");
            String type = rs.getString("product_type");
            
            if (type.equals("Movie")) {
                int productId = id;
                PreparedStatement movieStmt = conn.prepareStatement("SELECT director, duration FROM movies WHERE movie_id = ?");
                movieStmt.setInt(1, productId);
                ResultSet movieRs = movieStmt.executeQuery();
                String director = ""; int duration = 0;
                if (movieRs.next()) {
                    director = movieRs.getString("director");
                    duration = movieRs.getInt("duration");
                }
                movieRs.close();
                movieStmt.close();
            
                Movie movie = new Movie(id, title, genre, rating, description, price, imagePath, type, director, duration);
                
                result.add(movie);
            } else if (type.equals("Videogame")) {
                int productId = id;
                PreparedStatement videogameStmt = conn.prepareStatement("SELECT platform, studio FROM games WHERE games_id = ?");
                videogameStmt.setInt(1, productId);
                ResultSet videogameRs = videogameStmt.executeQuery();
                String platform = ""; String studio = "";
                if (videogameRs.next()) {
                    platform = videogameRs.getString("platform");
                    studio = videogameRs.getString("studio");
                }
                videogameRs.close();
                videogameStmt.close();
                Videogame game = new Videogame(id, title, genre, rating, description, price, imagePath, type, platform, studio);
                result.add(game);
            }
        }
        return result;
    }

}
