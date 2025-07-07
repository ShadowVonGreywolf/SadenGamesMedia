/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sadengamesmedia.model;

/**
 *
 * @author denia
 */
public class Videogame extends ProductItem{
    private String platform;
    private String studio;

    public Videogame(int id, String title, String genre, float rating, String description, double price, String imagePath, String platform, String studio) {
        super(id, title, genre, rating, description, price, imagePath);
        this.platform = platform;
        this.studio = studio;
    }

    @Override
    public String getType() {
        return "Videogame";
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }
    
    
}
