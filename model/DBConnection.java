package model;

import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DBConnection {
   private static final String URL = "jdbc:mysql://localhost:3306/myjavaproject";
   private static final String USER = "root";
   private static final String PASSWORD = "";
   private static Connection conn = null;

   public static Connection getConnection() {
      try {
         if (conn == null || conn.isClosed()) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myjavaproject", "root", "");
            System.out.println("Database Connected Successfully!");
         }
      } catch (ClassNotFoundException e) {
         JOptionPane.showMessageDialog((Component)null, "MySQL JDBC Driver not found! Please add mysql-connector-j.jar", "Driver Error", 0);
         e.printStackTrace();
      } catch (SQLException e) {
         JOptionPane.showMessageDialog((Component)null, "Database Connection Failed! Please check if XAMPP MySQL is running.", "Database Error", 0);
         e.printStackTrace();
      }

      return conn;
   }
}
