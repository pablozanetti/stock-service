package net.strangled.stockService;

import java.sql.*;
import java.util.ArrayList;

public class ItemDao {

    // TODO create a connection on startup and/or use a connection pool?
    // TODO add this to properties file or parameters
    private static String jdbcURL = "jdbc:postgresql://localhost:5432/stock";
    private static String dbUser = "postgres";
    private static String dbPassword = "postgres";
    //////

    private static final String SELECT_ITEMS_QUERY = "SELECT id, name, amount FROM item";
    private static final String UPDATE_ITEM_AMOUNT = "UPDATE item set amount = ? WHERE id = ?";
    private Connection conn = null;
    public ArrayList<Item> getItems() {
        ArrayList result = new ArrayList();
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            if (!conn.isClosed()) {

                PreparedStatement selectItems = conn.prepareStatement(SELECT_ITEMS_QUERY);
                ResultSet rs = selectItems.executeQuery();
                while (rs.next()) {
                    Item i = new Item(rs.getLong(1), rs.getString(2), rs.getInt(3));
                    result.add(i);
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
        return result;
    }

    public int updateItem(Item item) {
        int count = 0;
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            if (!conn.isClosed()) {

                PreparedStatement updateItem = conn.prepareStatement(UPDATE_ITEM_AMOUNT);
                updateItem.setInt(1, item.getAmount());
                updateItem.setLong(2, item.getId());
                count = updateItem.executeUpdate();
                updateItem.close();
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

        return count;
    }
}
