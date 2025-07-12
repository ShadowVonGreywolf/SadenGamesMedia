/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sadengamesmedia.model;

/**
 *
 * @author denia
 */
public class Movie extends ProductItem {
    private String director ;
    private int duration;

    public Movie(int id, String title, String genre, float rating, String description, double price, String imagePath, String type, int stock, String director, int duration) {
        super(id, title, genre, rating, description, price, imagePath, type, stock);
        this.director = director;
        this.duration = duration;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDirector(){
        return director;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    
}
