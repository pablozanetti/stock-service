package net.strangled.stockService;

import java.sql.*;

public class ItemDao {

    private static final String SELECT_ITEMS_QUERY = "SELECT id, name, amount FROM item";
    private Connection conn = null;
    public void getItems(String jdbcURL, String dbUser, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            if (!conn.isClosed()) {

                PreparedStatement selectItems = conn.prepareStatement(SELECT_ITEMS_QUERY);

                ResultSet rs = selectItems.executeQuery();
                while (rs.next()) {
                    /// instantiate items

                }
                rs.close();
            }

        } catch (Exception e) {
            System.err.println("Exception: "+e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {}
        }

    }

}
