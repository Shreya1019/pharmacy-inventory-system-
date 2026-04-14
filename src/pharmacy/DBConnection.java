/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Shreya
 */
package pharmacy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() {

        Connection con = null;

        try {

            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/pharmacy_inventory",
                "root",
                "shreya1919"
            );

            System.out.println("Database Connected Successfully");

        } catch (SQLException e) {
            System.out.println(e);
        }

        return con;
    }

   
}