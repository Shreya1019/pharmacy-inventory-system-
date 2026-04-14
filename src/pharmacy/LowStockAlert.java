

/**
 *
 * @author Shreya
 */

package pharmacy;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class LowStockAlert {

    public static void main(String[] args) {

        try {

            Connection con = DBConnection.getConnection();

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(
                    "SELECT medicine_name, stock_quantity FROM Medicines WHERE stock_quantity < 50"
            );
            
           
          String message = "";

while (rs.next()) {
     

    message += rs.getString("medicine_name") +
               " | Stock: " + rs.getInt("stock_quantity") + "\n";
}

if(!message.isEmpty()) {

    JOptionPane.showMessageDialog(null,
            "⚠ LOW STOCK ALERT ⚠\n\n" + message,
            "Low Stock Warning",
            JOptionPane.WARNING_MESSAGE);

} else {

    JOptionPane.showMessageDialog(null,
            "All medicines have sufficient stock.");
}
            
        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        }

    }

  
}