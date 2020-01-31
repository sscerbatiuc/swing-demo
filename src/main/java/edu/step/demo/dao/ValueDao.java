package edu.step.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author sscerbatiuc
 */
public class ValueDao {
 
   private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/test";
    private static final String DB_USER = "postgres";
    private static final String DB_PASS = "postgres";

    private Connection connect() throws SQLException {
        System.out.println("Connected to postgresql server successfully.");
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    public Map<Integer, String> getAll() {
        String sql = "SELECT id, name FROM app.Data"; // app <-> schema
        try (Connection conn = connect();
                Statement statement = conn.createStatement();
                ResultSet rs = statement.executeQuery(sql)) {
            Map<Integer, String> list = new HashMap<>();
            // Iterate result set
            while (rs.next()) {
                list.put(rs.getInt("id"), rs.getString("name"));
            }
            return list;
        } catch (SQLException ex) {
            System.err.println("Caught an error trying to get all the values");
        }
        return null;
    }
    
    public int add(String value) {
        String sql = "INSERT INTO app.Data(name) VALUES (?)";
        // DELETE FROM app.Data WHERE
         try (Connection conn = connect();
                PreparedStatement dbStatement = conn.prepareStatement(sql)) {

            dbStatement.setString(1, value);

            int affectedRows = dbStatement.executeUpdate();
            System.out.println(String.format("Executed insert statement. Affected %d rows", affectedRows));
            return affectedRows;
        } catch (SQLException ex) {
            System.err.println("Caught an error trying to insert the values: " + ex.getMessage());
        }
        return -1;
    }
    
    public int update(int id, String value) {
        String sql = "UPDATE app.Data SET name=? WHERE id =?";
         try (Connection conn = connect();
                PreparedStatement dbStatement = conn.prepareStatement(sql)) {

            dbStatement.setString(1, value);
            dbStatement.setInt(2, id);

            int affectedRows = dbStatement.executeUpdate();
            System.out.println(String.format("Executed insert statement. Affected %d rows", affectedRows));
            return affectedRows;
        } catch (SQLException ex) {
            System.err.println("Caught an error trying to update the value");
        }
        return -1;
    }
    
    public int delete(int id) {
        String sql = "DELETE FROM app.Data WHERE id =?";
         try (Connection conn = connect();
                PreparedStatement dbStatement = conn.prepareStatement(sql)) {

            dbStatement.setInt(1, id);

            int affectedRows = dbStatement.executeUpdate();
            System.out.println(String.format("Executed delete statement. Affected %d rows", affectedRows));
            return affectedRows;
        } catch (SQLException ex) {
            System.err.println("Caught an error trying to delete the value");
        }
        return -1;
    }
}
