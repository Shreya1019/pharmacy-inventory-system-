package pharmacy;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ExpiryAlert {

    public static void main(String[] args) {

        try {

            Connection con = DBConnection.getConnection();

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(
                    "SELECT medicine_name, expiry_date FROM Medicines WHERE expiry_date BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 30 DAY)"
            );

            String message = "";

            while (rs.next()) {

                message += rs.getString("medicine_name") +
                           " | Expiry: " + rs.getDate("expiry_date") + "\n";
            }

            if(!message.isEmpty()) {

                JOptionPane.showMessageDialog(null,
                        "⚠ EXPIRY ALERT ⚠\n\n" + message,
                        "Medicine Expiry Warning",
                        JOptionPane.WARNING_MESSAGE);

            } else {

                JOptionPane.showMessageDialog(null,
                        "No medicines expiring within 30 days.");
            }

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

    }

}