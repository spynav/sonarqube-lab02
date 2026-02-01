package main.java.com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserService {

    // SECURITY ISSUE: Hardcoded credentials
    private String password = "admin123";

    // ✅ FIXED: SQL Injection - using PreparedStatement
    public void findUser(String username) throws SQLException {
        // ✅ SonarQube fix: try-with-resources to auto-close Connection & PreparedStatement
        String query = "SELECT id, name, email FROM users WHERE name = ?";
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/db", "root", password);
                PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, username);
            pst.executeQuery();
        }
    }

    // SMELL: Unused method
    public void notUsed() {
        System.out.println("I am never called");
    }

    // ✅ FIXED: SQL Injection - using PreparedStatement
    public void deleteUser(String username) throws SQLException {
        // ✅ SonarQube fix: try-with-resources to auto-close Connection & PreparedStatement
        String query = "DELETE FROM users WHERE name = ?";
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/db", "root", password);
                PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, username);
            pst.execute();
        }
    }
}