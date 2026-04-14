

/**
 *
 * @author Shreya
 */
package pharmacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PharmacySystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\nPHARMACY INVENTORY SYSTEM");
            System.out.println("1. Add Medicine");
            System.out.println("2. View Inventory");
            System.out.println("3. Low Stock Alert");
            System.out.println("4. Expiry Alert");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            try {

                Connection con = DBConnection.getConnection();

                if (choice == 1) {

                    System.out.print("Medicine ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Medicine Name: ");
                    String name = sc.nextLine();

                    System.out.print("Category: ");
                    String category = sc.nextLine();

                    System.out.print("Price: ");
                    double price = sc.nextDouble();

                    System.out.print("Stock Quantity: ");
                    int stock = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Expiry Date (YYYY-MM-DD): ");
                    String expiry = sc.nextLine();

                    String query = "INSERT INTO Medicines VALUES (?,?,?,?,?,?)";

                    PreparedStatement ps = con.prepareStatement(query);

                    ps.setInt(1, id);
                    ps.setString(2, name);
                    ps.setString(3, category);
                    ps.setDouble(4, price);
                    ps.setInt(5, stock);
                    ps.setString(6, expiry);

                    ps.executeUpdate();

                    System.out.println("Medicine Added Successfully");

                }

                else if (choice == 2) {

                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM Medicines");

                    while (rs.next()) {

                        System.out.println(
                            rs.getInt("medicine_id") + " "
                            + rs.getString("medicine_name") + " "
                            + rs.getString("category") + " "
                            + rs.getDouble("price") + " "
                            + rs.getInt("stock_quantity") + " "
                            + rs.getDate("expiry_date")
                        );

                    }

                }

                else if (choice == 3) {

                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(
                        "SELECT medicine_name, stock_quantity FROM Medicines WHERE stock_quantity < 50"
                    );

                    System.out.println("Low Stock Medicines:");

                    while (rs.next()) {

                        System.out.println(
                            rs.getString("medicine_name")
                            + " | Stock: "
                            + rs.getInt("stock_quantity")
                        );

                    }

                }

                else if (choice == 4) {

                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(
                        "SELECT medicine_name, expiry_date FROM Medicines WHERE expiry_date < '2027-01-01'"
                    );

                    System.out.println("Medicines Expiring Soon:");

                    while (rs.next()) {

                        System.out.println(
                            rs.getString("medicine_name")
                            + " | Expiry: "
                            + rs.getDate("expiry_date")
                        );

                    }

                }

                else if (choice == 5) {

                    System.out.println("Exiting System...");
                    break;

                }

                else {

                    System.out.println("Invalid Choice");

                }

            } catch (SQLException e) {

                System.out.println(e);

            }

        }

    }

}