/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sadengamesmedia;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JFrame;

/**
 *
 * @author denia
 */
public class SadenGamesMedia {

    
    public static void main(String[] args) throws IOException, SQLException{
    
        new LoginMenu();
        Connection conn = DatabaseManager.getConnection();
    }
   
}
